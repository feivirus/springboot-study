package com.feivirus.orderpay.service.impl;

import com.feivirus.orderpay.dao.OrderMapper;
import com.feivirus.orderpay.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author feivirus
 */
@Service
public class OrderTableService {
    @Autowired
    private OrderMapper orderMapper;

    public Order selectByPrimaryKey(Integer orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    public int updateByPrimaryKey(Order order) {
        return orderMapper.updateByPrimaryKey(order);
    }

    /**
     * 基于状态的数据库乐观锁.
     * @param orderId
     * @param newStatus
     * @param oldStatus
     * @return
     */
    public int updateByPrimaryKeyAndStatus(Integer orderId, Integer newStatus, Integer oldStatus) {
        return 1;
    }
}
