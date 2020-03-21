package com.feivirus.redpacket.service;

/**
 * @author feivirus
 */
public interface RedPacketService {
    /**
     * 抢红包
     * @param rid
     * @param uid
     * @return
     */
    public String grabRedPacket(Integer rid, Integer uid);

    /**
     * 发红包
     * @param uid
     * @param number
     * @param amount
     * @return
     */
    public String sendRedPacket(Integer uid, Integer number, Double amount);
}
