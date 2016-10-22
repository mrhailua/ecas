package com.ecas.business;

import freemarker.template.Template;

/**
 * Created by LENOVO on 5/28/2016.
 */
public interface EmailServiceRegistry {

    EmailNotificationService getEmailNotificationService(Template subjectTemplate, Template bodyTemplate);

}
