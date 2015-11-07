/**
 * hkboateng
 */
package com.boateng.abankus.application.ws.svc;

import org.joda.time.DateTime;

import com.boateng.abankus.application.interfaces.PlatformRequest;
import com.boateng.abankus.exception.PlatformException;

/**
 * @author hkboateng
 *
 */
public class ProductRequest implements PlatformRequest {

	private String requestDate;
	
	

	@Override
	public void buildRequest() throws PlatformException {
		this.requestDate = DateTime.now().toString();

	}

	@Override
	public void sendRequest() throws PlatformException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getConfirmationNumber() {
		// TODO Auto-generated method stub
		return null;
	}

}
