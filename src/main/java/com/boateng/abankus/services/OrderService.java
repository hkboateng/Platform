/**
 * hkboateng
 */
package com.boateng.abankus.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.domain.OrderPayment;
import com.boateng.abankus.domain.Salesemployee;

/**
 * @author hkboateng
 *
 */
public interface OrderService {

	CustomerOrder orderService(HttpServletRequest request, String action);
	
	Salesemployee employeeSales(HttpServletRequest request,CustomerOrder customerOrder);

	/**
	 * @param order
	 * @return
	 */
	List<OrderPayment> getAllPaymentByCustomerOrder(CustomerOrder order);

	/**
	 * @param customerId
	 * @return
	 */
	List<OrderPayment> getAllPaymentByCustomerOrder(int customerId);
	
	List<OrderPayment> findAllPaymentByOrderNumber(String orderNumber);
}
