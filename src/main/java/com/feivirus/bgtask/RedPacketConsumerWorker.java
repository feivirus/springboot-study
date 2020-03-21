package com.feivirus.bgtask;

import com.feivirus.constants.Constants;
import com.feivirus.redpacket.helper.RedisHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author feivirus
 */
@Component
public class RedPacketConsumerWorker implements Runnable {
    @Autowired
    private RedisHelper redisHelper;

    @PostConstruct
    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            runLoop();
        }
    }

    /**
     * 从队列里面取出用户抢红包的请求，处理入库
     */
    public void runLoop() {
        if (redisHelper.llen(Constants.GRAP_RP_QUEUE_PREFIX) > 0) {
            String rpRequest = redisHelper.brpop(Constants.GRAP_RP_QUEUE_PREFIX, 30, String.class);
            if (StringUtils.isNoneBlank(rpRequest)) {
                String rid = rpRequest.split("_")[0];
                String uid = rpRequest.split("_")[1];

                String overAmountStr = redisHelper.get(Constants.GRAP_RP_OVER_AMOUNT_PREFIX + rid);
                String overNumberStr = redisHelper.get(Constants.GRAP_RP_OVER_NUMBER_PREFIX + rid);

            }
        } else {
            try {
                System.out.println("红包消费队列正在等待...");
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
