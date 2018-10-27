INSERT INTO pais (nome, sigla) VALUES ('China', 'CHN');
INSERT INTO pais (nome, sigla) VALUES ('Argentina', 'ARG');
INSERT INTO pais (nome, sigla) VALUES ('Uruguai', 'URU');

INSERT INTO estado (nome, sigla, pais_id) VALUES ('Guangzhou', 'GUA', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Foshan', 'FOS', 1);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Buenos Aires', 'BUA', 2);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Salta', 'SAL', 2);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Montevideo', 'MON', 3);
INSERT INTO estado (nome, sigla, pais_id) VALUES ('Minas', 'MIN', 3);

INSERT INTO cidade (nome, estado_id) VALUES ('Montevideo', 5);
INSERT INTO cidade (nome, estado_id) VALUES ('Minas', 6);