create sequence hibernate_sequence start 1 increment 1;

create table product (
     id int8 not null,
     image varchar(255),
     name varchar(255),
     price varchar(255),
     url varchar(255),
     valid_from_dttm timestamp,
     user_id int8,
     primary key (id)
);

create table product_x_user (
    user_id int8 not null,
    product_id int8 not null,
    primary key (product_id, user_id)
);

create table productxuser_version (
    id int8 not null,
    active boolean not null,
    product_id int8,
    user_id int8,
    valid_from_dttm timestamp,
    primary key (id)
);

create table user_role (
    user_id int8 not null,
    roles varchar(255)
);

create table usr (
    id int8 not null,
    activation_code varchar(255),
    active boolean not null,
    email varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id)
);

alter table if exists product
    add constraint FK5bo9autkgrf2nifv6e7deywmk foreign key (user_id) references usr;

alter table if exists product_x_user
    add constraint FK2nhn75ow9qv34jdac8eov9fu7 foreign key (product_id) references product;

alter table if exists product_x_user
    add constraint FK809lr8vemiskamt4ytp4tgb8d foreign key (user_id) references usr;

alter table if exists user_role
    add constraint FKfpm8swft53ulq2hl11yplpr5 foreign key (user_id) references usr;