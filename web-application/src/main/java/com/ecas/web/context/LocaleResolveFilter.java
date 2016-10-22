package com.ecas.web.context;

import com.ecas.utils.LocaleHolder;
import com.ecas.web.admin.bean.LanguageBean;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Resolve locale and put into thread context
 * 
 * @author tu.pham
 * 
 */
public class LocaleResolveFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
	        ServletException {
		if (isHandleRequest(request)) {
			if (request.getParameterMap().containsKey(LanguageBean.LANGE_PARAM)) {
				LocaleHolder.setParamLocale(request.getParameter(LanguageBean.LANGE_PARAM));
			}
			LocaleHolder.setLocale(request.getLocale().getLanguage());
			try {
				chain.doFilter(request, response);
			} finally {
				LocaleHolder.clearLocale();
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean isHandleRequest(ServletRequest request) {
		return !ServletFileUpload.isMultipartContent(((HttpServletRequest) request));
	}
}
