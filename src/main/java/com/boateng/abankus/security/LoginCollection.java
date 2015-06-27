package com.boateng.abankus.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;


public class LoginCollection {

	private  static Map<String, UserDetails> userMap = new HashMap<String,UserDetails>();
	
	public void put(String username, UserDetails userDetails){
		userMap.put(username, userDetails);
	}
	
	public UserDetails get(String key){
		if(userMap.containsKey(key)){
			return null;
		}
		return userMap.get(key);
	}
	
	public int size(){
		
		return userMap.size();
	}
	
	public void remove(String key) {

		if (userMap.containsKey(key)) {
			userMap.remove(key);
		}

	}
	
	
}
