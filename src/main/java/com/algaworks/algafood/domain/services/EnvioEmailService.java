package com.algaworks.algafood.domain.services;

import lombok.*;

import java.util.Map;
import java.util.Set;

public interface EnvioEmailService {

    void enviarEmail(Menssagem menssagem);

    @Getter @Setter
    @Builder
    class Menssagem{

        @Singular
        private Set<String> destinatarios;

        @Singular("variavel")
        private Map<String, Object> variaveis;

        @NonNull
        private String assunto;

        @NonNull
        private String conteudo;

    }
}
