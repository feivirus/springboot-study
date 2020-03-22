package com.feivirus.orderpay.service.impl;

import com.feivirus.orderpay.domain.Order;
import com.feivirus.orderpay.domain.PayApply;
import com.feivirus.orderpay.enums.OrderPayStatusEnum;
import com.feivirus.orderpay.enums.RemoteBankProcessResultEnum;
import com.feivirus.orderpay.service.OrderPayService;
import com.feivirus.orderpay.service.RemoteBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
     * 此方法进来时不能在事务中,调用银行服务可能很久，数据库连接池一直占用，耗尽
     * 5.用户连续多次连接,这里会多次调用,都卡在银行扣款一步
     * 6.锁
     *   redis,zookeeper,
     *   数据库悲观锁(select * from order where id=xxx for update),
     *   数据库乐观锁(版本号 update order set status = x where id =x and version =x)
     *   数据库乐观锁(基于状态 update order set status = 1 where id = x and status =0)
     * @param order
     */
    @Transactional(propagation = Propagation.NEVER, rollbackFor = Exception.class)
    @Override
    public void pay(Order order) {
        try {

            /**
             * 加数据库乐观锁，防止多个线程进入，同时请求银行扣款服务
             * 第一个线程改订单为处理中
             */
            Boolean lock = transactionTemplate.execute(new TransactionCallback<Boolean>() {
                @Override
                public Boolean doInTransaction(TransactionStatus status) {
                    int count = orderTableService.updateByPrimaryKeyAndStatus(order.getId(),
                            OrderPayStatusEnum.PAYING.getCode(),
                            order.getPayStatus());
                    if (count > 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            if (lock == false) {
                System.out.println("订单处理中");
                return;
            }
            //下面这个处理不行.多线程并发情况下，可能都进入了银行扣款服务，没有修改order表状态
//            Order orderDB = orderTableService.selectByPrimaryKey(order.getId());
//            /**
//             * 订单已经支付或者在支付中,返回不用处理.默认是待支付。
//             */
//            if (orderDB != null &&
//                    !orderDB.getPayStatus().equals(OrderPayStatusEnum.UNPAID)) {
//                return;
//            }

            /**
             * 创建支付流水
             */
            PayApply payApply = payApplyService.buildPayApply(order.getId());

            /**
             * 调用银行接口扣款
             */
            String bankNo = "123";
            Integer bankResult = remoteBankService.deductionMoney(payApply.getPaySerialNumber(), bankNo);
            if (bankResult.equals(RemoteBankProcessResultEnum.PROCESSED_FAILED) ||
                    bankResult.equals(RemoteBankProcessResultEnum.PROCESS_TIMEOUT) ||
                    bankResult.equals(RemoteBankProcessResultEnum.PROCESSING)) {
                /***
                 * 支付失败或者超时,修改order表,后面调度任务异步处理
                 */

                System.out.println("银行处理失败");
                order.setPayStatus(OrderPayStatusEnum.PAY_FAILED.getCode());
            } else {
                /**
                 * 银行支付成功,修改订单状态,减库存
                 */
                order.setPayStatus(OrderPayStatusEnum.PAYED.getCode());
            }


            transactionTemplate.execute(new TransactionCallback<String>() {
                @Override
                public String doInTransaction(TransactionStatus status) {
                    orderTableService.updateByPrimaryKey(order);
                    return null;
                }
            });
        } catch (Exception ex) {
            System.out.println("支付失败");
            ex.printStackTrace();
        }
    }
}
