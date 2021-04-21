package com.algaworks.algafood.core.email;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@Validated
@Component
@ConfigurationProperties("algafood.mail")
public class EmailProperties {

    private Implementacao impl = Implementacao.FAKE;

    @NotNull
    private String remetente;

    public enum Implementacao {
        FAKE, SMTP
    }

}
