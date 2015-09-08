/**
 * hkboateng
 */
package com.boateng.abankus.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;

/**
 * @author hkboateng
 * 
 * Utilities that are common through out the application
 *
 */
public class PlatformUtils {

	private PlatformUtils(){}
	
	public static String getProductNumber(){

		StringBuilder sbr = new StringBuilder();
		String characters = RandomStringUtils.randomAlphabetic(2).toUpperCase();
		String numbers = RandomStringUtils.randomNumeric(8);
		sbr.append(characters)
			.append("-")
			.append(numbers);
			
		return sbr.toString();	
	}
	

	public static String getClientOrderNumber(){
		StringBuilder sbr = new StringBuilder();
		DateTime dt = DateTime.now();
		
		String characters = RandomStringUtils.randomAlphanumeric(6).toUpperCase();
		sbr.append(characters);
		sbr.append("-").append(dt.getHourOfDay())
					   .append(dt.getMinuteOfHour())
					   .append(dt.getSecondOfDay());
		
		return sbr.toString();
	}
	


}
