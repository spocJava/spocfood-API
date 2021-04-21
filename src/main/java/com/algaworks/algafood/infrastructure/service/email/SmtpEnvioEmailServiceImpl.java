package com.algaworks.algafood.infrastructure.service.email;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.services.EnvioEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;

public class SmtpEnvioEmailServiceImpl implements EnvioEmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private EmailProperties emailProperties;
    @Autowired
    private Configuration configuration;


    @Override
    public void enviarEmail(Menssagem menssagem) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            String conteudo = montarTamplateDoEmail(menssagem);

            helper.setFrom(emailProperties.getRemetente());
            helper.setTo(menssagem.getDestinatarios().toArray(new String[0]));
            helper.setSubject(menssagem.getAssunto());
            helper.setText(conteudo, true);
        } catch (Exception ex) {
            throw new EmailException("Não foi possivél enviar o e-mail", ex);
        }
        javaMailSender.send(mimeMessage);
    }


    protected String montarTamplateDoEmail(Menssagem menssagem){
        try {
            Template template = configuration.getTemplate(menssagem.getConteudo());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, menssagem.getVariaveis());

        } catch (Exception ex) {
            throw new EmailException("Não foi possivél montar o template do e-mail", ex);
        }
    }
}
