/**
 * hkboateng
 */
package com.boateng.abankus.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author hkboateng
 *
 */
public class PlatformException extends Exception {

	private static final Log log = LogFactory.getLog(PlatformException.class);
	private static final Logger logger = Logger.getLogger(PlatformException.class.getName());
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
		logger.log(Level.FINEST, message, t);
	}
	public void logger(Level level,String message, Throwable t){
		logger.log(level, message,t);
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
		logger.log(Level.SEVERE, cause.getMessage(),cause);
		cause.printStackTrace();

	}

	
}
