CREATE TABLE trasacao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    idContaOrigem BIGINT NULL,
    idContaDestino BIGINT NULL,

    tipo VARCHAR(30) NOT NULL,
    status VARCHAR(20) NOT NULL,
    
    valor DECIMAL (15,2) NOT NULL,

    dataTransacao DATETIME NOT NULL,

    FOREIGN KEY (idContaOrigem) REFERENCES contabancaria(id),
    FOREIGN KEY (idContaDestino) REFERENCES contabancaria(id)
)