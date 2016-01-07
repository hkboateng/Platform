/**
 * hkboateng
 */
package com.boateng.abankus.application.builders;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * @author hkboateng
 *
 */
public class AuthenticationBuilder {
	
	public void sendRequest(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/AuthenticationHub/rs");
		target.path("/authentication/saveCustomerCredentials").queryParam("credential", null);
	}
}
