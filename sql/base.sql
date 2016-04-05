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


INSERT INTO usuario (id, nome, cpf, email, telefone, obs, date_created, ativo, perfil, senha) VALUES
(1, 'Adminstrador', 'adm', '', '', '', '2016-04-03 11:02:23', 1, 'ADM', '81dc9bdb52d04dc20036dbd8313ed055');

INSERT INTO usuario (id, nome, cpf, email, telefone, obs, date_created, ativo, perfil, senha) VALUES
(2, 'Operador', 'operador', '', '', '', '2016-04-03 11:02:23', 1, 'CON', '81dc9bdb52d04dc20036dbd8313ed055');