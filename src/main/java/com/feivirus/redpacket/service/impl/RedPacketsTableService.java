package com.feivirus.redpacket.service.impl;

import com.feivirus.redpacket.dao.RedPacketsMapper;
import com.feivirus.redpacket.domain.RedPackets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author feivirus
 * 对表red_packets相关的操作
 */
@Service
public class RedPacketsTableService {
    @Autowired
    private RedPacketsMapper redPacketsMapper;

    public int updateByPrimaryKey(RedPackets redPackets) {
        return redPacketsMapper.updateByPrimaryKey(redPackets);
    }

    public RedPackets insertRedPackets(Integer uid, Integer number, Double amount) {
        RedPackets redPackets = new RedPackets();

        redPackets.setTotalAmount(amount);
        redPackets.setTotalNumber(number);
        redPackets.setOverNumber(number);
        redPackets.setOverAmount(amount);
        redPackets.setAddTime(new Date());
        redPackets.setVersion(0);
        redPacketsMapper.insert(redPackets);
        return redPackets;
    }
}
