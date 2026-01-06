create table usuario (
    id bigint auto_increment not null,
    login varchar (100) not null,
    senha varchar(60) not null,

    primary key (id)
);