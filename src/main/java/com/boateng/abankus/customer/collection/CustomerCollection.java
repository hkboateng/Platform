/**
 * hkboateng
 */
package com.boateng.abankus.customer.collection;

import java.util.Map;

import com.boateng.abankus.customer.service.Client;
import com.boateng.abankus.domain.Customer;

/**
 * This is a collection of Customers information.
 * 
 * @author hkboateng
 *
 */
public class CustomerCollection implements Client {

	private Map<String,Customer> customerCollection = null;
	@Override
	public boolean isCustomerInGoodStanding() {

		return false;
	}

	@Override
	public void setCustomerInGoodStanding() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CustomerCollection getCustomerCollection() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setCustomerCollection(CustomerCollection collection) {
		
		
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.customer.service.Client#getCustomerNumber()
	 */
	@Override
	public String getCustomerNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.customer.service.Client#setCustomerNumber(java.lang.String)
	 */
	@Override
	public void setCustomerNumber(String customerNumber) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.customer.service.Client#getCustomerId()
	 */
	@Override
	public Long getCustomerId() {
		// TODO Auto-generated method stub
		return null;
	}

}