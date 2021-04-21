package com.algaworks.algafood.core.configurations;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.services.EnvioEmailService;
import com.algaworks.algafood.infrastructure.service.email.FakeEnvioEmailServiceImpl;
import com.algaworks.algafood.infrastructure.service.email.SmtpEnvioEmailServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService(){

        switch(emailProperties.getImpl()){

            case FAKE:
                return new FakeEnvioEmailServiceImpl();
            case SMTP:
                return new SmtpEnvioEmailServiceImpl();
            default:
                return null;
        }
    }
    
}
