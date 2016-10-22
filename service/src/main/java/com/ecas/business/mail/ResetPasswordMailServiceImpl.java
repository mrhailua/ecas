package com.ecas.business.mail;

import com.ecas.domain.User;
import com.ecas.service.MailService;
import freemarker.template.Template;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ResetPasswordMailServiceImpl extends BaseMailServiceImpl
         {
    private static final long serialVersionUID = 1638720132809435384L;
    static Logger log = Logger.getLogger(ResetPasswordMailServiceImpl.class.getName());

    public ResetPasswordMailServiceImpl(Template subjectTemplate, Template bodyTemplate, MailService emailService) {
        super(subjectTemplate, bodyTemplate, emailService);
    }

    public void sendResetEmail(User worker) {
        try {
            Map<String, Object> hTemplateVariables = new HashMap<String, Object>();
            hTemplateVariables.put("worker", worker);

            send(worker.getEmail(), hTemplateVariables);
        } catch (Exception ex) {
            log.error("Email notify user fail", ex);
        }
    }
}
