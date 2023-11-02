create table product_tbl (
       product_code integer generated by default as identity,
        price double,
        product_description varchar(255),
        product_name varchar(255),
        quantity_in_stock integer,
        primary key (product_code)
    )