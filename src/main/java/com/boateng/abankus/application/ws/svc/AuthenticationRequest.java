/**
 * hkboateng
 */
package com.boateng.abankus.application.ws.svc;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hkboateng
 *
 */
public class AuthenticationRequest {

	private String username;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**request.getParameter("username");
		String password = request.getParameter("password");
	 * @param username
	 * @param password
	 */
	public AuthenticationRequest(String username) {
		this.username = username;
	}
	public AuthenticationRequest(HttpServletRequest request) {
		this.username = request.getParameter("username");
		this.password = request.getParameter("password");
	}
	
}
