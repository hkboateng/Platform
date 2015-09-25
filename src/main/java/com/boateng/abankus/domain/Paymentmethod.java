package com.boateng.abankus.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the paymentmethod database table.
 * 
 */
@Entity
@NamedQuery(name="Paymentmethod.findAll", query="SELECT p FROM Paymentmethod p")
public class Paymentmethod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int paymentMethodId;

	private String paymentType;

	//bi-directional many-to-one association to Customer

	@ManyToOne
	@JoinColumn(name="clientOrderId")
	private CustomerOrder customerOrder;

	public Paymentmethod() {
	}

	public int getPaymentMethodId() {
		return this.paymentMethodId;
	}

	public void setPaymentMethodId(int paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public CustomerOrder getCustomer() {
		return this.customerOrder;
	}

	public void setCustomer(CustomerOrder customer) {
		this.customerOrder = customer;
	}

	public boolean isPaymentTypeCash(){
		return getPaymentType() == "Cash" ? true : false;
	}
}