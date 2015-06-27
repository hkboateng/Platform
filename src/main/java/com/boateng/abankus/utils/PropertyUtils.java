/**
 * 
 */
package com.boateng.abankus.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author hkboateng
 *
 */
public class PropertyUtils {

	private static final PropertyUtils property = new PropertyUtils();
	private PropertyUtils(){}
	
	public static PropertyUtils getInstance(){
		return property;
	}
	
	/**
	 * 
	 * @param propertname
	 * @param key
	 * @throws IOException
	 */
	public String getApplicationPrperty(String propertyname, String key){
		Properties props = new Properties();
		try {
			props.load(PropertyUtils.class.getResourceAsStream(propertyname));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!props.containsKey(key)){
			return null;
		}
		return (String) props.get(key);
	}
}
