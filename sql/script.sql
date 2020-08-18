CREATE DATABASE ecommerce;
USE ecommerce;

------------| TABELAS |---------------

CREATE TABLE cliente(
	id smallint not null,
	nome varchar (50) not null,
	email varchar (50) not null,
	login varchar (16) not null,
	senha varchar (255) not null,
	rua varchar (255) not null,
	numero varchar (10) not null,
	cidade varchar (255) not null,
	estado varchar (100) not null,
	cep varchar(20) not null
);


CREATE TABLE administrador(
	id smallint not null,
	nome varchar (50) not null,
	email varchar (50) not null,
	login varchar (16) not null,
	senha varchar (255) not null
);

CREATE TABLE produto(
	id smallint not null,
	descricao varchar (50) not null,
	preco double precision not null,
	quantidade smallint not null,
	foto varchar (255) not null
);

CREATE TABLE categoria(
	id smallint not null,
	descricao varchar (50) not null
);

CREATE TABLE compra(
	id smallint not null,
	data_hora Date not null,
	id_cliente smallint not null
);

CREATE TABLE compra_produto(
	id_produto smallint not null,
	id_compra smallint not null,
	quantidade_compra smallint not null
);

CREATE TABLE produto_categoria(
	id_produto smallint not null,
	id_categoria smallint not null
);

------------| RESTRIÇÕES |---------------

ALTER TABLE cliente
	ADD CONSTRAINT pk_cliente
		PRIMARY KEY (id);

ALTER TABLE administrador
	ADD CONSTRAINT pk_administrador
		PRIMARY KEY (id);

ALTER TABLE produto
	ADD CONSTRAINT pk_produto 
		PRIMARY KEY (id);

ALTER TABLE categoria
	ADD CONSTRAINT pk_categoria 
		PRIMARY KEY (id);

ALTER TABLE compra
	ADD CONSTRAINT pk_compra 
		PRIMARY KEY (id);

ALTER TABLE cliente
	ADD CONSTRAINT un_email_cliente
		UNIQUE (email);

ALTER TABLE cliente
	ADD CONSTRAINT un_login_cliente
		UNIQUE (login);

ALTER TABLE administrador
	ADD CONSTRAINT un_email_administrador
		UNIQUE (email);

ALTER TABLE administrador
	ADD CONSTRAINT un_login_administrador
		UNIQUE (login);

ALTER TABLE compra
	ADD CONSTRAINT fk_id_compra
		FOREIGN KEY (id_cliente) REFERENCES cliente
			ON DELETE NO ACTION
			ON UPDATE CASCADE;

ALTER TABLE compra_produto
	ADD CONSTRAINT fk_compra_produto_compra
		FOREIGN KEY (id_compra) REFERENCES compra
			ON DELETE NO ACTION
			ON UPDATE CASCADE;

ALTER TABLE compra_produto
	ADD CONSTRAINT fk_compra_produto_produto
		FOREIGN KEY (id_produto) REFERENCES produto
			ON DELETE NO ACTION
			ON UPDATE CASCADE;

ALTER TABLE produto_categoria
	ADD CONSTRAINT fk_produto_categoria_produto
		FOREIGN KEY (id_produto) REFERENCES produto
			ON DELETE NO ACTION
			ON UPDATE CASCADE;

ALTER TABLE produto_categoria
	ADD CONSTRAINT fk_produto_categoria_categoria
		FOREIGN KEY (id_categoria) REFERENCES categoria
			ON DELETE NO ACTION
			ON UPDATE CASCADE;

ALTER TABLE compra_produto
	ADD CONSTRAINT pk_compra_produto 
		PRIMARY KEY (id_produto, id_compra);

ALTER TABLE produto_categoria
	ADD CONSTRAINT pk_produto_categoria
		PRIMARY KEY (id_produto, id_categoria);