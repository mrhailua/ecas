package com.ecas.web.admin.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

@Component
@FacesValidator("validateEmailValidator")
public class ValidateEmailValidator extends BaseValidator implements Validator {
	private String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value != null && StringUtils.isNotBlank((String) value)) {
			if (!Pattern.matches(EMAIL_REGEX, (String) value)) {
				FacesMessage facesMsg = new FacesMessage(
				        getStringMessage("leave.store.datatable.column.input.email.invalid.msg"), null);
				facesMsg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(facesMsg);
			}
		}
	}

}
