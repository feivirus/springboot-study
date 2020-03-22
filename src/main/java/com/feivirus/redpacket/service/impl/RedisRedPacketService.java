package com.feivirus.redpacket.service.impl;

import com.feivirus.constants.Constants;
import com.feivirus.redpacket.domain.RedPackets;
import com.feivirus.redpacket.executor.GrabRedPacketExecutor;
import com.feivirus.redpacket.helper.RedPacketsHelper;
import com.feivirus.redpacket.helper.RedisHelper;
import com.feivirus.redpacket.service.RedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author feivirus
 */
@Service("redisRedPacketService")
public class RedisRedPacketService implements RedPacketService {
    @Autowired
    private GrabRedPacketExecutor grabRedPacketExecutor;
    @Autowired
    private RedPacketsTableService redPacketsTableService;
    @Autowired
    private RedisHelper redisHelper;

    @Override
    public String grabRedPacket(Integer rid, Integer uid) {
        return grabRedPacketExecutor.execute(String.valueOf(rid), String.valueOf(uid));
    }

    @Transactional
    @Override
    public String sendRedPacket(Integer uid, Integer number, Double amount) {
        /**
         * 红包入库
         */
        RedPackets redPackets = redPacketsTableService.insertRedPackets(uid, number, amount);

        /**
         * 拆分红包
         */
        Integer rid = redPackets.getId();
        List<Double> redPacketList = RedPacketsHelper.generateRedPackets(amount, number);
        String oneRPQueueKey = Constants.GRAP_RP_ONE_SPLIT_QUEUE + rid;
        for(Double element : redPacketList) {
            redisHelper.lpush(oneRPQueueKey, amount.toString(), Constants.SECONDS_OF_ONE_HOUR);
        }

        /**
         * 红包余额，剩余数量入redis
         */
        String overAmountKey = redisHelper.get(Constants.GRAP_RP_OVER_AMOUNT_PREFIX) + rid;
        String overNumberKey = redisHelper.get(Constants.GRAP_RP_OVER_NUMBER_PREFIX) + rid;
        redisHelper.set(overAmountKey, amount.toString());
        redisHelper.expire(overAmountKey, Constants.SECONDS_OF_ONE_HOUR);
        redisHelper.set(overNumberKey, number.toString());
        redisHelper.expire(overNumberKey, Constants.SECONDS_OF_ONE_HOUR);


        return null;
    }
}
