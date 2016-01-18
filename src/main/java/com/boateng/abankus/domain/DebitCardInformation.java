package com.boateng.abankus.domain;

import java.io.Serializable;
import javax.persistence.*;

public class DebitCardInformation implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cardInformationId;

	private String cardNumber;

	private String cardType;

	private String expirationDate;

	private String nameOnCard;

	private String securityNumber;

	//bi-directional one-to-one association to PaymentMethod
	@OneToOne
	@JoinColumn(name="paymentMethodId")
	private Paymentmethod paymentmethod;

	public DebitCardInformation() {
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

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
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
		return this.paymentmethod;
	}

	public void setPaymentmethod(Paymentmethod paymentmethod) {
		this.paymentmethod = paymentmethod;
	}


}