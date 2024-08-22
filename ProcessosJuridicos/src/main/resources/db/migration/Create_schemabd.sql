
CREATE TABLE processo (
    id SERIAL PRIMARY KEY,
    numero VARCHAR(20) NOT NULL
);

-- Criação da tabela "reu"
CREATE TABLE reu (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf CHAR(11) NOT NULL UNIQUE
);

-- Criação da tabela de relacionamento "processo_reu"
CREATE TABLE processo_reu (
    processo_id INT NOT NULL,
    reu_id INT NOT NULL,
    PRIMARY KEY (processo_id, reu_id),
    FOREIGN KEY (processo_id) REFERENCES processo(id) ON DELETE CASCADE,
    FOREIGN KEY (reu_id) REFERENCES reu(id) ON DELETE CASCADE
);
