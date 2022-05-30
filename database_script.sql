create database dborder;

drop type if exists status;

create type status as enum ('EM_ANDAMENTO', 'CONCLUIDO');

create table tb_order (
	order_id serial not null primary key,
    user_id int not null,
    order_total double precision not null,
    order_products text not null,
    order_date date not null,
    order_status status not null default 'EM_ANDAMENTO'
);