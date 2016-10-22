package com.ecas.business.mail;

import com.ecas.domain.User;
import com.ecas.service.InviteEmailService;
import com.ecas.service.MailService;
import com.ecas.utils.LocaleHolder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class InviteMailServiceImpl extends BaseMailServiceImpl implements InviteEmailService, InitializingBean, Serializable {
    private static final long serialVersionUID = 1638720132809435384L;
    static Logger log = Logger.getLogger(InviteMailServiceImpl.class.getName());
    private String subjectTemplate;
    private String bodyTemplate;

    private Configuration systemMarkerConfigure;

    @Override
    public void afterPropertiesSet() throws Exception {
        setSubjectTemplate(systemMarkerConfigure.getTemplate(subjectTemplate, LocaleHolder.getLocale()));
        setBodyTemplate(systemMarkerConfigure.getTemplate(bodyTemplate, LocaleHolder.getLocale()));
    }

    public InviteMailServiceImpl() {
        //Default constructor
    }

    public InviteMailServiceImpl(Template subjectTemplate, Template bodyTemplate, MailService emailService) {
        super(subjectTemplate, bodyTemplate, emailService);
    }

    @Override
    public void sendInviteEmail(User worker, User inviteUser) {
        try {
            Map<String, Object> hTemplateVariables = new HashMap<String, Object>();
            hTemplateVariables.put("invitedUser", worker);
            hTemplateVariables.put("inviteBy", inviteUser);

            send(worker.getEmail(), hTemplateVariables);
        } catch (Exception ex) {
            log.error("Email notify user fail", ex);
        }
    }

    public String getSubjectTemplate() {
        return subjectTemplate;
    }

    public void setSubjectTemplate(String subjectTemplate) {
        this.subjectTemplate = subjectTemplate;
    }

    public String getBodyTemplate() {
        return bodyTemplate;
    }

    public void setBodyTemplate(String bodyTemplate) {
        this.bodyTemplate = bodyTemplate;
    }

    public Configuration getSystemMarkerConfigure() {
        return systemMarkerConfigure;
    }

    public void setSystemMarkerConfigure(Configuration systemMarkerConfigure) {
        this.systemMarkerConfigure = systemMarkerConfigure;
    }
}
