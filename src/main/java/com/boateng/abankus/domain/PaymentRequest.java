/**
 * hkboateng
 */
package com.boateng.abankus.domain;

import java.math.BigDecimal;

import org.joda.time.DateTime;

/**
 * @author hkboateng
 *
 */
public class PaymentRequest {

	private Integer customerId;
	
	private String paymentMethod;
	
	private BigDecimal amountPaid;
	
	private String paymentDate;
	
	private Integer orderId;
	
	private Integer employeeId;
	
	public PaymentRequest(){
		
	}


	/**
	 * @param customerId
	 * @param orderNumber
	 * @param paymentMethod
	 * @param amountPaid
	 * @param paymentDate
	 */
	public PaymentRequest(Integer customerId, Integer orderId, String paymentMethod, BigDecimal amountPaid,
			String paymentDate) {
		this.customerId = customerId;
		this.setOrderId(orderId);
		this.paymentMethod = paymentMethod;
		this.amountPaid = amountPaid;
		this.paymentDate = paymentDate;
	}


	public Integer getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}



	public String getPaymentMethod() {
		return paymentMethod;
	}


	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	public BigDecimal getAmountPaid() {
		return amountPaid;
	}


	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}


	public String getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}


	public Integer getOrderId() {
		return orderId;
	}


	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}


	public Integer getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	
}
