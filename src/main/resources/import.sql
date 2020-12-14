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

insert into forma_pagamento (descricao) values ('Cartão de credito');
insert into forma_pagamento (descricao) values ('Cartão de débito');
insert into forma_pagamento (descricao) values ('Dinheiro');
insert into forma_pagamento (descricao) values ('Bitcoin');
insert into forma_pagamento (descricao) values ('PayPal');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Rio de janeiro', 4);
insert into cidade (id, nome, estado_id) values (5, 'Manáus', 5);

insert into restaurante(nome, taxa_frete, cozinha_id, endereco_cidade_id, end_cep, end_logradouro, end_numero, end_complemento, end_bairro) values ('Bela vista', 19.90, 1, 1, '28390-230', 'Rua do contorno', '279', 'Bar do throl', 'Indústrial');
insert into restaurante(nome, taxa_frete, cozinha_id, endereco_cidade_id, end_cep, end_logradouro, end_numero, end_complemento, end_bairro) values ('Porto velho', 15.90, 2, 2, '56895-050', 'Av das industrias', '253', 'Cemig', 'Canoas');
insert into restaurante(nome, taxa_frete, cozinha_id, endereco_cidade_id, end_cep, end_logradouro, end_numero, end_complemento, end_bairro) values ('Fogão a lenha', 9.50, 3, 5, '21056-451', 'Av Rondom', '1048', 'Caixa', 'Jardins');
insert into restaurante(nome, taxa_frete, cozinha_id, endereco_cidade_id, end_cep, end_logradouro, end_numero, end_complemento, end_bairro) values ('Casa do rango', 0.0, 5, 3, '10254-010', 'Rua dos tamoios', '1071', 'Burguer King', 'Centro');
insert into restaurante(nome, taxa_frete, cozinha_id, endereco_cidade_id, end_cep, end_logradouro, end_numero, end_complemento, end_bairro) values ('Rest food', 13.45, 4, 4, '12045-214', 'Av Palmares', '896', 'Kaldas Lacc', 'Centro');

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




