create table cidades (
	id_cidade int not null,
	nome varchar(40) not null,
	estado char(2) not null,
	
	constraint pk_cidade primary key (id_cidade)
);

create sequence seq_cidade_id;
