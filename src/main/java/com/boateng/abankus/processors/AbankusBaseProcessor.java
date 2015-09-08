/**
 * hkboateng
 */
package com.boateng.abankus.processors;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


/**
 * @author hkboateng
 *
 */
public class AbankusBaseProcessor {
	private static Logger logger = null;
	public boolean isNullOrBlank(String str){
		return (StringUtils.isBlank(str) || StringUtils.isEmpty(str));
	}
	
	/**
	public void log(Class<?> cls,String level,String message){
		
		logger = Logger.getLogger(cls);
		logger.setLevel(Level.toLevel(level));

	}
	**/
}
