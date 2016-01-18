/**
 * hkboateng
 */
package com.boateng.abankus.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hkboateng
 *
 */
public class BillingCollection {

	private String customerNumber;
	
	
	private List<CustomerBilling> billing = new ArrayList<CustomerBilling>();
	
	private List<CustomerOrder> customerOrderList = new ArrayList<CustomerOrder>();
	/**
	 * Key is Order Number
	 * Value is list of CustomerBilling
	 */
	private Map<String,CustomerBilling> billingMap = new ConcurrentHashMap<String,CustomerBilling>();
	
	
	/**
	 * key - Customer Number
	 * value - List of CustomerBilling
	 */
	private Map<String,List<CustomerBilling>> billingListMap = new HashMap<String,List<CustomerBilling>>();
	
	public BillingCollection(CustomerOrder order){
		
		this.customerNumber = order.getCustomer().getCustomerNumber();
		addBilling(order);
	}
	
	public BillingCollection(){
		
	}
	public BillingCollection(List<CustomerOrder> orderList){
		if(!orderList.isEmpty()){
			for(CustomerOrder order: orderList){
				addBilling(order);
			}
		}
	}	


	public void addBilling(CustomerOrder order){
		if(order != null){
			String orderNumber = order.getOrderNumber();
			setCustomerNumber(order.getCustomer().getCustomerNumber());
			customerOrderList.add(order);
			
			if(billingMap.containsKey(orderNumber)){
				CustomerBilling bill = new CustomerBilling(order);
				billing.add(bill);
				billingListMap.put(orderNumber, billing);
				billingMap.put(orderNumber, bill);
			}else{
				CustomerBilling bill = new CustomerBilling(order);
				billing.add(bill);	
				billingListMap.put(orderNumber, billing);
				billingMap.put(orderNumber, bill);
			}
			
		}
	}
	
	public String getCustomerNumber(){
		return this.customerNumber;
	}
	
	public void setCustomerNumber(String customerNumber){
		this.customerNumber  = customerNumber;
	}	

	public CustomerBilling getCustomerBilling(String orderNumber){

		return getBillingMap().get(orderNumber);
	}

	public List<CustomerBilling> getCustomerBillingList(String orderNumber){

		return getBillingListMap().get(orderNumber);
	}
	public Map<String,CustomerBilling> getBillingMap(){
		return this.billingMap;
	}

	/**
	public List<OrderPayment> getPaymentList() {
		return paymentList;
	}
	**/

	public void setPaymentList(List<OrderPayment> paymentList) {
		for(OrderPayment payments :paymentList){
			addPaymentsToCustomerBilling(payments.getClientorder().getOrderNumber(),payments);
		}

	}
	public void addPaymentsToCustomerBilling(String orderNumber,OrderPayment payment){
		CustomerBilling customerBilling = getCustomerBilling(orderNumber);
		customerBilling.addPaymentToList(payment);
	}

	public Map<String, List<CustomerBilling>> getBillingListMap() {
		return billingListMap;
	}

	public void setBillingListMap(Map<String, List<CustomerBilling>> billingListMap) {
		this.billingListMap = billingListMap;
	}
	
	
}
