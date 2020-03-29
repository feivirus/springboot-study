package com.feivirus.distributedlock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author feivirus
 */
@Service
public class DistributedLockService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private Redisson redisson;

    /**
     * 1.加分布式锁 setnx
     * 2.如果加完锁，系统立即挂掉了，需要设置锁的超时时间.
     * 3.加锁和超时时间，两步需要在一个事务里面完成.
     * 4.如果系统执行时间为超时时间前面一秒，此时jvm进行2秒的gc，其他线程已经拿到锁了，本线程会把锁删掉，
     *    其他线程加的锁就没了。所以需要uuid，标识删除的是本线程建立的锁
     * 5.超时时间假设设置10秒，可能业务执行超过了10秒，锁可能自动消失，其他线程会拿到锁.
     *  需要开个新线程定时器，对超时时间延续，比如每隔10秒/3 = 3秒检测一次,如果锁还存在,重新把锁超时时间设置为10秒
     * 6.redission中间件实现了分布式锁，可以直接调用
     * 7.redis的主从数据可能不一致，可能刚加完锁，master挂了，slave节点升级为master节点时，没有当前锁信息.
     * 8.可以把一个商品设置多个key，增加并发性，比如每个key对应10个库存，有10个key
     *   参考RedLock算法
     * @return
     */
    public String deductStockWithRedis() {
        System.out.println("进入减库存操作");

        /**
         * 加锁和设置超时在一个事务内完成 setnx
         * 注意是setIfAbsent == setnx ,不是set
         */
        String lockValue = UUID.randomUUID().toString();
        String lockKey = "lockKey";
//        Boolean locked = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, lockValue);
//        stringRedisTemplate.expire(lockKey, 60, TimeUnit.SECONDS);
        Boolean locked = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, 60, TimeUnit.SECONDS);

        if (locked == null ||
            locked.equals(Boolean.FALSE)) {
            return "系统繁忙,稍后再试";
        }

        Integer stock = null;
        try {
            stock = Integer.valueOf(stringRedisTemplate.opsForValue().get("stock"));
            if (stock != null &&
                    stock > 0) {
                stock -= 1;

                Thread.sleep(100000);
                stringRedisTemplate.opsForValue().set("stock", stock.toString());
                System.out.println("剩余库存 " + stock);
            } else {
                System.out.println("剩余库存为0,不能减");
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            String afterLockValue = stringRedisTemplate.opsForValue().get(lockKey);
            if (lockValue.equals(afterLockValue)) {
                stringRedisTemplate.delete(lockKey);
            }
        }


        return stock + "";
    }

    public String deductStockWithRedission () {
        Integer stock = null;
        RLock lock = null;
        try {

            String lockKey = UUID.randomUUID().toString();
            lock = redisson.getLock(lockKey);
            lock.lock(60, TimeUnit.SECONDS);

            stock = Integer.valueOf(stringRedisTemplate.opsForValue().get("stock"));
            if (stock != null &&
                    stock > 0) {
                stock -= 1;

                Thread.sleep(100000);
                stringRedisTemplate.opsForValue().set("stock", stock.toString());
                System.out.println("剩余库存 " + stock);
            } else {
                System.out.println("剩余库存为0,不能减");
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
        return stock + "";
    }

    public String deductStockWithZookeeper() {
        return "";
    }
}
