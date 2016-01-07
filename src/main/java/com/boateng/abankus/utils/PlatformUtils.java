/**
 * hkboateng
 */
package com.boateng.abankus.utils;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	public static String generateConfirmationNo(){
		StringBuilder sbr = new StringBuilder();
		DateTime dt = DateTime.now();
		String characters = RandomStringUtils.randomAlphanumeric(6).toUpperCase();
		sbr.append(characters);
		sbr.append("-").append(dt.getHourOfDay())
		   .append(dt.getMinuteOfHour())
		   .append(dt.getSecondOfDay())
		   .append(dt.getMillisOfSecond());
		return sbr.toString();
	}

	public static DateTime convertUnixTime(String date){
		return DateTime.parse(date).toDateTime();
	}

	public static String convertToJSON(Object o) throws IOException{
	ObjectMapper mapper = new ObjectMapper();
		String map = null;
		if(o != null && o instanceof Object){
			mapper.writeValue(System.out, o);
			map = mapper.writeValueAsString(o);
		}
		return map;
	}
}
