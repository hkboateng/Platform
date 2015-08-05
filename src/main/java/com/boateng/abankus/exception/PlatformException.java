/**
 * hkboateng
 */
package com.boateng.abankus.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author hkboateng
 *
 */
public class PlatformException extends Exception {

	public static final Log log = LogFactory.getLog(PlatformException.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -8408549120452171340L;

	/**
	 * 
	 */
	public PlatformException() {
		// TODO Auto-generated constructor stub
	}

	public void logger(String message, Throwable t){
		log.warn(message, t);
	}
	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public PlatformException(String message, Throwable cause,boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	/**
	 * @param message
	 * @param cause
	 */
	public PlatformException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public PlatformException(String message) {
		super(message);
		log.warn(message);
	}

	/**
	 * @param cause
	 */
	public PlatformException(Throwable cause) {
		super(cause);
		log.warn(cause);
	}

	
}
