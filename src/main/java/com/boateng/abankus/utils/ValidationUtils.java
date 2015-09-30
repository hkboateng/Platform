/**
 * hkboateng
 */
package com.boateng.abankus.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author hkboateng
 *
 */
public class ValidationUtils {

	public static boolean isAlpha(String s){
		return StringUtils.isAlphanumeric(s);
	}

	
	public static boolean isNullOrBlank(String str){
		return (StringUtils.isBlank(str) || StringUtils.isEmpty(str));
	}
	

}
