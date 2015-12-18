package com.boateng.abankus.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the debitcardinformation database table.
 * 
 */
@Entity
@NamedQuery(name="Debitcardinformation.findAll", query="SELECT d FROM Debitcardinformation d")
public class Debitcardinformation implements Serializable {
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

	public Debitcardinformation() {
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
		this.expirationDate = expirationDate;
	}

	public String getNameOnCard() {
		return this.nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
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

}