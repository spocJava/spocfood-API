create table restaurante_usuario_responsavel (
usuario_id bigint not null,
restaurante_id bigint not null,
primary key (usuario_id, restaurante_id),
constraint FK_usuario_123 foreign key (usuario_id) references usuario (id),
constraint FK_restaurante_123 foreign key (restaurante_id) references restaurante (id)
) ENGINE=InnoDB default charset=utf8;