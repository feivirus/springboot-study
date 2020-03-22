package com.feivirus.orderpay.service.impl;

import com.feivirus.orderpay.domain.Order;
import com.feivirus.orderpay.domain.PayApply;
import com.feivirus.orderpay.enums.OrderPayStatusEnum;
import com.feivirus.orderpay.enums.RemoteBankProcessResultEnum;
import com.feivirus.orderpay.service.OrderPayService;
import com.feivirus.orderpay.service.RemoteBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author feivirus
 * 订单支付服务
 */
public class OrderPayServiceImpl implements OrderPayService {
    @Autowired
    private RemoteBankService remoteBankService;
    @Autowired
    private OrderTableService orderTableService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private PayApplyService payApplyService;

    /**
     * 事务
     * 1.是否已经支付过
     * 2.调用银行远程接口支付
     * 处理超时,订单返回支付中,添加流水号，做幂等性操作
     * 3.修改订单支付状态
     * 4.修改库存数量
     * @param order
     */
    @Override
    public void pay(Order order) {
        Order orderDB = orderTableService.selectByPrimaryKey(order.getId());
        /**
         * 订单已经支付或者在支付中,返回不用处理.默认是待支付。
         */
        if (orderDB != null &&
            !orderDB.getPayStatus().equals(OrderPayStatusEnum.UNPAID))  {
            return;
        }

        /**
         * 创建支付流水
         */
        PayApply payApply = payApplyService.buildPayApply(orderDB.getId());

        /**
         * 调用银行接口扣款
         */
        String bankNo = "123";
        Integer bankResult = remoteBankService.deductionMoney(payApply.getPaySerialNumber(), bankNo);
        if (bankResult.equals(RemoteBankProcessResultEnum.PROCESSED_FAILED) ||
            bankResult.equals(RemoteBankProcessResultEnum.PROCESS_TIMEOUT)) {
            /***
             * 支付失败或者超时,修改order表
             */
            transactionTemplate.execute(new TransactionCallback<String>() {
                @Override
                public String doInTransaction(TransactionStatus status) {
                    return null;
                }
            });
            System.out.println("银行处理失败");

            return;
        }

        /**
         * 银行支付成功,修改订单状态,减库存
         */
        order.setPayStatus(OrderPayStatusEnum.PAYED.getCode());
        transactionTemplate.execute(new TransactionCallback<String>() {
            @Override
            public String doInTransaction(TransactionStatus status) {

                return null;
            }
        });

    }
}
