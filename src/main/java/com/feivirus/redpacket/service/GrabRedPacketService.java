package com.feivirus.redpacket.service;

import com.feivirus.redpacket.dao.GrabRedPakcetsCustomMapper;
import com.feivirus.redpacket.domain.RedPackets;
import com.feivirus.redpacket.domain.RpRecords;
import com.feivirus.redpacket.domain.UserAccount;
import com.feivirus.redpacket.helper.RedPacketsHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author feivirus
 * 数据库悲观锁(行锁)实现
 * 1.效率低
 * 2.可能死锁
 * 3.大量线程抢锁，都阻塞，有太多线程切换
 */
@Service
public class GrabRedPacketService {
    @Autowired
    private RedPacketsService redPacketsService;
    @Autowired
    private RpRecordsService rpRecordsService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private GrabRedPakcetsCustomMapper grabRedPakcetsCustomMapper;

    /**
     * 抢红包
     * @param rid
     * @param uid
     * @return
     */
    public String grabRedPacket(Integer rid, Integer uid) {
        String result = "success";
        /**
         * TODO
         * 检查权限，用户是否有权限抢红包
         */

        /**
         * 查询用户是否已经抢过红包
         */
        Integer count = rpRecordsService.countRpRecords(rid, uid);
        if (count > 0) {
            return "您已经领取过红包";
        }

        /**
         * 查询红包信息，给红包上锁
         * 基于数据库悲观锁实现
         * 多线程进入这里只有一个线程能拿到行锁
         * 如果出现异常，这里没有释放锁，可能会死锁
         */
        RedPackets redPackets = grabRedPakcetsCustomMapper.selectRedPacketsById(rid);
        if (redPackets == null) {
            return "红包不存在";
        }
        if (redPackets.getOverAmount() <= 0 || redPackets.getOverNumber() <= 0) {
            return "红包已经领完";
        }

        /**
         * 拆分红包
         */
        List<Double> redPacketsList = RedPacketsHelper.generateRedPackets(redPackets.getOverAmount(),
                redPackets.getOverNumber());
        if (CollectionUtils.isEmpty(redPacketsList)) {
            return "红包无法拆分";
        }

        /**
         * 取出拆分后的第一个红包,红包表上，可用数量-1，可用金额减去红包的金额
         * 修改红包表的余额和可用数量
         */
        Double grabedMoney = redPacketsList.get(0);
        Integer newOverNumber = redPackets.getOverNumber() - 1;
        Double newOverAmount = redPackets.getOverAmount() - grabedMoney;
        redPackets.setOverAmount(newOverAmount);
        redPackets.setOverNumber(newOverNumber);
        Integer updatedCount = redPacketsService.updateByPrimaryKey(redPackets);

        /**
         * 向抢到红包的用户转钱
         */
        UserAccount userAccount = userAccountService.selectUserAccountById(uid);
        Double newAmount = userAccount.getAmount() + grabedMoney;
        userAccount.setAmount(newAmount);
        userAccountService.updateByPrimaryKey(userAccount);

        /**
         * 插入一条红包记录
         */
        RpRecords rpRecords = new RpRecords();
        rpRecords.setAmount(grabedMoney);
        rpRecords.setRid(rid);
        rpRecords.setUid(uid);
        rpRecordsService.insertSelective(rpRecords);

        return result;
    }
}
