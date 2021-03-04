alter table pedido add column codigo varchar(36) not null after id;
alter table pedido add constraint UK_pedido_codigo unique (codigo);