/**
 * hkboateng
 */
package com.boateng.abankus.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.boateng.abankus.application.interfaces.Billing;

/**
 * @author hkboateng
 *
 */
public class CustomerBilling implements Billing {

	private String billingId;
	
	private Customer customer;
	
	private CustomerOrder clientOrderId;
	
	private DateTime orderDate;
	
	private String totalOrderAmount;
	
	private List<OrderPayment> payments;

	private Map<String, OrderPayment> paymentMap;
	
	public CustomerBilling(CustomerOrder clientOrderId){
		payments = new ArrayList<OrderPayment>();
		if(clientOrderId != null){
			this.clientOrderId = clientOrderId;
			this.orderDate = convertDateFromLong(clientOrderId);
			this.totalOrderAmount = clientOrderId.getTotalAmount().toString();
			this.customer = clientOrderId.getCustomer();
			//addPaymentToList();
		}
	}

	/**
	 * @param payments the payments to set
	 */
	public void setPayments(List<OrderPayment> payments) {
		this.payments = payments;
	}

	private DateTime convertDateFromLong(CustomerOrder order){
		DateTime dateTime = new DateTime(order.getOrderDate());
		
		return dateTime;
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
	public DateTime getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(DateTime orderDate) {
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
		if(getClientOrderId().getOrderNumber().equals(payment.getClientorder().getOrderNumber())){
			payments.add(payment);
		}
	}
	
	public float totalAmountPaid(){
		float total = 0.0f;
		if(!getPayments().isEmpty()){
			
			for(OrderPayment payment: payments){
				total +=payment.getAmountPaid();
			}
		}
		
		return total;
	}
	
	public double totalAmountRemaining(){
		double total = 0.00;
		BigDecimal d = null;
		if(!finishPaying()){
			double amountPaid = totalAmountPaid();
			double totalAmount = Double.valueOf(getTotalOrderAmount());
			
			total = totalAmount - amountPaid;
			d = new BigDecimal(total);
			d = d.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		
		return d.doubleValue();
	}
	
	public boolean finishPaying(){
		boolean finished = false;
		float totalAmount = Float.valueOf(getTotalOrderAmount());
		if(totalAmount == totalAmountPaid()){
			finished = true;
		}
		return finished;
	}
}
