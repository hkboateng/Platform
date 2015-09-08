 package com.boateng.abankus.utils;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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


	  public static String hashPin(String pin, String password) throws NoSuchAlgorithmException, InvalidKeySpecException{
		  StringBuilder str = new StringBuilder(pin);
		  str.append(password);

		  generateStorngCodeHash(str.toString());

		  return passwdencode().encode(password);
	  }
	    private static String generateStorngCodeHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
	    {
	        int iterations = 1000;
	        char[] chars = password.toCharArray();
	        byte[] salt = getSalt().getBytes();
	         
	        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
	        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	        byte[] hash = skf.generateSecret(spec).getEncoded();
	        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
	    }
	     
	    private static String getSalt() throws NoSuchAlgorithmException
	    {
	        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
	        byte[] salt = new byte[16];
	        sr.nextBytes(salt);
	        return salt.toString();
	    }
	     
	    private static String toHex(byte[] array) throws NoSuchAlgorithmException
	    {
	        BigInteger bi = new BigInteger(1, array);
	        String hex = bi.toString(16);
	        int paddingLength = (array.length * 2) - hex.length();
	        if(paddingLength > 0)
	        {
	            return String.format("%0"  +paddingLength + "d", 0) + hex;
	        }else{
	            return hex;
	        }
	    }
	    
	    
}
