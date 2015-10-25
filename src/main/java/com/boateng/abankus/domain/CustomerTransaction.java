/**
 * hkboateng
 */
package com.boateng.abankus.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hkboateng
 *
 */
public class CustomerTransaction {

	private String transactionId;
	
	private String customerNumber;
	
	private List<CustomerOrder> customerOrder;

	private List<OrderPayment> orderPayment;
	
	private Map<String,List<OrderPayment>> orderPaymentMap;
	
	public CustomerTransaction(CustomerOrder order, OrderPayment payment){
		orderPayment = new ArrayList<OrderPayment>();
		orderPaymentMap = new ConcurrentHashMap<String, List<OrderPayment>>();
		addOrderAndPayment(order,payment);
	}

	
	public Map<String,List<OrderPayment>> getOrderPayment(){
		return orderPaymentMap;
	}
	public List<OrderPayment> getOrderPayment(String orderNumber){
		return getOrderPayment().get(orderNumber);
	}
	private void addOrderAndPayment(CustomerOrder order, OrderPayment payment){
		List<OrderPayment> orderPayment = null;
		if(orderPaymentMap.containsKey(order.getOrderNumber())){
			orderPayment = orderPaymentMap.get(order.getOrderNumber());
			orderPayment.add(payment);
			orderPaymentMap.put(order.getOrderNumber(), orderPayment);
		}else{
			orderPayment =   new ArrayList<OrderPayment>();
			orderPayment.add(payment);
			orderPaymentMap.put(order.getOrderNumber(), orderPayment);
		}
	}
	
	public void addCustomerOrder(CustomerOrder order){
		if(!customerOrder.contains(order.getOrderNumber())){
			customerOrder.add(order);
		}
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
	
	

}
