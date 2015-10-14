/**
 * hkboateng
 */
package com.boateng.abankus.customer.processor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author hkboateng
 *
 */
public class CustomerLocationSvc {

	private static final String CONSUMER_KEY = "uR4yyup5uGFuYQ7lPk6JmJ1VLAH9kZ9j";
	
	private static final String GEOCODE_QUERY = "http://www.mapquestapi.com/geocoding/v1/address?key=";
	
	private CustomerLocationSvc(){}
	
	public static String getLocationGeoCode(String location){
		StringBuilder sbr = new StringBuilder();
		sbr.append(GEOCODE_QUERY).append(CONSUMER_KEY).append("&location=").append(location);
		try {
			
			URL url = new URL(sbr.toString());

				HttpURLConnection client = (HttpURLConnection) url.openConnection();
				
		} catch (IOException ae) {
			ae.printStackTrace();
		}
		return "";
	}
}
