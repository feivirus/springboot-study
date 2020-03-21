package com.feivirus.redpacket.controller;

import com.feivirus.redpacket.service.GrabRedPacketService;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author feivirus
 */
@Controller
public class RedPacketController {
    @Autowired
    private GrabRedPacketService grabRedPacketService;

    /**
     * 抢红包
     * @param rid 红包id
     * @param uid 用户id
     * @return
     */
    @GetMapping("/grabRedPakcet")
    @ResponseBody
    public String grabRedPacket(@RequestParam String rid,
                                @RequestParam String uid) {
        if ((!NumberUtils.isDigits(rid)) || (!NumberUtils.isDigits(uid))) {
            return "参数错误";
        }
        String result = grabRedPacketService.grabRedPacket(Integer.valueOf(rid), Integer.valueOf(uid));
        return result;
    }
}
