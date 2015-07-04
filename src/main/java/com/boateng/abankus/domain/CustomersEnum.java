/**
 * 
 */
package com.boateng.abankus.domain;

/**
 * @author hkboateng
 *
 */
public enum CustomersEnum {

	INDIVIDUAL("individual"),
	COMPANY("company");
	
	private String customer;

	CustomersEnum(String customer) {
		this.customer = customer;
	}

	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}


	
	
}
