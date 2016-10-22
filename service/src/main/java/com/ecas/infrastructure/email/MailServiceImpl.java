package com.ecas.infrastructure.email;

import com.ecas.service.MailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;
import java.io.Serializable;

public class MailServiceImpl implements MailService, Serializable {

    private static final long serialVersionUID = -1722536888184983405L;
    private JavaMailSender mailSender;
    private String defaultSenderEmail;

    public void sendMail(final String to, final String subject, final String msg) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(to);
                message.setFrom(defaultSenderEmail);
                message.setSubject(subject);
                message.setText(msg, true);
            }
        };

        mailSender.send(preparator);
    }

    public String getDefaultSenderEmail() {
        return defaultSenderEmail;
    }

    public void setDefaultSenderEmail(String defaultSenderEmail) {
        this.defaultSenderEmail = defaultSenderEmail;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
}
