create table t_user_account (
id int(11) not null AUTO_INCREMENT COMMENT '用户id',
user_name varchar(64) default null,
amount double DEFAULT 0,
PRIMARY key(id)
)ENGINE=INNODB DEFAULT charset=utf8;

create table t_rp_records (
id int(11) not null AUTO_INCREMENT,
rid int(11) not null COMMENT '红包id',
uid int(11) not null COMMENT '用户id,t_user_account表uid',
amount double DEFAULT 0 COMMENT '红包金额',
add_time datetime DEFAULT NULL COMMENT '创建时间',
PRIMARY key(id)
)ENGINE=INNODB DEFAULT charset=utf8 COMMENT='红包记录表';

create table t_red_packets (
id int(11) not null auto_increment,
uid int(11) not null COMMENT '发红包的账号',
version int(11) not null comment '版本',
total_amount double DEFAULT 0 comment '红包总额',
total_number int default 0 comment '红包总个数',
over_amount double DEFAULT 0 comment '剩余金额',
over_number int DEFAULT 0 comment '剩余个数',
over_time datetime DEFAULT null comment '截止时间',
add_time datetime DEFAULT CURRENT_TIMESTAMP comment '创建时间',
PRIMARY key(id)
)ENGINE=INNODB DEFAULT charset=utf8 COMMENT='红包表';