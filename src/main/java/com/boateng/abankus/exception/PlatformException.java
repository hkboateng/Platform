/**
 * hkboateng
 */
package com.boateng.abankus.exception;

/**
 * @author hkboateng
 *
 */
public class PlatformException extends Exception {

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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public PlatformException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
