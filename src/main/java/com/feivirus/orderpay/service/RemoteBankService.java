package com.feivirus.orderpay.service;

/**
 * @author feivirus
 */
public interface RemoteBankService {
    /**
     * 银行扣款
     * @param paySerialNumber
     * @param bankNo
     * @return
     */
    Integer deductionMoney(String paySerialNumber, String bankNo);
}
