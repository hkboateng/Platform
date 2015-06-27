package com.boateng.abankus.security;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.boateng.abankus.fields.EmployeeFields;

public class AuthenticationProcessor implements AuthenticationProvider{

	private static final AuthenticationProcessor authenticate = new AuthenticationProcessor();
	
	private AuthenticationProcessor(){
		//Private Constructor
	}
	
	private Pattern pattern = null;
	private Matcher matcher = null;
	
	public static AuthenticationProcessor getInstance(){
		return authenticate;
	}
	
	public List<String> validLogin(HttpServletRequest request){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		List<String> errors = new LinkedList<String>();
		
		pattern = Pattern.compile(EmployeeFields.ALPHA_PATTERN);
		matcher = pattern.matcher(username);
		//Validate username field
		if(username.isEmpty() || password.isEmpty()){
			errors.add("Username or Password cannot be null or empty.");
		}
		
		if(!matcher.matches()){
			errors.add("Username or Password is Invalid!!");
		}
		return errors;
	}
	
	public void process(HttpServletRequest request){
		List<String> validate = validLogin(request);
		if(!validate.isEmpty()){
			
		}
	}
	


	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}
}
	
	
