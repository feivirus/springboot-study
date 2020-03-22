package com.feivirus.orderpay.service;

import com.feivirus.orderpay.domain.Order;

/**
 * @author feivirus
 */
public interface OrderPayService {
    public void pay(Order order);
}
