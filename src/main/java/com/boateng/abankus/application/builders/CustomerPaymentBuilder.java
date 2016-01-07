/**
 * hkboateng
 */
package com.boateng.abankus.application.builders;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.boateng.abankus.domain.BankInformation;
import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.domain.CreditDebitCardInformation;
import com.boateng.abankus.domain.Employee;
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
	
	private String amount;
	
	private String orderAmount;
	
	private String paymentSchedule;
	
	private String orderNumber;
	
	//private	Paymentmethod paymentMethod;
	
	private String nameOnCard;
	
	private String cardType;
	
	private String cardNumber;
	
	private String expirationDate;
	
	private String securityNumber;
	
	public CustomerPaymentBuilder(HttpServletRequest request){
		/**
		 * Whether payment is made in cash or CC or Bank
		 */
		this.typeOfPayment = request.getParameter("paymentForm");
		this.bankName = request.getParameter("bankName");
		this.bankRoutingNumber = request.getParameter("bankRoutingNumber");
		this.bankCustomerName = request.getParameter("bankCustName");
		this.bankAccountNumber = request.getParameter("bankAccountNumber");
		this.checkNumber = request.getParameter("checkNumber");		
		this.amount = request.getParameter("paymentAmount");
		this.orderAmount = request.getParameter("orderTotalAmount");
		this.orderNumber = request.getParameter("orderNumber");
		this.paymentSchedule = request.getParameter("paymentSchedule");
		this.nameOnCard = request.getParameter("nameOnCard");
		this.cardType  = request.getParameter("cardType");
		this.cardNumber = request.getParameter("cardNumber");
		this.expirationDate = request.getParameter("expirationDate");
		this.securityNumber = request.getParameter("securityNumber");
	}
	
	public Paymentmethod getPaymentMethod(){
		Paymentmethod paymentMethod = new Paymentmethod();
		paymentMethod.setPaymentType(getTypeOfPayment());
		
		return paymentMethod;
	}
	
	/**
	 * 
	 */
	public BankInformation buildBankInformation() {
		BankInformation bank = new BankInformation();
		bank.setAccountNumber(bankAccountNumber);
		bank.setBankName(bankName);
		bank.setBankNumber(bankRoutingNumber);
		bank.setCheckNumber(checkNumber);
		bank.setNameOnAccount(bankCustomerName);
		bank.setPaymentmethod(getPaymentMethod());
		return bank;
	}

	public CreditDebitCardInformation buildCardInformation(){
		CreditDebitCardInformation card = new CreditDebitCardInformation();
		
		card.setPaymentmethod(getPaymentMethod());
		card.setCardNumber(getCardNumber());
		card.setCareType(getCardType());
		card.setExpirationDate(getExpirationDate());
		card.setNameOnCard(getNameOnCard());
		card.setPaymentmethod(getPaymentMethod());
		card.setSecurityNumber(getSecurityNumber());
		
		return card;
	}
	public OrderPayment buildOrderPayment(CustomerOrder order,Employee employee){
		OrderPayment orderPayment = new OrderPayment();
		double paidAmount = 0.0;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		
		Date dateTime = new Date();
		paidAmount = Double.parseDouble(getAmount());
		orderPayment.setAmountPaid(paidAmount);
		orderPayment.setPaymentSchedule(paymentSchedule);
		orderPayment.setClientorder(order);
		orderPayment.setEmployee(employee);
		orderPayment.setPaymentMethod(getPaymentMethod());
		orderPayment.setDatePaid(dateFormatter.format(dateTime).toString());
		return orderPayment;
	}

	/**
	 * @return the typeOfPayment
	 */
	public String getTypeOfPayment() {

		return this.typeOfPayment;
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
	
	
}
