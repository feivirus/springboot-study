package com.feivirus.orderpay.service.impl;

import com.feivirus.orderpay.dao.PayApplyMapper;
import com.feivirus.orderpay.domain.PayApply;
import com.feivirus.orderpay.enums.RemoteBankProcessResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author feivirus
 */
@Component
public class PayApplyService {
    @Autowired
    private PayApplyMapper payApplyMapper;

    public PayApply buildPayApply(Integer orderId) {
        PayApply payApply = new PayApply();

        payApply.setOrderId(orderId);
        payApply.setPaySerialNumber(buildPaySerialNumber());
        payApply.setProcessStatus(RemoteBankProcessResultEnum.NO_PROCESS.getCode());
        payApplyMapper.insert(payApply);
        return payApply;
    }

    /**
     * 支付流水号，不能重复
     * @return
     */
    public String buildPaySerialNumber() {
        int random = new Random(1000).nextInt();
        return String.valueOf(random);
    }
}
