CREATE TABLE trasacao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    idContaOrigem BIGINT NULL,
    idContaDestino BIGINT NULL,

    tipo VARCHAR(30) NOT NULL,
    status VARCHAR(20) NOT NULL,
    
    valor DECIMAL (15,2) NOT NULL

    dataTrasacao DATETIME NOT NULL,

    FOREIGN KEY (idContaOrigem) REFERENCES contaBancaria(id),
    FOREIGN KEY (idContaDestino) REFERENCES contaBancaria(id),
)