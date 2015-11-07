/**
 * hkboateng
 */
package com.boateng.abankus.application.interfaces;

import com.boateng.abankus.exception.PlatformException;

/**
 * Interface for all request to be sent to a web service
 * @author hkboateng
 *
 */
public interface PlatformRequest {

	void buildRequest() throws PlatformException;
	
	void sendRequest() throws PlatformException;
	
	String getConfirmationNumber();
}
