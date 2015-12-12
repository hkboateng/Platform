/**
 * hkboateng
 */
package com.boateng.abankus.services;

import java.util.List;

import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerOrder;

/**
 * @author hkboateng
 *
 */
public interface CustomerOrderService {

	CustomerOrder saveCustomerOrder(CustomerOrder order,Integer customerId);
	

	/**
	 * @param customerId
	 * @return
	 */
	List<CustomerOrder> findAllCustomerOrderByCustomerId(int customerId);


	
	CustomerOrder findCustomerOrderByOrderNumber(String orderNumber);
}
