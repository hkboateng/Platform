/**
 * hkboateng
 */
package com.boateng.abankus.exception;

import java.util.logging.Logger;

import org.springframework.security.core.AuthenticationException;

/**
 * @author hkboateng
 *
 */
public class CustomAuthenticationException extends AuthenticationException {

	private static final Logger logger = Logger.getLogger(CustomAuthenticationException.class.getName());
	/**
	 * 
	 */
	private static final long serialVersionUID = -2529490377891872455L;

	/**
	 * @param msg
	 */
	public CustomAuthenticationException(String msg) {
		super(msg);
		logger.warning(msg);
	}

	public CustomAuthenticationException(String msg,Throwable e) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
}
