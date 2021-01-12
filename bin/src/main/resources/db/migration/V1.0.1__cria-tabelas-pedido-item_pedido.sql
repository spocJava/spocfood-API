create table pedido (
id bigint not null auto_increment, 
data_cancelamento datetime(6), 
data_confirmacao datetime(6), 
data_criacao datetime(6) not null, 
data_entrega datetime(6), 
end_bairro varchar(255) not null, 
end_cep varchar(255) not null, 
end_complemento varchar(255), 
end_logradouro varchar(255) not null, 
end_numero varchar(255) not null, 
status integer, 
sub_total decimal(19,2) not null, 
taxa_frete decimal(19,2) not null, 
valor_total decimal(19,2) not null, 
usuario_cliente_id bigint not null, 
endereco_cidade_id bigint, 
forma_pagamento_id bigint not null, 
restaurante_id bigint not null, 
primary key (id),

constraint FK_pedido_endereco_cidade foreign key (endereco_cidade_id) references cidade (id),
constraint FK_pedido_usuario_cliente foreign key (usuario_cliente_id) references usuario (id),
constraint FK_pedido_forma_pegamento foreign key (forma_pagamento_id) references forma_pagamento (id),
constraint FK_pedido_restaurante foreign key (restaurante_id) references restaurante (id)
) engine=InnoDB default charset=utf8;


create table item_pedido (
id bigint not null auto_increment, 
observacao varchar(255), 
preco_total decimal(19,2) not null, 
preco_unitario decimal(19,2) not null, 
quantidade integer not null, 
pedido_id bigint not null, 
produto_id bigint not null,
 
primary key (id),
unique key UK_item_pedido_produto (pedido_id, produto_id),

constraint FK_item_pedido_pedido foreign key (pedido_id) references pedido (id),
constraint FK_item_pedido_produto foreign key (produto_id) references produto (id)
) engine=InnoDB default charset=utf8;