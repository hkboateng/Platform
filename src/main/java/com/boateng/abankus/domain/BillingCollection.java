/**
 * hkboateng
 */
package com.boateng.abankus.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.utils.SecurityUtils;

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

	public void addPaymentsToCustomerBilling(String orderNumber,OrderPayment payment){
		CustomerBilling customerBilling = getCustomerBilling(orderNumber);
		customerBilling.addPaymentListToMap(payment);
	}
	public void addBilling(CustomerOrder order){
		if(order != null){
			setCustomerNumber(order.getCustomer().getCustomerNumber());
			customerOrderList.add(order);
			
			if(billingMap.containsKey(order.getOrderNumber())){
				CustomerBilling bill = new CustomerBilling(order);
				billingMap.put(order.getOrderNumber(), bill);
			}else{
				CustomerBilling bill = new CustomerBilling(order);
				billing.add(bill);		
				billingMap.put(order.getOrderNumber(), bill);
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

	public List<OrderPayment> getCustomerBillingPayment(String orderNumber){
		
		return getCustomerBilling(orderNumber).getPayments();
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
	
}
