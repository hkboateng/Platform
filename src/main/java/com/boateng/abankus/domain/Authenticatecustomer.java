package com.boateng.abankus.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the authenticatecustomer database table.
 * 
 */
@Entity
@NamedQuery(name="Authenticatecustomer.findAll", query="SELECT a FROM Authenticatecustomer a")
public class Authenticatecustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int authenticateId;

	private String pin;

	//bi-directional one-to-one association to Customer
	@OneToOne
	@JoinColumn(name="customerId")
	private Customer customer;

	public Authenticatecustomer() {
	}

	public int getAuthenticateId() {
		return this.authenticateId;
	}


	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}