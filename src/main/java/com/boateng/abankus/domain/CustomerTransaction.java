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

	/**
	 * Same as confirmation number
	 */
	private String transactionId;
	
	private String customerNumber;
	
	//Cash, Check or Charge Card
	private String paymentType;
	
	private String orderNumber;
	
	private String amountPaid;
	
	private String payment_year;
	
	private String payment_month;
	
	private String payment_day;
	
	//24-hr time
	private String payment_time;
	
	
	public CustomerTransaction(OrderPayment payment){
		this.amountPaid = String.valueOf(payment.getAmountPaid());
		this.orderNumber = payment.getClientorder().getOrderNumber();
		this.customerNumber = payment.getClientorder().getCustomer().getCustomerNumber();
		this.transactionId = payment.getConfirmationNumber();
		this.paymentType = payment.getPaymentMethod().getPaymentType();
		this.payment_year = String.valueOf(payment.getPaymentDate().toLocalDateTime().getYear());
		this.payment_month = String.valueOf(payment.getPaymentDate().toLocalDateTime().getMonth().getValue());
		this.payment_day = String.valueOf(payment.getPaymentDate().toLocalDateTime().getDayOfMonth());
		this.payment_time = String.valueOf(payment.getPaymentDate().toLocalDateTime().toLocalTime());
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

	/**
	 * @return the payment_year
	 */
	public String getPayment_year() {
		return payment_year;
	}

	/**
	 * @param payment_year the payment_year to set
	 */
	public void setPayment_year(String payment_year) {
		this.payment_year = payment_year;
	}

	/**
	 * @return the payment_month
	 */
	public String getPayment_month() {
		return payment_month;
	}

	/**
	 * @param payment_month the payment_month to set
	 */
	public void setPayment_month(String payment_month) {
		this.payment_month = payment_month;
	}

	/**
	 * @return the payment_day
	 */
	public String getPayment_day() {
		return payment_day;
	}

	/**
	 * @param payment_day the payment_day to set
	 */
	public void setPayment_day(String payment_day) {
		this.payment_day = payment_day;
	}

	/**
	 * @return the payment_time
	 */
	public String getPayment_time() {
		return payment_time;
	}

	/**
	 * @param payment_time the payment_time to set
	 */
	public void setPayment_time(String payment_time) {
		this.payment_time = payment_time;
	}
	
	

}
