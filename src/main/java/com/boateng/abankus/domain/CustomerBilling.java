/**
 * hkboateng
 */
package com.boateng.abankus.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boateng.abankus.customer.service.Client;

/**
 * @author hkboateng
 *
 */

public class CustomerBilling {

	private String billingId;
	
	private Customer customer;
	
	private CustomerOrder clientOrderId;
	
	private Date orderDate;
	
	private String totalOrderAmount;
	
	private List<OrderPayment> payments;

	private Map<String, OrderPayment> paymentMap;
	
	public CustomerBilling(Customer customer){
		this.customer = customer;
	}

}
