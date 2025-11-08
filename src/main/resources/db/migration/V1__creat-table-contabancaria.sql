-- database: :memory:
create table contabancaria (
    id bigint auto_increment not null,
    numeroConta varchar (100) not null,
    saldo DECIMAL(10,2) not null,
    titular varchar (100) not null,

    primary key (id)
);