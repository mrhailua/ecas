package com.ecas.web.admin.validator;

import com.ecas.domain.User;
import com.ecas.service.UserDataService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@Component
@FacesValidator("validateEmailUniqueValidator")
public class ValidateEmailUniqueValidator extends BaseValidator implements Validator {
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value != null && StringUtils.isNotBlank((String) value)) {
			User user = getUserDataService().getUserByEmail((String) value);
			// User email is existed
			if (user != null) {
				FacesMessage facesMsg = new FacesMessage(getStringMessage("com.cas.form.email.existed",
				        user.getEmail()), null);
				facesMsg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(facesMsg);
			}
		}
	}

	private UserDataService getUserDataService() {
		return (UserDataService) getServiceRegistry().getService(User.class);
	}
}
