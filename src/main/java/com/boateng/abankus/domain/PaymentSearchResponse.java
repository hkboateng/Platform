package com.boateng.abankus.domain;

import java.math.BigDecimal;

public class PaymentSearchResponse {

	private String confirmationNumber;
	
	private BigDecimal amountPaid;
	
	private String datePaid;
	
	private String paymentType;
	
	private String orderNumber;
	
	private String employeeId;
	
	private BigDecimal orderAmount;

	private String customerNumber;
	
	/**
	 * 
	 */
	public PaymentSearchResponse() {
		// Default Constructor
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public BigDecimal getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(String amountPaid) {
		BigDecimal bd = null;
		if(amountPaid != null){
			bd = new BigDecimal(amountPaid);
		}else{
			bd = new BigDecimal("0.00");
		}
		this.amountPaid = bd;
	}

	public String getDatePaid() {
		return datePaid;
	}

	public void setDatePaid(String datePaid) {
		this.datePaid = datePaid;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		BigDecimal bd = null;
		if(orderAmount != null){
			bd = new BigDecimal(orderAmount);
		}else{
			bd = new BigDecimal("0.00");
		}
		this.orderAmount = bd;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	
	
}
