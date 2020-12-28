create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permicoes (grupo_id bigint not null, permicoes_id bigint not null) engine=InnoDB
create table item_pedido (id bigint not null auto_increment, observacao varchar(255), preco_total decimal(19,2), preco_unitario decimal(19,2), quantidade integer, pedido_id bigint not null, produto_id bigint not null, primary key (id)) engine=InnoDB
create table pedido (id bigint not null auto_increment, data_cancelamento datetime(6), data_confirmacao datetime(6), data_criacao datetime(6), data_entrega datetime(6), end_bairro varchar(255), end_cep varchar(255), end_complemento varchar(255), end_logradouro varchar(255), end_numero varchar(255), status integer, sub_total decimal(19,2), taxa_frete decimal(19,2), valor_total decimal(19,2), usuario_cliente_id bigint not null, endereco_cidade_id bigint, forma_pagamento_id bigint not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table permicao (id bigint not null auto_increment, descricão varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255) not null, nome varchar(255) not null, preco decimal(19,2) not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_criacao datetime not null, end_bairro varchar(255), end_cep varchar(255), end_complemento varchar(255), end_logradouro varchar(255), end_numero varchar(255), nome varchar(255) not null, taxa_frete double precision not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_formas_pagamento (restaurante_id bigint not null, formas_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadrastro datetime not null, email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupos (usuario_id bigint not null, grupos_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permicoes add constraint FKgi15srpgjypkwblle8shbjgl0 foreign key (permicoes_id) references permicao (id)
alter table grupo_permicoes add constraint FK5xkbp03ad0238q6o1v0hnob9h foreign key (grupo_id) references grupo (id)
alter table item_pedido add constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido (id)
alter table item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto (id)
alter table pedido add constraint FKcccmjvm9ytuxbe00h3wmtm77y foreign key (usuario_cliente_id) references usuario (id)
alter table pedido add constraint FKk987vfg9cpgx7qxj3166fdqig foreign key (endereco_cidade_id) references cidade (id)
alter table pedido add constraint FKqaa411xsl0xu4tkvt1wpccd3b foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table pedido add constraint FK3eud5cqmgsnltyk704hu3qj71 foreign key (restaurante_id) references restaurante (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_formas_pagamento add constraint FKmif0s0aywu1c01qhmkt8ln3hu foreign key (formas_pagamento_id) references forma_pagamento (id)
alter table restaurante_formas_pagamento add constraint FK7uj3ol5o5h1jpn780cxtl3tvb foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupos add constraint FK92ff1v8fkwig9tqv9bk4nvi0t foreign key (grupos_id) references grupo (id)
alter table usuario_grupos add constraint FK158r9y55ufwykh675ddt8kb43 foreign key (usuario_id) references usuario (id)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permicoes (grupo_id bigint not null, permicoes_id bigint not null) engine=InnoDB
create table item_pedido (id bigint not null auto_increment, observacao varchar(255), preco_total decimal(19,2), preco_unitario decimal(19,2), quantidade integer, pedido_id bigint not null, produto_id bigint not null, primary key (id)) engine=InnoDB
create table pedido (id bigint not null auto_increment, data_cancelamento datetime(6), data_confirmacao datetime(6), data_criacao datetime(6), data_entrega datetime(6), end_bairro varchar(255), end_cep varchar(255), end_complemento varchar(255), end_logradouro varchar(255), end_numero varchar(255), status integer, sub_total decimal(19,2), taxa_frete decimal(19,2), valor_total decimal(19,2), usuario_cliente_id bigint not null, endereco_cidade_id bigint, forma_pagamento_id bigint not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table permicao (id bigint not null auto_increment, descricão varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255) not null, nome varchar(255) not null, preco decimal(19,2) not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime not null, data_criacao datetime not null, end_bairro varchar(255), end_cep varchar(255), end_complemento varchar(255), end_logradouro varchar(255), end_numero varchar(255), nome varchar(255) not null, taxa_frete double precision not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_formas_pagamento (restaurante_id bigint not null, formas_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadrastro datetime not null, email varchar(255) not null, nome varchar(255) not null, senha varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupos (usuario_id bigint not null, grupos_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permicoes add constraint FKgi15srpgjypkwblle8shbjgl0 foreign key (permicoes_id) references permicao (id)
alter table grupo_permicoes add constraint FK5xkbp03ad0238q6o1v0hnob9h foreign key (grupo_id) references grupo (id)
alter table item_pedido add constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido (id)
alter table item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto (id)
alter table pedido add constraint FKcccmjvm9ytuxbe00h3wmtm77y foreign key (usuario_cliente_id) references usuario (id)
alter table pedido add constraint FKk987vfg9cpgx7qxj3166fdqig foreign key (endereco_cidade_id) references cidade (id)
alter table pedido add constraint FKqaa411xsl0xu4tkvt1wpccd3b foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table pedido add constraint FK3eud5cqmgsnltyk704hu3qj71 foreign key (restaurante_id) references restaurante (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_formas_pagamento add constraint FKmif0s0aywu1c01qhmkt8ln3hu foreign key (formas_pagamento_id) references forma_pagamento (id)
alter table restaurante_formas_pagamento add constraint FK7uj3ol5o5h1jpn780cxtl3tvb foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupos add constraint FK92ff1v8fkwig9tqv9bk4nvi0t foreign key (grupos_id) references grupo (id)
alter table usuario_grupos add constraint FK158r9y55ufwykh675ddt8kb43 foreign key (usuario_id) references usuario (id)
