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

	private String nameOnCard;
	
	private String cardType;
	
	private String cardNumber;
	
	private String expirationDate;
	
	private String securityNumber;
	
	private String typeOfPayment;
	
	private String bankName;
	
	private String bankRoutingNumber;
	
	private String bankCustomerName;
	
	private String bankAccountNumber;
	
	private String checkNumber;
	
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

	public String getNameOnCard() {
		return nameOnCard;
	}


	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}


	public String getCardType() {
		return cardType;
	}


	public void setCardType(String cardType) {
		this.cardType = cardType;
	}


	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public String getExpirationDate() {
		return expirationDate;
	}


	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}


	public String getSecurityNumber() {
		return securityNumber;
	}


	public void setSecurityNumber(String securityNumber) {
		this.securityNumber = securityNumber;
	}


	public String getTypeOfPayment() {
		return typeOfPayment;
	}


	public void setTypeOfPayment(String typeOfPayment) {
		this.typeOfPayment = typeOfPayment;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getBankRoutingNumber() {
		return bankRoutingNumber;
	}


	public void setBankRoutingNumber(String bankRoutingNumber) {
		this.bankRoutingNumber = bankRoutingNumber;
	}


	public String getBankCustomerName() {
		return bankCustomerName;
	}


	public void setBankCustomerName(String bankCustomerName) {
		this.bankCustomerName = bankCustomerName;
	}


	public String getBankAccountNumber() {
		return bankAccountNumber;
	}


	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}


	public String getCheckNumber() {
		return checkNumber;
	}


	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}
	
	
}
