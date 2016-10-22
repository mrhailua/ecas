package com.ecas.web.admin.converter;

import com.ecas.domain.User;
import com.ecas.service.ServiceRegistry;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Component
@FacesConverter("userConverter")
public class UserConverter implements Converter {

	@Autowired
	private ServiceRegistry serviceRegistry;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (StringUtils.isNotBlank(value)) {
			return serviceRegistry.getService(User.class).get(Integer.valueOf(value));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return String.valueOf(value);
		}
		return StringUtils.EMPTY;
	}

}
