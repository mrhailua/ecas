package com.ecas.web.admin.validator.application;

import com.ecas.domain.Application;
import com.ecas.service.ApplicationDataService;
import com.ecas.web.admin.validator.BaseValidator;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.util.MessageFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@Component("applicationNameUnique")
@Scope("request")
public class ApplicationNameUniqueValidator extends BaseValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value != null && StringUtils.isNotBlank((String) value)) {
            if (getApplicationDataService().isNameExisted((String) value)) {
                FacesMessage facesMsg = new FacesMessage(getStringMessage("com.cas.application.form.name.existed",
                        (String) value), null);
                facesMsg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(facesMsg);
            }
        }
    }

    private ApplicationDataService getApplicationDataService() {
        return (ApplicationDataService) getServiceRegistry().getService(Application.class);
    }

    protected String getStringMessage(String messageId, Object... objs) {
        return MessageFactory.getFormattedText(FacesContext.getCurrentInstance().getApplication().getDefaultLocale(), FacesContext
                .getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString(messageId), objs);
    }
}
