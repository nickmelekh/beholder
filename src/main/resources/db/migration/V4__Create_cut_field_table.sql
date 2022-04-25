create table cust_char_field (
     id int8 not null,
     product_id int8 not null,
     valid_from_dttm timestamp not null,
     fied_name varchar(255) not null,
     fied_value varchar(255) not null,
     primary key (id, product_id, valid_from_dttm, fied_name)
);