package com.boateng.abankus.application.ws.svc;

public class AuthenticationCompanyRequest {

	private int companyId;
	
	private String authenticationKey;

	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the authenticationKey
	 */
	public String getAuthenticationKey() {
		return authenticationKey;
	}

	/**
	 * @param authenticationKey the authenticationKey to set
	 */
	public void setAuthenticationKey(String authenticationKey) {
		this.authenticationKey = authenticationKey;
	}

	/**
	 * 
	 */
	public AuthenticationCompanyRequest() {
		// TODO Auto-generated constructor stub
	}
	
	
}
