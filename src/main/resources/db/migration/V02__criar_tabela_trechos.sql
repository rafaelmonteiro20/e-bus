CREATE SEQUENCE trechos_sequence;

CREATE TABLE trechos (
	id_trecho INT NOT NULL,
	id_cidade_origem INT NOT NULL,
	id_cidade_destino INT NOT NULL,
	distancia DECIMAL NOT NULL CHECK (distancia > 0),
	
	CONSTRAINT pk_trechos PRIMARY KEY (id_trecho),
	CONSTRAINT fk_trechos_cidade_origem FOREIGN KEY (id_cidade_origem) REFERENCES cidades (id_cidade),
	CONSTRAINT fk_trechos_cidade_destino FOREIGN KEY (id_cidade_destino) REFERENCES cidades (id_cidade)
);

ALTER TABLE trechos ALTER COLUMN id_trecho SET DEFAULT NEXTVAL('trechos_sequence');
