# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tbBarnd (
  id                        varchar(255) not null,
  name                      varchar(255),
  picture                   varchar(255),
  constraint pk_tbBarnd primary key (id))
;

create table tbOrders (
  id                        varchar(255) not null,
  date                      datetime,
  ulogin_id                 varchar(255),
  status                    varchar(255),
  constraint pk_tbOrders primary key (id))
;

create table tbOrdersDetail (
  id                        varchar(255) not null,
  orders_id                 varchar(255),
  product_id                varchar(255),
  amount                    integer,
  constraint pk_tbOrdersDetail primary key (id))
;

create table tbProductNews (
  number                    varchar(255) not null,
  name                      varchar(255),
  details                   varchar(255),
  date                      varchar(255),
  picture                   varchar(255),
  constraint pk_tbProductNews primary key (number))
;

create table tbProductPhone (
  id                        varchar(255) not null,
  name                      varchar(255),
  price                     varchar(255),
  picture                   varchar(255),
  details                   varchar(255),
  details1                  varchar(255),
  details2                  varchar(255),
  details3                  varchar(255),
  camera                    varchar(255),
  display                   varchar(255),
  ram                       varchar(255),
  langue                    varchar(255),
  brand_id                  varchar(255),
  constraint pk_tbProductPhone primary key (id))
;

create table tbUser (
  id                        varchar(255) not null,
  name                      varchar(255),
  lastname                  varchar(255),
  age                       varchar(255),
  address                   varchar(255),
  tel                       varchar(255),
  username                  varchar(255),
  password                  varchar(255),
  position                  varchar(255),
  constraint pk_tbUser primary key (id))
;

alter table tbOrders add constraint fk_tbOrders_ulogin_1 foreign key (ulogin_id) references tbUser (id) on delete restrict on update restrict;
create index ix_tbOrders_ulogin_1 on tbOrders (ulogin_id);
alter table tbOrdersDetail add constraint fk_tbOrdersDetail_orders_2 foreign key (orders_id) references tbOrders (id) on delete restrict on update restrict;
create index ix_tbOrdersDetail_orders_2 on tbOrdersDetail (orders_id);
alter table tbOrdersDetail add constraint fk_tbOrdersDetail_product_3 foreign key (product_id) references tbProductPhone (id) on delete restrict on update restrict;
create index ix_tbOrdersDetail_product_3 on tbOrdersDetail (product_id);
alter table tbProductPhone add constraint fk_tbProductPhone_brand_4 foreign key (brand_id) references tbBarnd (id) on delete restrict on update restrict;
create index ix_tbProductPhone_brand_4 on tbProductPhone (brand_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table tbBarnd;

drop table tbOrders;

drop table tbOrdersDetail;

drop table tbProductNews;

drop table tbProductPhone;

drop table tbUser;

SET FOREIGN_KEY_CHECKS=1;

