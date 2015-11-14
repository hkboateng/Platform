package com.boateng.abankus.domain;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

import javax.persistence.*;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;


/**
 * The persistent class for the paymenttransaction database table.
 * 
 */
@Entity
@NamedQuery(name="PaymentTransaction.findAll", query="SELECT p FROM PaymentTransaction p")
public class PaymentTransaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int paymentTrasactionId;

	private String amountPaid;

	private String customerNumber;

	private String orderNumber;

	private String paymentDate;

	private String paymentDay;

	private String paymentMonth;

	private String paymentTime;

	private String paymentType;

	private String paymentYear;

	private String transactionNumber;

	public PaymentTransaction() {
	}
	public PaymentTransaction(OrderPayment payment){
		this.amountPaid = String.valueOf(payment.getAmountPaid());
		this.orderNumber = payment.getClientorder().getOrderNumber();
		this.customerNumber = payment.getClientorder().getCustomer().getCustomerNumber();
		this.transactionNumber = payment.getConfirmationNumber();
		this.paymentType = payment.getPaymentMethod().getPaymentType();
		this.paymentTime = String.valueOf(DateTime.now().toLocalTime());
		this.paymentDay = String.valueOf(DateTime.now().getDayOfMonth());
		this.paymentMonth = String.valueOf(DateTime.now().getMonthOfYear());
		this.paymentYear = String.valueOf(DateTime.now().getYear());
		this.paymentDate = payment.getDatePaid().toString();
	}
	public int getPaymentTrasactionId() {
		return this.paymentTrasactionId;
	}

	public void setPaymentTrasactionId(int paymentTrasactionId) {
		this.paymentTrasactionId = paymentTrasactionId;
	}

	public String getAmountPaid() {
		return this.amountPaid;
	}

	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getCustomerNumber() {
		return this.customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentDay() {
		return this.paymentDay;
	}

	public void setPaymentDay(String paymentDay) {
		this.paymentDay = paymentDay;
	}

	public String getPaymentMonth() {
		return this.paymentMonth;
	}

	public void setPaymentMonth(String paymentMonth) {
		this.paymentMonth = paymentMonth;
	}

	public String getPaymentTime() {
		return this.paymentTime;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentYear() {
		return this.paymentYear;
	}

	public void setPaymentYear(String paymentYear) {
		this.paymentYear = paymentYear;
	}

	public String getTransactionNumber() {
		return this.transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

}