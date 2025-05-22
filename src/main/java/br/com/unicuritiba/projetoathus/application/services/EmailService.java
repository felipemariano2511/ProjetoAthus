package br.com.unicuritiba.projetoathus.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import java.util.Map;
import org.springframework.core.io.ClassPathResource;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String remetente;

    public void enviarEmailComTemplate(String destinatario, String assunto, String templateNome, Map<String, Object> variaveis) {
        try {
            MimeMessage mensagem = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true, "UTF-8");

            Context context = new Context();
            context.setVariables(variaveis);

            String html = templateEngine.process(templateNome, context);

            helper.setFrom(remetente);
            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(html, true);

            ClassPathResource imagem = new ClassPathResource("images/logo_Athus.png");
            helper.addInline("logoAthus", imagem);

            mailSender.send(mensagem);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar e-mail: " + e.getMessage(), e);
        }
    }
}

