create table cidade (
id bigint not null auto_increment, 
nome varchar(60) not null, 
estado_id bigint not null, 
primary key (id)
) engine=InnoDB default charset=utf8;

create table cozinha (
id bigint not null auto_increment, 
nome varchar(60) not null, 
primary key (id)
) engine=InnoDB default charset=utf8;

create table estado (
id bigint not null auto_increment, 
nome varchar(30) not null, 
primary key (id)
) engine=InnoDB default charset=utf8;

create table forma_pagamento (
id bigint not null auto_increment, 
descricao varchar(60) not null, 
primary key (id)
) engine=InnoDB default charset=utf8;

create table grupo (
id bigint not null auto_increment, 
nome varchar(30) not null, 
primary key (id)
) engine=InnoDB default charset=utf8;

create table grupo_permicoes (
grupo_id bigint not null, 
permicoes_id bigint not null
) engine=InnoDB default charset=utf8;

create table permicao (
id bigint not null auto_increment, 
descricão varchar(60), 
nome varchar(30), 
primary key (id)
) engine=InnoDB default charset=utf8;

create table produto (
id bigint not null auto_increment, 
ativo bit not null, 
descricao varchar(80) not null, 
nome varchar(30) not null, 
preco decimal(5,2) not null, 
restaurante_id bigint not null, 
primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurante (
id bigint not null auto_increment, 
data_atualizacao datetime not null, 
data_criacao datetime not null, 
end_bairro varchar(30), 
end_cep varchar(11), 
end_complemento varchar(30), 
end_logradouro varchar(30), 
end_numero varchar(6), 
nome varchar(30) not null, 
taxa_frete double precision not null, 
cozinha_id bigint not null, 
endereco_cidade_id bigint, 
primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurante_formas_pagamento (
restaurante_id bigint not null, 
formas_pagamento_id bigint not null
) engine=InnoDB default charset=utf8;

create table usuario (
id bigint not null auto_increment, 
data_cadrastro datetime not null, 
email varchar(120) not null, 
nome varchar(60) not null, 
senha varchar(20) not null, 
primary key (id)
) engine=InnoDB default charset=utf8;

create table usuario_grupos (
usuario_id bigint not null,
grupos_id bigint not null
) engine=InnoDB default charset=utf8;

alter table cidade add constraint FK_cidade_estado foreign key (estado_id) references estado (id);
alter table grupo_permicoes add constraint FK_grupo_permicao foreign key (permicoes_id) references permicao (id);
alter table grupo_permicoes add constraint FK_permicao_grupo foreign key (grupo_id) references grupo (id);
alter table produto add constraint FK_produto_restaurante foreign key (restaurante_id) references restaurante (id);
alter table restaurante add constraint FK_restaurante_cozinha foreign key (cozinha_id) references cozinha (id);
alter table restaurante add constraint FK_restaurante_cidade foreign key (endereco_cidade_id) references cidade (id);
alter table restaurante_formas_pagamento add constraint FK_restaurante_formaPagamento foreign key (formas_pagamento_id) references forma_pagamento (id);
alter table restaurante_formas_pagamento add constraint FK_formaPagamento_restaurante foreign key (restaurante_id) references restaurante (id);
alter table usuario_grupos add constraint FK_usuario_grupo foreign key (grupos_id) references grupo (id);
alter table usuario_grupos add constraint FK_grupo_usuario foreign key (usuario_id) references usuario (id);
