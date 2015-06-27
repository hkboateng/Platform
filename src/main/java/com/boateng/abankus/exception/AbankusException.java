/**
 * 
 */
package com.boateng.abankus.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author hkboateng
 *
 */
public class AbankusException extends Exception {
	public static final Log log = LogFactory.getLog(AbankusException.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -9086849842617171667L;

	
	public AbankusException(String msg, Throwable arg1) {
		super(msg, arg1);
		log.warn(msg);
	}

	public AbankusException(String msg) {
		super(msg);
		log.warn(msg);
	}

	public AbankusException(Throwable e) {
		super(e);
		log.warn(e);
	}
	
	

}
