CREATE SEQUENCE cidades_sequence;

CREATE TABLE cidades (
	id_cidade INT NOT NULL,
	nome VARCHAR(40) NOT NULL,
	estado CHAR(2) NOT NULL,
	
	CONSTRAINT pk_cidades PRIMARY KEY (id_cidade)
);

ALTER TABLE cidades ALTER COLUMN id_cidade SET DEFAULT NEXTVAL('cidades_sequence');
