/**
 * hkboateng
 */
package com.boateng.abankus.application.builders;

import javax.servlet.http.HttpServletRequest;

import com.boateng.abankus.domain.OrderPayment;
import com.boateng.abankus.domain.Paymentmethod;

/**
 * @author hkboateng
 *
 */
public class CustomerPaymentBuilder {

	private String typeOfPayment;
	
	private String bankName;
	
	private String bankRoutingNumber;
	
	private String bankCustomerName;
	
	private String bankAccountNumber;
	
	private String checkNumber;
	
	private String amount = null;
	
	private String orderAmount;
	
	private String paymentSchedule;
	
	private String orderNumber ;
	
	private	Paymentmethod paymentMethod = null;
	
	public CustomerPaymentBuilder(HttpServletRequest request){
		/**
		 * Whether payment is made in cash or CC or Bank
		 */
		typeOfPayment = request.getParameter("paymentType");
		bankName = request.getParameter("bankName");
		bankRoutingNumber = request.getParameter("bankRoutingNumber");
		bankCustomerName = request.getParameter("bankCustName");
		bankAccountNumber = request.getParameter("bankAccountNumber");
		checkNumber = request.getParameter("checkNumber");		
		amount = request.getParameter("paymentAmount");
		orderAmount = request.getParameter("orderTotalAmount");
		orderNumber = request.getParameter("orderNumber");
		paymentSchedule = request.getParameter("paymentSchedule");
	}
	
	public Paymentmethod buildPaymentMethod(){
	paymentMethod = new Paymentmethod();

		
		paymentMethod.setPaymentType(typeOfPayment);
		if(typeOfPayment.equalsIgnoreCase("bank")){
			paymentMethod.setAccountnumber(bankAccountNumber);
			paymentMethod.setBankname(bankName);
			paymentMethod.setBanknumber(bankRoutingNumber);
			paymentMethod.setChecknumber(checkNumber);
			paymentMethod.setNameOnAccount(bankCustomerName);
		}		
		return paymentMethod;
	}
	
	public OrderPayment buildOrderPayment(){
		OrderPayment orderPayment = new OrderPayment();
		double paidAmount = 0.0;
		
		paidAmount = Double.parseDouble(amount);
		orderPayment.setAmountPaid(paidAmount);
		orderPayment.setPaymentSchedule(paymentSchedule);

		return null;
	}

	/**
	 * @return the typeOfPayment
	 */
	public String getTypeOfPayment() {
		return typeOfPayment;
	}

	/**
	 * @param typeOfPayment the typeOfPayment to set
	 */
	public void setTypeOfPayment(String typeOfPayment) {
		this.typeOfPayment = typeOfPayment;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the bankRoutingNumber
	 */
	public String getBankRoutingNumber() {
		return bankRoutingNumber;
	}

	/**
	 * @param bankRoutingNumber the bankRoutingNumber to set
	 */
	public void setBankRoutingNumber(String bankRoutingNumber) {
		this.bankRoutingNumber = bankRoutingNumber;
	}

	/**
	 * @return the bankCustomerName
	 */
	public String getBankCustomerName() {
		return bankCustomerName;
	}

	/**
	 * @param bankCustomerName the bankCustomerName to set
	 */
	public void setBankCustomerName(String bankCustomerName) {
		this.bankCustomerName = bankCustomerName;
	}

	/**
	 * @return the bankAccountNumber
	 */
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	/**
	 * @param bankAccountNumber the bankAccountNumber to set
	 */
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	/**
	 * @return the checkNumber
	 */
	public String getCheckNumber() {
		return checkNumber;
	}

	/**
	 * @param checkNumber the checkNumber to set
	 */
	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the orderAmount
	 */
	public String getOrderAmount() {
		return orderAmount;
	}

	/**
	 * @param orderAmount the orderAmount to set
	 */
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * @return the paymentSchedule
	 */
	public String getPaymentSchedule() {
		return paymentSchedule;
	}

	/**
	 * @param paymentSchedule the paymentSchedule to set
	 */
	public void setPaymentSchedule(String paymentSchedule) {
		this.paymentSchedule = paymentSchedule;
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
	 * @return the paymentMethod
	 */
	public Paymentmethod getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(Paymentmethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
}
