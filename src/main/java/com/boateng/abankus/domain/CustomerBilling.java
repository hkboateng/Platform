/**
 * hkboateng
 */
package com.boateng.abankus.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boateng.abankus.application.interfaces.Billing;
import com.boateng.abankus.customer.service.Client;

/**
 * @author hkboateng
 *
 */
public class CustomerBilling implements Billing {

	private String billingId;
	
	private Customer customer;
	
	private CustomerOrder clientOrderId;
	
	private Date orderDate;
	
	private String totalOrderAmount;
	
	private List<OrderPayment> payments;

	private Map<String, OrderPayment> paymentMap;
	
	public CustomerBilling(Customer customer){
		this.customer = customer;
		payments = new ArrayList<OrderPayment>();
	}

	/**
	 * @return the billingId
	 */
	public String getBillingId() {
		return billingId;
	}

	/**
	 * @param billingId the billingId to set
	 */
	public void setBillingId(String billingId) {
		this.billingId = billingId;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the clientOrderId
	 */
	public CustomerOrder getClientOrderId() {
		return clientOrderId;
	}

	/**
	 * @param clientOrderId the clientOrderId to set
	 */
	public void setClientOrderId(CustomerOrder clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the totalOrderAmount
	 */
	public String getTotalOrderAmount() {
		return totalOrderAmount;
	}

	/**
	 * @param totalOrderAmount the totalOrderAmount to set
	 */
	public void setTotalOrderAmount(String totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}

	/**
	 * @return the payments
	 */
	public List<OrderPayment> getPayments() {
		return payments;
	}



	/**
	 * @return the paymentMap
	 */
	public Map<String, OrderPayment> getPaymentMap() {
		return paymentMap;
	}

	
	public void addPaymentToMap(OrderPayment payment){
		
	}

	public void addPaymentToList(OrderPayment payment){
		if(payment.getClientorder().getOrderNumber().equals(getClientOrderId().getOrderNumber())){
			payments.add(payment);
		}
	}
}
