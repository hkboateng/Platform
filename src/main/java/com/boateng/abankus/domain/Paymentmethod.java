package com.boateng.abankus.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the paymentmethod database table.
 * 
 */
@Entity
@DynamicUpdate(value=true)
@NamedQuery(name="Paymentmethod.findAll", query="SELECT p FROM Paymentmethod p")
public class Paymentmethod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int paymentMethodId;


	private String paymentType;
	
	@JsonIgnoreProperties
	@OneToOne(mappedBy="paymentmethod")
	private OrderPayment orderpayment;


	//bi-directional many-to-one association to BankInformation
	@OneToOne(mappedBy="paymentmethod")
	private BankInformation bankinformation;
	
	private DebitCardInformation debitcardinformation;

	
	public Paymentmethod() {
	}

	public Paymentmethod(String paymentType) {
		this.paymentType = paymentType;
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

	public OrderPayment getOrderpayment() {
		return this.orderpayment;
	}

	public void setOrderpayment(OrderPayment orderpayment) {
		this.orderpayment = orderpayment;
	}

	public BankInformation getBankinformation() {
		return bankinformation;
	}

	public void setBankinformation(BankInformation bankinformation) {
		this.bankinformation = bankinformation;
	}

	public DebitCardInformation getDebitcardinformation() {
		return debitcardinformation;
	}

	public void setDebitcardinformation(DebitCardInformation debitcardinformation) {
		this.debitcardinformation = debitcardinformation;
	}

}