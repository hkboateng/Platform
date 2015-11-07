/**
 * hkboateng
 */
package com.boateng.abankus.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hkboateng
 *
 */
public class CustomerTransaction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String transactionId;
	
	private String customerNumber;
	
	private String paymentType;
	
	private String orderNumber;
	
	private String amountPaid;
	
	private Map<String,List<OrderPayment>> orderPaymentMap;
	
	public CustomerTransaction(OrderPayment payment){
		this.amountPaid = String.valueOf(payment.getAmountPaid());
		this.orderNumber = payment.getClientorder().getOrderNumber();
		this.customerNumber = payment.getClientorder().getCustomer().getCustomerNumber();
		this.transactionId = payment.getConfirmationNumber();
		this.paymentType = payment.getPaymentMethod().getPaymentType();

	}

	
	public Map<String,List<OrderPayment>> getOrderPayment(){
		return orderPaymentMap;
	}
	public List<OrderPayment> getOrderPayment(String orderNumber){
		return getOrderPayment().get(orderNumber);
	}

	


	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the customerNumber
	 */
	public String getCustomerNumber() {
		return customerNumber;
	}

	/**
	 * @param customerNumber the customerNumber to set
	 */
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}


	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}


	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}


	/**
	 * @return the orderNumber
	 */
	public String getOrderNumber() {
		return orderNumber;
	}


	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}


	/**
	 * @return the amountPaid
	 */
	public String getAmountPaid() {
		return amountPaid;
	}


	/**
	 * @param amountPaid the amountPaid to set
	 */
	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}
	
	

}
