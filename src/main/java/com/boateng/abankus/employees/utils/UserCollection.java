/**
 * hkboateng
 */
package com.boateng.abankus.employees.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.boateng.abankus.domain.User;

/**
 * @author hkboateng
 *
 */
public class UserCollection {

	private UserCollection(){}
	
	private static final  UserCollection userCollection = new UserCollection();
	
	private static Map<String, User> userMap = new ConcurrentHashMap<String, User>();
	public static UserCollection getInstance(){
		return userCollection;
	}
	
	public User getUserByUsername(String username){
		return userMap.get(username);
	}
	
	public void addUser(String username, User u){
		if(!userMap.containsKey(username)){
			userMap.put(username, u);
		}
	}
	
}
