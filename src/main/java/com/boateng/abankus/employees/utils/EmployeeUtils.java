/**
 * 
 */
package com.boateng.abankus.employees.utils;

import org.apache.commons.lang3.RandomStringUtils;

import com.boateng.abankus.servlet.PlatformAbstractServlet;

/**
 * @author hkboateng
 *
 */
public class EmployeeUtils extends PlatformAbstractServlet{

	public static synchronized String generateEmployeeId(){
		String numbers = RandomStringUtils.randomNumeric(10);
		return numbers;	
	}
	
	public static synchronized String generateAccountNumber(){
			String numbers = RandomStringUtils.randomNumeric(10);
			return numbers;	
	}
}
