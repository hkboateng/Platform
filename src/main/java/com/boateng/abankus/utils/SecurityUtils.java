 package com.boateng.abankus.utils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.boateng.abankus.domain.Role;
import com.boateng.abankus.service.impl.AuthenticationServiceImpl;

public class SecurityUtils {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private AuthenticationServiceImpl authenticate;
	private SecurityUtils(){
		authenticate = new AuthenticationServiceImpl();
	}
	public static synchronized Long generateEmployeeId(){
		String numbers = RandomStringUtils.randomNumeric(10);
		return Long.parseLong(numbers);	
	}
	public static synchronized String generateCustomerId(){
		StringBuilder sbr = new StringBuilder();
		String characters = RandomStringUtils.randomAlphabetic(2);
		String numbers = RandomStringUtils.randomNumeric(8);
		sbr.append(characters).append(numbers);
		return sbr.toString();	
	}

	
	private static BCryptPasswordEncoder passwdencode(){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		return encoder;
	}
	 protected static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
	    {
	        String[] parts = storedPassword.split(":");
	        int iterations = Integer.parseInt(parts[0]);
	        byte[] salt = fromHex(parts[1]);
	        byte[] hash = fromHex(parts[2]);
	         
	        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
	        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	        byte[] testHash = skf.generateSecret(spec).getEncoded();
	         
	        int diff = hash.length ^ testHash.length;
	        for(int i = 0; i < hash.length && i < testHash.length; i++)
	        {
	            diff |= hash[i] ^ testHash[i];
	        }
	        return diff == 0;
	    }
	    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
	    {
	        byte[] bytes = new byte[hex.length() / 2];
	        for(int i = 0; i<bytes.length ;i++)
	        {
	            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
	        }
	        return bytes;
	    }
	  public static synchronized String generateStorngPasswordHash(String password){
		  return passwdencode().encode(password);
	  }
	  
	  public static boolean authenticatePassword(String originalPassword, String storedPassword){
		  return passwdencode().matches(originalPassword, storedPassword);
	  }


	  public  List<Role> getAllRoles(){
		  return authenticate.getAllRoles();
	  }
	    
}
