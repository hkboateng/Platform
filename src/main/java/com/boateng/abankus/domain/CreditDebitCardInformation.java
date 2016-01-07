package com.boateng.abankus.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.boateng.abankus.application.interfaces.PaymentCollection;


/**
 * The persistent class for the debitcardinformation database table.
 * 
 */
@Entity
@Table(name="Debitcardinformation")
@NamedQuery(name="Debitcardinformation.findAll", query="SELECT d FROM CreditDebitCardInformation d")
public class CreditDebitCardInformation implements Serializable,PaymentCollection {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cardInformationId;

	private String cardNumber;

	private String careType;

	private String expirationDate;

	private String nameOnCard;

	private String securityNumber;

	private Paymentmethod paymentmethodId;

	public CreditDebitCardInformation() {
	}

	public int getCardInformationId() {
		return this.cardInformationId;
	}

	public void setCardInformationId(int cardInformationId) {
		this.cardInformationId = cardInformationId;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCareType() {
		return this.careType;
	}

	public void setCareType(String careType) {
		this.careType = careType;
	}

	public String getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		if(expirationDate != null && !expirationDate.isEmpty()){
			this.expirationDate = expirationDate;
		}
	}

	public String getNameOnCard() {
		return this.nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		if(nameOnCard != null && !nameOnCard.isEmpty()){
			this.nameOnCard = nameOnCard;
		}
	}

	public String getSecurityNumber() {
		return this.securityNumber;
	}

	public void setSecurityNumber(String securityNumber) {
		this.securityNumber = securityNumber;
	}

	public Paymentmethod getPaymentmethod() {
		return this.paymentmethodId;
	}

	public void setPaymentmethod(Paymentmethod paymentmethod) {
		this.paymentmethodId = paymentmethod;
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.application.interfaces.PaymentCollection#getPaymentType()
	 */
	@Override
	public String getPaymentType() {
		return paymentmethodId.getPaymentType();
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.application.interfaces.PaymentCollection#setPaymentType(java.lang.String)
	 */
	@Override
	public void setPaymentType(String paymentType) {
		// TODO Auto-generated method stub
		
	}

}