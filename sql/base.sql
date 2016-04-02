create table cliente (
  id int unsigned auto_increment not null,
  nome varchar(50) not null,
  cpf varchar(14) not null,
  email varchar(50),
  telefone varchar(12),
  obs varchar(500),
  date_created timestamp default now(),
  primary key (id),
  constraint uc_cliente unique (id,cpf)
);

create table usuario (
  id int unsigned auto_increment not null,
  nome varchar(50) not null,
  cpf varchar(14) not null,
  email varchar(50),
  telefone varchar(12),
  obs varchar(500),
  date_created timestamp default now(),
  ativo boolean not null default true,
  perfil varchar(5) not null,
  senha varchar(50) not null,
  primary key (id),
  constraint uc_usuario unique (id,cpf)
);