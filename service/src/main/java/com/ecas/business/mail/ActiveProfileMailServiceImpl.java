package com.ecas.business.mail;

import com.ecas.domain.User;
import com.ecas.service.ActiveProfileEmailService;
import com.ecas.utils.LocaleHolder;
import freemarker.template.Configuration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;

public class ActiveProfileMailServiceImpl extends BaseMailServiceImpl implements ActiveProfileEmailService, InitializingBean {
    static Logger log = Logger.getLogger(ActiveProfileMailServiceImpl.class.getName());
    private static final long serialVersionUID = 1638720132809435384L;

    private Configuration systemMarkerConfigure;
    private String subjectTemplate;
    private String bodyTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        setSubjectTemplate(systemMarkerConfigure.getTemplate(subjectTemplate, LocaleHolder.getLocale()));
        setBodyTemplate(systemMarkerConfigure.getTemplate(bodyTemplate, LocaleHolder.getLocale()));
    }

    @Override
    public void sendActivateEmail(User worker) {
        try {
            Map<String, Object> hTemplateVariables = new HashMap<String, Object>();
            hTemplateVariables.put("name", worker.getName());
            hTemplateVariables.put("mail", worker.getEmail());
            hTemplateVariables.put("code", worker.getActivationCode());

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
