package com.feivirus.redpacket.dao;

import com.feivirus.redpacket.domain.RedPackets;

/**
 * @author feivirus
 */
public interface GrabRedPakcetsCustomMapper {
    RedPackets selectRedPacketsById(Integer rid);
}
