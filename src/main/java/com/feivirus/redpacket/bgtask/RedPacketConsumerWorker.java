package com.feivirus.redpacket.bgtask;

import com.feivirus.constants.Constants;
import com.feivirus.redpacket.domain.RedPackets;
import com.feivirus.redpacket.domain.RpRecords;
import com.feivirus.redpacket.domain.UserAccount;
import com.feivirus.redpacket.helper.RedisHelper;
import com.feivirus.redpacket.service.impl.RedPacketsTableService;
import com.feivirus.redpacket.service.impl.RpRecordsService;
import com.feivirus.redpacket.service.impl.UserAccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @author feivirus
 */
//@Component
public class RedPacketConsumerWorker implements Runnable {
    @Autowired
    private RedisHelper redisHelper;
    @Autowired
    private RedPacketsTableService redPacketsTableService;
    @Autowired
    private RpRecordsService rpRecordsService;
    @Autowired
    private UserAccountService userAccountService;

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
                String oneRPQueueKey = Constants.GRAP_RP_ONE_SPLIT_QUEUE + rid;
                if (Double.parseDouble(overAmountStr) > 0 &&
                    Integer.parseInt(overNumberStr) > 0 &&
                    redisHelper.llen(oneRPQueueKey) > 0) {
                    String grabedAmountStr = redisHelper.brpop(oneRPQueueKey, 30, String.class);
                    System.out.println(uid + " 抢到红包金额 " + grabedAmountStr);

                    //修改红包余额,插入红包记录,转账等数据库操作
                    updateDatabase(Integer.parseInt(rid), Integer.parseInt(uid),
                            Double.parseDouble(grabedAmountStr));

                    //修改redis的红包余额
                    Double newOverAmount = Double.parseDouble(overAmountStr) -
                            Double.valueOf(grabedAmountStr);
                    Integer newOverNumber = Integer.parseInt(overNumberStr) - 1;
                    redisHelper.set(Constants.GRAP_RP_OVER_AMOUNT_PREFIX + rid, newOverAmount.toString());
                    redisHelper.set(Constants.GRAP_RP_OVER_NUMBER_PREFIX + rid, newOverNumber.toString());

                    //设置用户抢到红包的redis,在GrabRedPacketExecutor中的请求正常返回
                    String userResultKey = Constants.GRAP_RP_RESULT_PREFIX + rid + "_" + uid;
                    redisHelper.set(userResultKey, grabedAmountStr);
                    redisHelper.expire(userResultKey, Constants.SECONDS_OF_ONE_HOUR);
                }
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

    /**
     * 修改红包余额,插入红包记录,转账等数据库操作
     * @param rid
     * @param uid
     * @param amount
     * @return
     */
    @Transactional
    public String updateDatabase(Integer rid, Integer uid, Double amount) {
        /**
         * 修改红包余额
         */
        RedPackets redPackets = redPacketsTableService.selectByPrimaryKey(rid);

        double newAmount = redPackets.getOverAmount() - amount;
        redPackets.setOverAmount(newAmount);
        int newNumber = redPackets.getOverNumber() - 1;
        redPackets.setOverNumber(newNumber);
        redPacketsTableService.updateByPrimaryKey(redPackets);

        /**
         * 插入红包记录
         */
        RpRecords rpRecords = new RpRecords();
        rpRecords.setUid(uid);
        rpRecords.setRid(rid);
        rpRecords.setAmount(amount);
        rpRecords.setAddTime(new Date());
        rpRecordsService.insert(rpRecords);

        /**
         * 转钱给收红包的人
         */
        UserAccount userAccount = userAccountService.selectUserAccountById(uid);
        userAccount.setAmount(userAccount.getAmount() + amount);
        userAccountService.updateByPrimaryKey(userAccount);
        return "";
    }
}
