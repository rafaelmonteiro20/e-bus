create table trechos (
	id_trecho int not null,
	id_cidade_origem int not null,
	id_cidade_destino int not null,
	distancia real not null check (distancia > 0),
	
	constraint pk_trecho primary key (id_trecho),
	constraint fk_trechos_cidade_origem foreign key (id_cidade_origem) references cidades (id_cidade),
	constraint fk_trechos_cidade_destino foreign key (id_cidade_destino) references cidades (id_cidade)
);

create sequence seq_trecho_id;
