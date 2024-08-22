INSERT INTO processo (numero) VALUES ('123456');
INSERT INTO processo (numero) VALUES ('789101');


INSERT INTO reu (nome, cpf) VALUES ('João Silva', '12345678901');
INSERT INTO reu (nome, cpf) VALUES ('Maria Oliveira', '98765432100');
INSERT INTO reu (nome, cpf) VALUES ('Carlos Pereira', '19283746509');
INSERT INTO reu (nome, cpf) VALUES ('Ana Souza', '56473829101');


INSERT INTO processo_reu (processo_id, reu_id) VALUES (1, 1);
INSERT INTO processo_reu (processo_id, reu_id) VALUES (1, 2);
INSERT INTO processo_reu (processo_id, reu_id) VALUES (2, 3);
INSERT INTO processo_reu (processo_id, reu_id) VALUES (2, 4);