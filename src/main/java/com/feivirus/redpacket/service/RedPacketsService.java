package com.feivirus.redpacket.service;

import com.feivirus.redpacket.dao.RedPacketsMapper;
import com.feivirus.redpacket.domain.RedPackets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author feivirus
 * 对表red_packets相关的操作
 */
@Service
public class RedPacketsService {
    @Autowired
    private RedPacketsMapper redPacketsMapper;

    public int updateByPrimaryKey(RedPackets redPackets) {
        return redPacketsMapper.updateByPrimaryKey(redPackets);
    }
}
