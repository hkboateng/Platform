/**
 * hkboateng
 */
package com.boateng.abankus.authentication;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * @author hkboateng
 *
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	private String errorPage;
	
	
	/**
	 * @param errorPage
	 */
	public CustomAccessDeniedHandler(String errorPage) {
		this.errorPage = errorPage;
	}

	/**
	 * @param errorPage
	 */
	public CustomAccessDeniedHandler() {
		
	}
	/**
	 * @return the errorPage
	 */
	public String getErrorPage() {
		return errorPage;
	}


	/**
	 * @param errorPage the errorPage to set
	 */
	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	private static final Logger logger = Logger.getLogger(CustomAccessDeniedHandler.class.getCanonicalName());
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,AccessDeniedException accessDeniedException) throws IOException, ServletException {
		logger.warning(accessDeniedException.getMessage());
		response.sendRedirect(errorPage);

	}

}
