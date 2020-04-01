DROP DATABASE IF EXISTS xlm;
create DATABASE xlm  default character set utf8 collate utf8_general_ci;
use xlm;

DROP TABLE IF EXISTS merchant;
create table merchant
(
    id  varchar(40) not null primary key comment '主键',
    merchant_code  varchar(40) comment '商户编号',
    name   varchar(32) comment '商户名称',
    mobile     varchar(32) comment '商户手机号',
    mobile_cipher  varchar(50) comment '商户手机号密文',
    vip_type   int default 0 comment '商户会员标识 0:非会员 y:年会员 m:月会员',
    vip_start_date timestamp comment 'vip开始时间',
    vip_end_date   timestamp comment 'vip结束时间',
    register_date  timestamp comment '注册时间',
    active_date    timestamp comment '激活时间',
    call_back_time timestamp comment '召回时间',
    brand_id   varchar(32) comment '更新时间',
    user_id    varchar(32) comment '激活时间',
    created    timestamp    not null default current_timestamp comment '创建时间',
    modified   timestamp    not null default current_timestamp on update current_timestamp comment '修改时间'
) comment  '商户表';

DROP TABLE IF EXISTS merchant_brand;
create table merchant_brand
(
    id  varchar(40) not null  primary key comment '主键',
    merchant_id   varchar(40) comment '商户id',
    merchant_code    varchar(40) comment '商户编号',
    brand_id  varchar(32) comment '品牌id',
    user_id   varchar(32) comment '盟友id',
    fact_id   varchar(32) comment '机具厂商',
    fact_sn   varchar(64) comment '机具序号',
    fact_status    int default 0 comment '激活状态0已登记2未激活1已激活',
    fact_state     int default 0 comment '默认0装机1解绑2',
    brand_flag     int default 1 not null comment '品牌标记：1：默认 0：旧的',
    name   varchar(32) comment '商户名称',
    mobile     varchar(32) comment '商户手机号',
    mobile_cipher  varchar(50) comment '商户手机号密文',
    install_date   timestamp    comment '安装时间',
    active_date    timestamp    comment '激活时间',
    created    timestamp    not null default current_timestamp comment '创建时间',
    modified   timestamp    not null default current_timestamp on update current_timestamp comment '修改时间'
) comment   '商户产品表';

DROP TABLE IF EXISTS rate_check_in;
create table rate_check_in
(
    id  varchar(32) not null primary key comment '主键',
    merchant_brand_id  varchar(32)  comment '商户产品表id',
    fact_sn   varchar(20)  not null comment '机具序列号',
    fact_id   varchar(32)  not null comment '厂商编号',
    brand_id  varchar(32)  not null comment '产品id',
    rate_id   varchar(32)  not null comment '费率编号',
    rate_desc varchar(120) not null comment '费率描述',
    check_in_user varchar(32)  not null comment '登记用户编号',
    created   timestamp    not null default current_timestamp comment '创建时间',
    modified  timestamp    not null default current_timestamp on update current_timestamp comment '修改时间'
) comment  '登记费率表';


DROP TABLE IF EXISTS rate_check_in_log;
create table rate_check_in_log
(
    id  varchar(32)  not null  primary key comment '主键',
    merchant_brand_id  varchar(32)  comment '商户产品表id',
    fact_sn   varchar(20)  not null comment '机具序列号',
    fact_id   varchar(32)  not null comment '厂商编号',
    brand_id  varchar(32)  not null comment '产品id',
    rate_id   varchar(32)  not null comment '费率编号',
    rate_desc varchar(120) not null comment '费率描述',
    check_in_user varchar(32)  not null comment '登记用户编号',
    created   timestamp    not null default current_timestamp comment '创建时间',
    modified  timestamp    not null default current_timestamp on update current_timestamp comment '修改时间'
)comment   '登记费率表日志';

-- alter table merchant_brand drop foreign key  merchant_id_out ;
-- alter table merchant_brand drop foreign key  rate_check_in_log_out ;
-- alter table merchant_brand drop foreign key  rate_check_in_out ;
--
-- -- 创建merchant时，更新brand的merchantId；
-- alter table merchant_brand add constraint merchant_id_out foreign key(merchant_id) references merchant(id);
-- alter table rate_check_in_log add constraint rate_check_in_log_out foreign key(merchant_brand_id) references merchant_brand(id);
-- alter table rate_check_in add constraint rate_check_in_out foreign key(merchant_brand_id) references merchant_brand(id);
