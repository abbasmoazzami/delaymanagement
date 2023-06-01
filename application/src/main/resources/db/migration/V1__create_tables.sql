create table if not exists vendors
(
    id int unsigned auto_increment primary key not null
);

create table if not exists agents
(
    id int unsigned auto_increment primary key not null
);

create table if not exists trips
(
    id       int unsigned auto_increment primary key not null,
    order_id int unique                              not null,
    `status` varchar(60)                             not null
);

create table if not exists orders
(
    id            int unsigned auto_increment primary key not null,
    created_at    datetime(6)                             not null,
    updated_at    datetime(6)                             not null,

    vendor_id     int unsigned                            not null,
    time_delivery int                                     not null,

    constraint fk_orders_vendor_id foreign key (vendor_id) references vendors (id)
);

create table if not exists delay_reports
(
    id         int unsigned auto_increment primary key not null,
    created_at datetime(6)                             not null,
    updated_at datetime(6)                             not null,

    order_id   int unsigned                            not null,
    agent_id   int unsigned                            null,
    delay_time int                                     null,
    status     varchar(60)                             not null,

    constraint fk_delay_reports_order_id foreign key (order_id) references orders (id)
);