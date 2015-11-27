/**
 * hkboateng
 */
package com.boateng.abankus.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;

/**
 * @author hkboateng
 *
 */
@Entity
@DynamicUpdate(value=true)
public class ContactPerson  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int contactId;
	
	private String firstname;
	
	private String lastname;
	
	private String email;
	
	private String phoneNumber;
	
	private String association;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;
	/**
	 * @return the contactId
	 */
	public int getContactId() {
		return contactId;
	}

	/**
	 * @param contactId the contactId to set
	 */
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the association
	 */
	public String getAssociation() {
		return association;
	}

	/**
	 * @param association the association to set
	 */
	public void setAssociation(String association) {
		this.association = association;
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

	public String toString(){
		StringBuilder sbr = new StringBuilder();
		sbr.append(getFirstname()).append(" ").append(getLastname());
		return sbr.toString();
	}
}
