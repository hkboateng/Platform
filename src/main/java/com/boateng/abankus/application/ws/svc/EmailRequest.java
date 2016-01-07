/**
 * hkboateng
 */
package com.boateng.abankus.application.ws.svc;

/**
 * @author hkboateng
 *
 */
public class EmailRequest {

	private String sentTo;
	
	private String message;
	
	private String sentFrom;

	public String getSentTo() {
		return sentTo;
	}

	public void setSentTo(String sentTo) {
		this.sentTo = sentTo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSentFrom() {
		return sentFrom;
	}

	public void setSentFrom(String sentFrom) {
		this.sentFrom = sentFrom;
	}
	
	
}
