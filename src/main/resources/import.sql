insert into cozinha (nome) values ('Mineira');
insert into cozinha (nome) values ('Bahiana');

insert into restaurante(nome, taxa_frete, cozinha_id) values ('Bela vista', 19.90, 1);
insert into restaurante(nome, taxa_frete, cozinha_id) values ('Porto velho', 19.90, 2);

insert into forma_pagamento (descricao) values ('Cartão de credito');
insert into forma_pagamento (descricao) values ('Vale alimentação');

insert into permicao (descricão, nome) values ('Fucionario-vendas', 'PFVO1');
insert into permicao (descricão, nome) values ('Gestor-almoxerifado', 'PGAO1');

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);