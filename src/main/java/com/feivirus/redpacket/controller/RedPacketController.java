package com.feivirus.redpacket.controller;

import com.feivirus.redpacket.service.RedPacketService;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author feivirus
 */
@Controller
public class RedPacketController {
    @Resource(name = "dBRowLockRedPacketService")
    private RedPacketService dBRowLockRedPacketService;
    @Resource(name = "redisRedPacketService")
    private RedPacketService redisRedPacketService;

    @GetMapping("/sendRedPacket")
    @ResponseBody
    public String sendRedPacket(Integer uid, Integer number, Double amount) {
        String result = redisRedPacketService.sendRedPacket(uid, number, amount);
        return result;
    }

    /**
     * 抢红包
     * @param rid 红包id
     * @param uid 用户id
     * @return
     */
    @GetMapping("/grabRedPacket")
    @ResponseBody
    public String grabRedPacket(@RequestParam String rid,
                                @RequestParam String uid) {
        if ((!NumberUtils.isDigits(rid)) || (!NumberUtils.isDigits(uid))) {
            return "参数错误";
        }
        //String result = dBRowLockRedPacketService.grabRedPacket(Integer.valueOf(rid), Integer.valueOf(uid));
        String result = redisRedPacketService.grabRedPacket(Integer.valueOf(rid),
                Integer.valueOf(uid));
        return result;
    }
}
