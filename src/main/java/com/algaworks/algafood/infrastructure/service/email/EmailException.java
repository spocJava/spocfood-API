package com.algaworks.algafood.infrastructure.service.email;

public class EmailException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public EmailException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
