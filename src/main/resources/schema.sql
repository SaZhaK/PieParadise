create table if not exists ingredients (
id varchar(4) not null,
name varchar(20) not null,
type varchar(10) not null,
price varchar(10) not null
);

create table if not exists products (
id int not null auto_increment,
name varchar(32) not null,
price varchar(8) not null,
ingredients varchar(256) not null,
description varchar(256) not null,
image varchar(256) not null
);

create table if not exists clients (
id int not null auto_increment,
name varchar(32) not null,
lastname varchar(32) not null,
mail varchar(32) not null,
phone varchar(12) not null,
address varchar(64) not null,
cardnumber varchar(16) not null
);

create table if not exists orders (
id int not null auto_increment,
clientId int not null,
customId int not null
);

create table if not exists order_to_product (
id int not null auto_increment,
orderId int not null,
productId int not null,
amount varchar(16) not null
);

create table if not exists custom_to_order (
id int not null auto_increment,
orderId int not null,
ingredientId int not null,
pieId int not null
);