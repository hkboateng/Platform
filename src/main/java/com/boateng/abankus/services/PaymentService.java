/**
 * hkboateng
 */
package com.boateng.abankus.services;

import java.util.List;

import com.boateng.abankus.domain.OrderPayment;
import com.boateng.abankus.domain.Paymentmethod;

/**
 * @author hkboateng
 *
 */
public interface PaymentService {

	String submitPayment(OrderPayment payment, Paymentmethod paymentMethod);
	
	List<OrderPayment> findPaymentsByOrderId(long customerOrderId);

	/**
	 * @param customerId
	 * @return
	 */
	List<OrderPayment> findPaymentsByOrderId(int customerId);
}
