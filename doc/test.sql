-- jdbc:h2:file:/worker/workspace/ddd/customer/data/customer

show tables;

select *
from merchant_brand;

drop table association_value_entry if exists;
drop table merchant if exists;
drop table merchant_brand if exists;
drop table rate_check_in if exists;
drop table rate_check_in_log if exists;
drop table saga_entry if exists;
drop table token_entry if exists;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;
create table association_value_entry
(
    id                bigint       not null,
    association_key   varchar(255) not null,
    association_value varchar(255),
    saga_id           varchar(255) not null,
    saga_type         varchar(255),
    primary key (id)
);
create table merchant
(
    id           bigint generated by default as identity,
    active_date    timestamp,
    brand_id       varchar(255),
    call_back_time timestamp,
    created        timestamp,
    merchant_code  varchar(255),
    mobile         varchar(255),
    mobile_cipher  varchar(255),
    modified       timestamp,
    name           varchar(255),
    register_date  timestamp,
    user_id        varchar(255),
    vip_end_date   timestamp,
    vip_start_date timestamp,
    vip_type       integer,
    primary key (id)
) ;
create table merchant_brand
(
    id          bigint generated by default as identity,
    brand_id      varchar(255),
    created       timestamp,
    active_date   timestamp,
    brand_flag    integer,
    fact_id       varchar(255),
    fact_sn       varchar(255),
    fact_state    integer,
    fact_status   integer,
    install_date  timestamp,
    merchant_code varchar(255),
    merchant_id   bigint,
    mobile        varchar(255),
    mobile_cipher varchar(255),
    name          varchar(255),
    modified      timestamp,
    user_id       varchar(255),
    primary key (id)
)
;
create table rate_check_in
(
    id              bigint generated by default as identity,
    brand_id          varchar(255),
    check_in_user     varchar(255),
    created           timestamp,
    fact_id           varchar(255),
    fact_sn           varchar(255),
    merchant_brand_id bigint,
    modified          timestamp,
    rate_desc         varchar(255),
    rate_id           varchar(255),
    primary key (id)
);
create table rate_check_in_log
(
    id              bigint generated by default as identity,
    brand_id          varchar(255),
    check_in_user     varchar(255),
    created           timestamp,
    fact_id           varchar(255),
    fact_sn           varchar(255),
    merchant_brand_id bigint,
    modified          timestamp,
    rate_desc         varchar(255),
    rate_id           varchar(255),
    primary key (id)
);
create table saga_entry
(
    saga_id         varchar(255) not null,
    revision        varchar(255),
    saga_type       varchar(255),
    serialized_saga blob,
    primary key (saga_id)
);
create table token_entry
(
    processor_name varchar(255) not null,
    segment        integer      not null,
    owner          varchar(255),
    timestamp      varchar(255) not null,
    token          blob,
    token_type     varchar(255),
    primary key (processor_name, segment)
);
create index IDXk45eqnxkgd8hpdn6xixn8sgft on association_value_entry (saga_type, association_key, association_value);
create index IDXgv5k1v2mh6frxuy5c0hgbau94 on association_value_entry (saga_id, saga_type);
alter table merchant_brand
    add constraint FKmc35vasrbk8mxo2c0bwa14iyy foreign key (id) references rate_check_in;

alter table rate_check_in_log
    add constraint FKpeurc8bxgixbnrn8saa9scv9r foreign key (merchant_brand_id) references merchant_brand;

