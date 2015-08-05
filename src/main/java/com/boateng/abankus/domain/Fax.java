/**
 * hkboateng
 */
package com.boateng.abankus.domain;

/**
 * An entity for Customer or Client Fax numbers
 * @author hkboateng
 *
 */
public class Fax {

	private int faxId;
	
	private String faxNumber;
	
	private Customer customer;

	/**
	 * @return the faxId
	 */
	public int getFaxId() {
		return faxId;
	}

	/**
	 * @param faxId the faxId to set
	 */
	public void setFaxId(int faxId) {
		this.faxId = faxId;
	}

	/**
	 * @return the faxNumber
	 */
	public String getFaxNumber() {
		return faxNumber;
	}

	/**
	 * @param faxNumber the faxNumber to set
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Default Constructor
	 */
	public Fax() {
	}

	/**
	 * @param faxId
	 * @param faxNumber
	 * @param customer
	 */
	public Fax(int faxId, String faxNumber, Customer customer) {
		this.faxId = faxId;
		this.faxNumber = faxNumber;
		this.customer = customer;
	}
	
	
}
