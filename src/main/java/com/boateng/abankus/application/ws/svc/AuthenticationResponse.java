/**
 * hkboateng
 */
package com.boateng.abankus.application.ws.svc;

/**
 * @author hkboateng
 *
 */
public class AuthenticationResponse {


	private boolean result;

	private String message;
	
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
	public AuthenticationResponse(String result) {
		this.result = Boolean.parseBoolean(result);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
