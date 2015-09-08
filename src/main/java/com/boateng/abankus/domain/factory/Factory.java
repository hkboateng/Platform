/**
 * 
 */
package com.boateng.abankus.domain.factory;

import javax.servlet.http.HttpServletRequest;

import com.boateng.abankus.customer.service.Client;

/**
 * @author hkboateng
 *
 */
public abstract class Factory {


	public abstract Client construct(String domain,HttpServletRequest request);

	
	
}
