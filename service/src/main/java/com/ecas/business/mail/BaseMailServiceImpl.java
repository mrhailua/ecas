package com.ecas.business.mail;

import com.ecas.service.MailService;
import freemarker.template.Template;
import org.springframework.scheduling.annotation.Async;

import java.io.StringWriter;
import java.util.Map;

/**
 * Created by LENOVO on 5/28/2016.
 */
public abstract class BaseMailServiceImpl {
    private Template subjectTemplate;
    private Template bodyTemplate;
    private MailService emailService;

    public BaseMailServiceImpl() {
        //Default constructor
    }

    public BaseMailServiceImpl(Template bodyTemplate, Template subjectTemplate, MailService emailService) {
        this.bodyTemplate = bodyTemplate;
        this.subjectTemplate = subjectTemplate;
        this.emailService = emailService;
    }

    @Async
    protected void send(String to, Map<String, Object> templateModel) throws Exception {
        this.emailService.sendMail(to, processSubject(templateModel), processBody(templateModel));
    }

    private String processBody(Map<String, Object> templateModel) throws Exception {
        StringWriter contentWriter = new StringWriter();
        bodyTemplate.process(templateModel, contentWriter);

        return contentWriter.toString();
    }

    private String processSubject(Map<String, Object> templateModel) throws Exception {
        StringWriter contentWriter = new StringWriter();
        subjectTemplate.process(templateModel, contentWriter);

        return contentWriter.toString();
    }

    public void setBodyTemplate(Template bodyTemplate) {
        this.bodyTemplate = bodyTemplate;
    }

    public void setSubjectTemplate(Template subjectTemplate) {
        this.subjectTemplate = subjectTemplate;
    }

    public MailService getEmailService() {
        return emailService;
    }

    public void setEmailService(MailService emailService) {
        this.emailService = emailService;
    }
}
