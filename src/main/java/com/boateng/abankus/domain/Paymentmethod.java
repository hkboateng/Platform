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



	private String bankname;
	
	private String banknumber;
	
	private String checknumber;
	
	private String accountnumber;
	
	private String nameOnAccount;
	
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


	public boolean isPaymentTypeCash(){
		return getPaymentType() == "Cash" ? true : false;
	}

	/**
	 * @return the bankname
	 */
	public String getBankname() {
		return bankname;
	}

	/**
	 * @param bankname the bankname to set
	 */
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	/**
	 * @return the banknumber
	 */
	public String getBanknumber() {
		return banknumber;
	}

	/**
	 * @param banknumber the banknumber to set
	 */
	public void setBanknumber(String banknumber) {
		this.banknumber = banknumber;
	}

	/**
	 * @return the checknumber
	 */
	public String getChecknumber() {
		return checknumber;
	}

	/**
	 * @param checknumber the checknumber to set
	 */
	public void setChecknumber(String checknumber) {
		this.checknumber = checknumber;
	}

	/**
	 * @return the accountnumber
	 */
	public String getAccountnumber() {
		return accountnumber;
	}

	/**
	 * @param accountnumber the accountnumber to set
	 */
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getNameOnAccount() {
		return nameOnAccount;
	}

	public void setNameOnAccount(String nameOnAccount) {
		this.nameOnAccount = nameOnAccount;
	}

	
	
}