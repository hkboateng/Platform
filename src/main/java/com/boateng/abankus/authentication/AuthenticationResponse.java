package com.boateng.abankus.authentication;

import java.util.ArrayList;
import java.util.List;

import com.boateng.abankus.domain.Permission;
import com.boateng.abankus.domain.User;


public class AuthenticationResponse {


	private boolean result;

	private String message;

	private int userId;
	
	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	/**
	 * @param result
	 */
	public AuthenticationResponse(boolean result) {
		this.result = result;
	}
	
	public AuthenticationResponse() {
		//Default Constructor
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	
}
