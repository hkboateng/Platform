 package com.boateng.abankus.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.service.impl.AuthenticationServiceImpl;

public class SecurityUtils {
	
	/**
	 * 
	 */
	private static final String SECURITY_ALGORITHM = "AES/CBC/PKCS5PADDING";

	private static final String SECURITY_KEY = "Abankwah11kOFI5514*Macintosh-1250SmithfieldRoadAptF7NorthProvidenceIAmAChildOfGod";
	
	private static final String SECURITY_ITERATIONS = "65536";
	
	private static final String ENCODING = "UTF-8";
	
	private static final String AES_ALGORITHM = "AES";

	/*** Encoding Type */
	private static final String ENCODING_TYPE = "AES/CBC/PKCS5Padding";	
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


	  public static String hashPin(String pin, String password) throws NoSuchAlgorithmException, InvalidKeySpecException, PlatformException{
		  StringBuilder str = new StringBuilder(pin);
		  str.append(password);

		  generateStrongCodeHash(str.toString());

		  return passwdencode().encode(password);
	  }
	    private static String generateStrongCodeHash(String password) throws  PlatformException
	    {
	    	try{
		        int iterations = 1000;
		        char[] chars = password.toCharArray();
		        byte[] salt = getSalt().getBytes();
		         
		        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
		        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		        byte[] hash = skf.generateSecret(spec).getEncoded();
		        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
		        
	    	}catch( NoSuchAlgorithmException | InvalidKeySpecException e){
				PlatformException ace = new PlatformException(e);
				throw ace;	        	
	        }
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

	    private static SecretKey generateKey() throws PlatformException{
	    	SecretKey key = null;
			try {
		    	SecretKeyFactory factory = SecretKeyFactory.getInstance("AES");
		        KeySpec spec = new PBEKeySpec(SECURITY_KEY.toCharArray(), getSalt().getBytes(), Integer.parseInt(SECURITY_ITERATIONS),128);
		        SecretKey tmp = factory.generateSecret(spec);
		        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");	 		
		        key = new SecretKeySpec(SECURITY_KEY.getBytes(ENCODING),"AES");
	    	    return secret;
	    	    
			} catch (Exception e) {
				PlatformException ace = new PlatformException(e);
				throw ace;
			}
	    	    	
	    }
    
	    public static String generateCustomerPIN() throws PlatformException{
	    	
	    	StringBuilder sbr = new StringBuilder();
	    	String pin = RandomStringUtils.random(6, true, true).toUpperCase();
	    	sbr.append(pin);
	    	return sbr.toString();
	    }
	    
	    public static String encode(String message) throws PlatformException{

			try {
				Cipher cipher = Cipher.getInstance(ENCODING_TYPE);
				byte[] keyBytes = new byte[16];
				byte[] b = SecurityUtils.SECURITY_KEY.getBytes(SecurityUtils.ENCODING);
				int len = b.length;
				if (len > keyBytes.length) {
					len = keyBytes.length;
				}
				System.arraycopy(b, 0, keyBytes, 0, len);
				SecretKeySpec keySpec = new SecretKeySpec(keyBytes, SecurityUtils.AES_ALGORITHM);
				IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
				cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
				byte[] encryptResults = cipher.doFinal(message.getBytes(SecurityUtils.ENCODING));
				byte[] encodedByte = Base64.encodeBase64(encryptResults);
				String result = new String(encodedByte);
				return result;
			} catch (Exception e) {
				PlatformException ace = new PlatformException(e);
				throw ace;
			}
	    }
	    public static String decode(String message) throws PlatformException{
	    	
	    	try {
				Cipher cipher = Cipher.getInstance(ENCODING_TYPE);
				byte[] keyBytes = new byte[16];
				byte[] b = SecurityUtils.SECURITY_KEY.getBytes(SecurityUtils.ENCODING);
				int len = b.length;
				if (len > keyBytes.length) {
					len = keyBytes.length;
				}
				System.arraycopy(b, 0, keyBytes, 0, len);
				SecretKeySpec keySpec = new SecretKeySpec(keyBytes, SecurityUtils.AES_ALGORITHM);
				IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
				cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
				byte[] results = cipher.doFinal(new Base64().decode(message.getBytes()));
				return new String(results, SecurityUtils.ENCODING);

			} catch (Exception e) {
				PlatformException ace = new PlatformException(e);
				throw ace;
			}
	    }	    
	    public static String encryptOrderNumber(String order) throws PlatformException{

			try {
				Cipher cipher = Cipher.getInstance(ENCODING_TYPE);
				byte[] keyBytes = new byte[16];
				byte[] b = SecurityUtils.SECURITY_KEY.getBytes(SecurityUtils.ENCODING);
				int len = b.length;
				if (len > keyBytes.length) {
					len = keyBytes.length;
				}
				System.arraycopy(b, 0, keyBytes, 0, len);
				SecretKeySpec keySpec = new SecretKeySpec(keyBytes, SecurityUtils.AES_ALGORITHM);
				IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
				cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
				byte[] encryptResults = cipher.doFinal(order.getBytes(SecurityUtils.ENCODING));
				byte[] encodedByte = Base64.encodeBase64(encryptResults);
				String result = new String(encodedByte);
				return result;
			} catch (Exception e) {
				PlatformException ace = new PlatformException(e);
				throw ace;
			}
	    }
	    public static String decryptOrderNumber(String order) throws PlatformException{
	    	
	    	try {
				Cipher cipher = Cipher.getInstance(ENCODING_TYPE);
				byte[] keyBytes = new byte[16];
				byte[] b = SecurityUtils.SECURITY_KEY.getBytes(SecurityUtils.ENCODING);
				int len = b.length;
				if (len > keyBytes.length) {
					len = keyBytes.length;
				}
				System.arraycopy(b, 0, keyBytes, 0, len);
				SecretKeySpec keySpec = new SecretKeySpec(keyBytes, SecurityUtils.AES_ALGORITHM);
				IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
				cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
				byte[] results = cipher.doFinal(new Base64().decode(order.getBytes()));
				return new String(results, SecurityUtils.ENCODING);

			} catch (Exception e) {
				PlatformException ace = new PlatformException(e);
				throw ace;
			}
	    }    

}
