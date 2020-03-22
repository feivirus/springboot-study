# springboot-study
一.红包实现思路  
(一)发红包  
1.红包入库  
2.把红包预先拆分好，每个拆分后的红包放入redis队列中，key是红包id  
3.在redis中缓存每个红包的剩余数量，剩余大小，key是红包id  
(二)抢红包  
1.以红包id，用户id为key，取出redis中的的用户红包信息缓存，如果状态为已经抢过，直接返回  
2.以红包id为key，判断redis中的红包剩余数量，剩余大小，如果到0，则红包已经抢完.  
3.以红包id，用户id为一个请求，入redis的list请求队列，异步处理.spring启动时新建线程一直处理这个队列。
  然后当前线程死循环取redis的处理结果，以红包id，用户id为key.异步线程处理完会set这个key.  
4.后台异步线程处理:取出redis的list请求队列的元素,如果当前红包还有剩余数量，剩余大小，取出红包，修改数据库中的红包
剩余金额，插入红包记录，转账给抢红包的人。以红包id，用户id为key，设置redis中当前这个红包的状态。这样，用户的请求
线程可以正常退出.  
5.问题:如果用户请求队列的红包，一直没有处理，或者后台线程出异常，用户的controller过来的请求会一直卡在那。  

二.订单支付实现思路  
1.加锁，数据库悲观锁(select for update)，乐观锁(版本号，状态等)，zk锁，redis锁.
防止并发情况下，多个线程同时处理一个订单，请求银行扣款.比如基于状态的乐观锁，只有第一个线程能改订单为处理中，
后面的请求直接返回.  
2.创建银行扣款申请记录，通过流水号，不是订单号，作为请求银行的uuid，因为一个订单可能有多次扣款请求.这是处理银行
接口本身的幂等性问题.  
3.请求远程银行接口扣款，需要处理超时行为  
4.判断远程银行扣款返回值，如果是超时，扣款中，扣款失败，入库，后台使用定时任务,异步处理这种情况。  
5.事务请求更新数据库中的订单状态
