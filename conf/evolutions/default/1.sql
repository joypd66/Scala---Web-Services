# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table lookup (
  date_stamp                varchar(255) not null,
  username                  varchar(255),
  stock_ticker              varchar(255),
  stock_price               float,
  constraint pk_lookup primary key (date_stamp))
;

create table user (
  username                  varchar(255) not null,
  password                  varchar(255),
  constraint pk_user primary key (username))
;

create sequence lookup_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists lookup;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists lookup_seq;

drop sequence if exists user_seq;

