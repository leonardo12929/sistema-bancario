ALTER TABLE contabancaria
RENAME COLUMN titular TO apelido;

ALTER TABLE contabancaria
ADD COLUMN titularId BIGINT;


CREATE TABLE cliente (
    id BIGINT auto_increment NOT NULL,
    nome varchar(100) NOT NULL,
    cpf CHAR(11) NOT NULL,
    email varchar(200) NOT NULL,
    telefone varchar(15) NOT NULL,
    dataNascimento DATE NOT NULL,
    dataCadastro DATETIME NOT NULL,
    endereco varchar(100) NOT NULL,

    PRIMARY KEY (id)
);


ALTER TABLE contabancaria 
ADD CONSTRAINT fk_conta_titular
FOREIGN KEY (titularId)
REFERENCES cliente(id);
