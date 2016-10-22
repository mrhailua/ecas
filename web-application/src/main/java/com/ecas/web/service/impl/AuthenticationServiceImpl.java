package com.ecas.web.service.impl;

import com.ecas.web.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

public class AuthenticationServiceImpl implements AuthenticationService, Serializable {
	private static final long serialVersionUID = -2183798795341214010L;
	private AuthenticationManager authenticationManager;

	@Override
	public boolean login(String username, String password) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
		        username, password));
		if (authentication.isAuthenticated()) {
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(authentication);
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
			        .getSession(false);
			session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
			return true;
		}
		return false;
	}

	/**
	 * @return the authenticationManager
	 */
	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	/**
	 * @param authenticationManager
	 *            the authenticationManager to set
	 */
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = (AuthenticationManager) authenticationManager;
	}
}
