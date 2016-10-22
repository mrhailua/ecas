package com.ecas.web.context;

import com.ecas.domain.User;
import com.ecas.web.admin.bean.BaseBean;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * All request in organization context must sure that there is organization
 * context is available
 * 
 * @author tu.pham
 * 
 */
public class UserFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
	        ServletException {
		HttpServletRequest httpRequest = ((HttpServletRequest) request);
		User worker = (User) httpRequest.getSession().getAttribute(BaseBean.USER_CONTEXT_SESSION_KEY);

		if ((worker != null) && worker.isDelete()) {
			((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath() + "/views/user/deleted.xhtml");
		} else {
			chain.doFilter(request, response);
		}

	}
}
