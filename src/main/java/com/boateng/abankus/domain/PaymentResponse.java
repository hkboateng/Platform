package com.boateng.abankus.domain;

import java.util.List;

public class PaymentResponse {
	
	private String customerNumber;
	
	private String paymentFrom;
	
	private String paymentTo;

	private List<PaymentTransaction> paymentList;
	
	private List<OrderPayment> orderPaymentList;

	
	public List<PaymentTransaction> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<PaymentTransaction> paymentList) {
		this.paymentList = paymentList;
	}

	public List<OrderPayment> getOrderPaymentList() {
		return orderPaymentList;
	}

	public void setOrderPaymentList(List<OrderPayment> orderPaymentList) {
		this.orderPaymentList = orderPaymentList;
	}

	/**
	 * 
	 */
	public PaymentResponse() {
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getPaymentFrom() {
		return paymentFrom;
	}

	public void setPaymentFrom(String paymentFrom) {
		this.paymentFrom = paymentFrom;
	}

	public String getPaymentTo() {
		return paymentTo;
	}

	public void setPaymentTo(String paymentTo) {
		this.paymentTo = paymentTo;
	}
	
	public List<PaymentTransaction> addOrderPayment(List<OrderPayment> orderPayment){
		if(orderPayment != null){
			for(OrderPayment payment : orderPayment){
				PaymentTransaction transaction = new PaymentTransaction(payment);
				this.paymentList.add(transaction);
			}
		}
		return this.paymentList;
	}
}
