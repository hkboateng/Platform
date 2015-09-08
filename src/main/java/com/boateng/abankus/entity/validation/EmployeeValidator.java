/**
 * hkboateng
 */
package com.boateng.abankus.entity.validation;

import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is used to validate Employee Object before
 * performing any operation with the object.
 * @author hkboateng
 *
 */
public class EmployeeValidator {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeValidator.class);
	
	private HttpServletRequest request = null;
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String ZIPCODE_REGEX = "\\d{5}(-\\d{4})?";
	public EmployeeValidator(HttpServletRequest request){
		this.request = request;
	}
	
	public Map<String, String> validateEmployee(){

		return null;
	}
	
	private boolean isEmailValid(String email){
		return Pattern.matches(EMAIL_PATTERN, email);
	}
	
	private boolean isZipCodeValid(String zipcode){
		return Pattern.matches(ZIPCODE_REGEX, zipcode);
	}
	
	private boolean isNullOrBlank(String inputString){
		boolean isNull = false;
		if(inputString == null){
			isNull = true;
		}
		if(inputString.isEmpty()){
			isNull = true;
		}
		if(inputString.length() < 0){
			isNull = true;
		}
		return isNull;
	}
	
}
