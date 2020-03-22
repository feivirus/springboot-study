create table t_user_account (
id int(11) not null AUTO_INCREMENT COMMENT '用户id',
user_name varchar(64) default null,
amount double DEFAULT 0,
PRIMARY key(id)
)ENGINE=INNODB DEFAULT charset=utf8;

create table t_order (
id int(11) not null AUTO_INCREMENT COMMENT '订单id',
order_no varchar(64) COMMENT '订单号',
goods_id int(11) default null,
user_id int(11) DEFAULT NULL COMMENT '用户id',
PRIMARY key(id)
)ENGINE=INNODB DEFAULT charset=utf8;

create table t_goods (
id int(11) not null AUTO_INCREMENT COMMENT '订单id',
goods_no varchar(64) COMMENT '商品编号',
goods_name varchar(20) default null COMMENT '商品名称',
goods_count int(11) DEFAULT NULL COMMENT '库存数量',
PRIMARY key(id)
)ENGINE=INNODB DEFAULT charset=utf8;