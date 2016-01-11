/**
 * hkboateng
 */
package com.boateng.abankus.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * @author hkboateng
 *
 */
public class ValidationUtils {

	private static Pattern pattern;
	private static Matcher matcher;
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static final String ZIPCODE_PATTERN = 
			"^[0-9]{5}(?:-[0-9]{4})?$";
	
	//private String 
	public static boolean isAlpha(String s){
		if(isNullOrBlank(s)){
			return false;
		}else{
			return StringUtils.isAlpha(s);
		}
	}

	public static boolean isAlphaNumeric(String s){
		if(isNullOrBlank(s)){
			return false;
		}else{
			return StringUtils.isAlphanumeric(s);
		}
	}	
	
	public static boolean isNullOrBlank(String str){
		return (StringUtils.isBlank(str) || StringUtils.isEmpty(str));
	}
	
	public static boolean isZipCodeValid(String zipcode){
		if(isNullOrBlank(zipcode)){
			return false;
		}else{
			pattern = Pattern.compile(ZIPCODE_PATTERN);
			matcher = pattern.matcher(zipcode);
			
			return matcher.matches();
		}		
	}
	public static boolean isEmailValid(String email){
		if(isNullOrBlank(email)){
			return false;
		}else{
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(email);
			
			return matcher.matches();
		}
	}
}
