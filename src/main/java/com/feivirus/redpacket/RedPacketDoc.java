package com.feivirus.redpacket;

/**
 * @author feivirus
 * 红包说明
 * 1.乐观锁问题:减少发红包的人的账号余额
 * update user_account set amount = amount - #{amount}, version=version+1
 * where id=#{uid} and version=#{version}
 * 乐观锁问题:
 * 如果多个线程同时执行这个sql，都是取到version=x，第一个线程成功更新后，后面的线程
 * where 里面 version=#{version}时找不到记录，此时version=x+1.这样，后面的线程抢不到红包，
 * 后面的人进来才能抢到红包，手慢的抢到，手快的抢得到.
 * 2.上面只有一个用户抢到时，其他线程请求事务都回滚，很多无效请求，增加DB压力.
 * 3.悲观锁:select * from xxx for update.多个线程请求排队，依次处理，会比较慢.
 */
public class RedPacketDoc {
}
