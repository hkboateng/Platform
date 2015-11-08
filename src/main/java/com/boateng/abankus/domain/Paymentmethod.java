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

	private String accountnumber;

	private String bankname;

	private String banknumber;

	private String checknumber;

	private String nameOnAccount;

	private String paymentType;

	//bi-directional one-to-one association to Orderpayment
	@OneToOne(mappedBy="paymentmethod")
	private OrderPayment orderpayment;

	public Paymentmethod() {
	}

	public int getPaymentMethodId() {
		return this.paymentMethodId;
	}

	public void setPaymentMethodId(int paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public String getAccountnumber() {
		return this.accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getBankname() {
		return this.bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBanknumber() {
		return this.banknumber;
	}

	public void setBanknumber(String banknumber) {
		this.banknumber = banknumber;
	}

	public String getChecknumber() {
		return this.checknumber;
	}

	public void setChecknumber(String checknumber) {
		this.checknumber = checknumber;
	}

	public String getNameOnAccount() {
		return this.nameOnAccount;
	}

	public void setNameOnAccount(String nameOnAccount) {
		this.nameOnAccount = nameOnAccount;
	}

	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public OrderPayment getOrderpayment() {
		return this.orderpayment;
	}

	public void setOrderpayment(OrderPayment orderpayment) {
		this.orderpayment = orderpayment;
	}

}