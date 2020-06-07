drop table ingredients;
drop table products;
drop table clients;
drop table orders;
drop table order_to_product;
drop table custom_to_order;

create table if not exists ingredients (
id varchar(4) not null,
name varchar(20) not null,
type varchar(10) not null,
price varchar(10) not null
);

create table if not exists products (
id serial,
name varchar(32) not null,
price varchar(8) not null,
ingredients varchar(256) not null,
description varchar(256) not null,
image varchar(256) not null
);

create table if not exists clients (
id serial,
name varchar(32) not null,
lastname varchar(32) not null,
mail varchar(32) not null,
phone varchar(12) not null,
address varchar(64) not null,
cardnumber varchar(16) not null
);

create table if not exists orders (
id serial,
clientId varchar(16) not null,
customId varchar(16) not null
);

create table if not exists order_to_product (
id serial,
orderId varchar(16) not null,
productId varchar(16) not null,
amount varchar(16) not null
);

create table if not exists custom_to_order (
id serial,
orderId varchar(16) not null,
ingredientId varchar(16) not null,
pieId varchar(16) not null
);