package com.boateng.abankus.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.boateng.abankus.application.interfaces.PaymentCollection;


/**
 * The persistent class for the bankinformation database table.
 * 
 */
@Entity
@Table(name="Bankinformation")
@NamedQuery(name="Bankinformation.findAll", query="SELECT b FROM BankInformation b")
public class BankInformation implements Serializable,PaymentCollection {
	private static final long serialVersionUID = 1L;
	
	private int bankInformationId;
	
	private String accountNumber;
	
	private String bankName;
	
	private String bankNumber;
	
	private String checkNumber;
	
	private String nameOnAccount;
	
	private Paymentmethod paymentmethod;

	public BankInformation() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getBankInformationId() {
		return this.bankInformationId;
	}

	public void setBankInformationId(int bankInformationId) {
		this.bankInformationId = bankInformationId;
	}


	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getBankNumber() {
		return this.bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}


	public String getCheckNumber() {
		return this.checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}


	public String getNameOnAccount() {
		return this.nameOnAccount;
	}

	public void setNameOnAccount(String nameOnAccount) {
		this.nameOnAccount = nameOnAccount;
	}


	//bi-directional one-to-one association to Paymentmethod
	@OneToOne
	@JoinColumn(name="paymentMethodId")
	public Paymentmethod getPaymentmethod() {
		return this.paymentmethod;
	}

	public void setPaymentmethod(Paymentmethod paymentmethod) {
		this.paymentmethod = paymentmethod;
	}


	/* (non-Javadoc)
	 * @see com.boateng.abankus.application.interfaces.PaymentCollection#getPaymentType()
	 */
	@Override
	public String getPaymentType() {
		return paymentmethod.getPaymentType();
	}


	/* (non-Javadoc)
	 * @see com.boateng.abankus.application.interfaces.PaymentCollection#setPaymentType(java.lang.String)
	 */
	@Override
	public void setPaymentType(String paymentType) {
		// TODO Auto-generated method stub
		
	}

}