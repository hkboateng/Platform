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

	public static synchronized Long generateEmployeeId(){
		String numbers = RandomStringUtils.randomNumeric(10);
		return Long.parseLong(numbers);	
	}
	
	public static Long generateAccountNumber(){
			String numbers = RandomStringUtils.randomNumeric(10);
			return Long.parseLong(numbers);	
	}
}
