package com.algaworks.algafood.infrastructure.service.email;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEnvioEmailServiceImpl extends SmtpEnvioEmailServiceImpl{

   @Override
   public void enviarEmail(Menssagem menssagem) {
       String conteudo = montarTamplateDoEmail(menssagem);
       log.info("[ENVIANDO E-MAIL FAKE] para -> {}\n{}", menssagem.getDestinatarios(), conteudo);
   }

}
