/**
 * hkboateng
 */
package com.boateng.abankus.domain;

import com.boateng.abankus.customer.service.Client;

/**
 * @author hkboateng
 *
 */
public class CustomerBilling implements Client {

	private String billingId;
	
	private Customer customer;

	public CustomerBilling(Customer customer){
		this.customer = customer;
	}

}
