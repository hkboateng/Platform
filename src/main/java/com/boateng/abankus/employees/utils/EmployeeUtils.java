/**
 * 
 */
package com.boateng.abankus.employees.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author hkboateng
 *
 */
public class EmployeeUtils {

	public static synchronized String generateEmployeeId(){
		String numbers = RandomStringUtils.randomNumeric(10);
		return numbers;	
	}
	
	public static synchronized String generateAccountNumber(){
			String numbers = RandomStringUtils.randomNumeric(10);
			return numbers;	
	}
}
