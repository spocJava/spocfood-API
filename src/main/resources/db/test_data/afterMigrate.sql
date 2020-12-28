set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permicoes;
delete from permicao;
delete from produto;
delete from restaurante;
delete from restaurante_formas_pagamento;
delete from usuario;
delete from usuario_grupos;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permicao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;

insert into cozinha (nome) values ('Mineira');
insert into cozinha (nome) values ('Bahiana');
insert into cozinha (nome) values ('Cearense');
insert into cozinha (nome) values ('Goiana');
insert into cozinha (nome) values ('Paulistana');

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');
insert into estado (id, nome) values (4, 'Rio de janeiro');
insert into estado (id, nome) values (5, 'Amazonas');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Rio de janeiro', 4);
insert into cidade (id, nome, estado_id) values (5, 'Manáus', 5);

insert into forma_pagamento (descricao) values ('Cartão de credito');
insert into forma_pagamento (descricao) values ('Cartão de débito');
insert into forma_pagamento (descricao) values ('Dinheiro');
insert into forma_pagamento (descricao) values ('Bitcoin');
insert into forma_pagamento (descricao) values ('PayPal');

insert into restaurante(nome, taxa_frete, cozinha_id, data_criacao, data_atualizacao, endereco_cidade_id, end_cep, end_logradouro, end_numero, end_complemento, end_bairro) values ('Bela vista', 19.90, 1, utc_timestamp, utc_timestamp, 1, '28390-230', 'Rua do contorno', '279', 'Bar do throl', 'Indústrial');
insert into restaurante(nome, taxa_frete, cozinha_id, data_criacao, data_atualizacao, endereco_cidade_id, end_cep, end_logradouro, end_numero, end_complemento, end_bairro) values ('Porto velho', 15.90, 2, utc_timestamp, utc_timestamp, 2, '56895-050', 'Av das industrias', '253', 'Cemig', 'Canoas');
insert into restaurante(nome, taxa_frete, cozinha_id, data_criacao, data_atualizacao, endereco_cidade_id, end_cep, end_logradouro, end_numero, end_complemento, end_bairro) values ('Fogão a lenha', 9.50, 3, utc_timestamp, utc_timestamp, 5, '21056-451', 'Av Rondom', '1048', 'Caixa', 'Jardins');
insert into restaurante(nome, taxa_frete, cozinha_id, data_criacao, data_atualizacao, endereco_cidade_id, end_cep, end_logradouro, end_numero, end_complemento, end_bairro) values ('Casa do rango', 0.0, 5, utc_timestamp, utc_timestamp, 3, '10254-010', 'Rua dos tamoios', '1071', 'Burguer King', 'Centro');
insert into restaurante(nome, taxa_frete, cozinha_id, data_criacao, data_atualizacao, endereco_cidade_id, end_cep, end_logradouro, end_numero, end_complemento, end_bairro) values ('Rest food', 13.45, 4, utc_timestamp, utc_timestamp, 4, '12045-214', 'Av Palmares', '896', 'Kaldas Lacc', 'Centro');

insert into restaurante_formas_pagamento (restaurante_id, formas_pagamento_id) values (1, 1), (1, 2), (1, 3), (1, 4), (1, 5);
insert into restaurante_formas_pagamento (restaurante_id, formas_pagamento_id) values (2, 1), (2, 2), (2, 3), (2, 4), (2, 5);
insert into restaurante_formas_pagamento (restaurante_id, formas_pagamento_id) values (3, 1), (3, 2), (3, 3), (3, 4), (3, 5);
insert into restaurante_formas_pagamento (restaurante_id, formas_pagamento_id) values (4, 1), (4, 2), (4, 4), (4, 5);
insert into restaurante_formas_pagamento (restaurante_id, formas_pagamento_id) values (5, 1), (5, 2), (5, 4), (5, 5);

insert into permicao (descricão, nome) values ('Fucionario-vendas', 'PFVO1');
insert into permicao (descricão, nome) values ('Gestor-almoxerifado', 'PGAO1');
insert into permicao (descricão, nome) values ('Garçon', 'PGAO1');
insert into permicao (descricão, nome) values ('Somelier', 'PSOA1');
insert into permicao (descricão, nome) values ('Rosther', 'PRO34');

insert into produto (ativo, descricao, nome, preco, restaurante_id) values (1, 'Deliciosa carne suína ao molho especial', 'Porco com molho agridoce', 78.90, 1);
insert into produto (ativo, descricao, nome, preco, restaurante_id) values (1, 'Delicioso Hamburques com creme cheder x', 'Hamburquer cheder XXX', 26.90, 2);
insert into produto (ativo, descricao, nome, preco, restaurante_id) values (1, 'Prato italiano tipico dos bohemios dster', 'Aggrite de suar foloy', 56.90, 3);
insert into produto (ativo, descricao, nome, preco, restaurante_id) values (1, 'Picanha em forma de cubos sucolentos', 'Picanha suitê-managno', 78.90, 4);
insert into produto (ativo, descricao, nome, preco, restaurante_id) values (1, 'Salmão ao molho de espargos do mar e molho sate', 'Salamão crustado', 68.50, 5);
insert into produto (ativo, descricao, nome, preco, restaurante_id) values (1, 'Deliciosa carne bovina ao alho peró', 'Caxilhe de carne', 112.90, 1);
insert into produto (ativo, descricao, nome, preco, restaurante_id) values (1, 'Saboroso picanha ao churrasco pratê', 'Churrasquinho pratê', 45.70, 2);
insert into produto (ativo, descricao, nome, preco, restaurante_id) values (1, 'Deliciosa picanha suína ao molho especial', 'Porco com alho', 89.90, 3);
insert into produto (ativo, descricao, nome, preco, restaurante_id) values (1, 'Deliciosa pizza alemã acuwester', 'Pizza wester', 36.90, 4);
insert into produto (ativo, descricao, nome, preco, restaurante_id) values (1, 'Saboroso medalhão de alcatra', 'Medalhão Bovino', 98.90, 5);