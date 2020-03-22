package com.feivirus.redpacket.executor;

import com.feivirus.constants.Constants;
import com.feivirus.redpacket.helper.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author feivirus
 */
@Component
public class GrabRedPacketExecutor {
    @Autowired
    private RedisHelper redisHelper;

    public String execute(String rid ,String uid) {
        String result = "";

        /**
         * 已经抢过红包
         */
        String statusKey = redisHelper.get(Constants.GRAB_RP_STATUS_PREFIX) + rid + "-" + uid;
        if ("1".equals(statusKey)) {
            return "已经抢过红包";
        }

        /**
         * 红包是否已经被抢完
         */
        String overAmountKey = redisHelper.get(Constants.GRAP_RP_OVER_AMOUNT_PREFIX) + rid;
        String overNumberKey = redisHelper.get(Constants.GRAP_RP_OVER_NUMBER_PREFIX) + rid;
        //红包拆分的队列不为空
        String oneRPQueueKey = Constants.GRAP_RP_ONE_SPLIT_QUEUE + rid;
        if (redisHelper.llen(oneRPQueueKey) == 0 ||
                Integer.parseInt(overNumberKey) <= 0 ||
            Double.parseDouble(overAmountKey) <= 0) {
            return "红包已经抢完";
        }

        /**
         * 红包可以抢，请求入队
         */
        redisHelper.lpush(Constants.GRAP_RP_QUEUE_PREFIX, rid + "_" + uid,
                Constants.SECONDS_OF_ONE_HOUR);

        /**
         * 等待redis队列处理完，返回抢红包结果
         * 这地方如果redis长期没处理完，线程会一直等，线程耗尽?
         */
        int count = 0;
        String userResultKey = Constants.GRAP_RP_RESULT_PREFIX + rid + "_" + uid;
        String userResult = null;
        while ( (userResult = redisHelper.get(userResultKey)) == null) {
            if (count >= 20) {
                break;
            }
            count++;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        if (userResult != null) {
            return "抢到红包 " + userResult;
        } else {
            return "服务器繁忙";
        }
    }
}
