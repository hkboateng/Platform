/**
 * hkboateng
 */
package com.boateng.abankus.utils;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.boateng.abankus.exception.PlatformException;

/**
 * @author hkboateng
 *
 */
public class CompanyUtils {

	/**
	 * 
	 */
	private CompanyUtils() {
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
}
