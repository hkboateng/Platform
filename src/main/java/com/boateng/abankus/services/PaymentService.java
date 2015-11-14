/**
 * hkboateng
 */
package com.boateng.abankus.services;

import java.util.List;

import com.boateng.abankus.domain.OrderPayment;
import com.boateng.abankus.domain.PaymentTransaction;
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

	/**
	 * @param from - Beginning search date
	 * @param to - Ending Date
	 * @return - List of payments 
	 */
	List<OrderPayment> findAllPaymentsByFromAndToDate(String from, String to);

	/**
	 * @param date
	 * @return
	 */
	List<PaymentTransaction> findAllPaymentsByDate(String date);

	/**
	 * @param year
	 * @param month
	 * @return
	 */
	List<PaymentTransaction> findAllMonthPaymentByYearAndMonth(String year, String month);

	/**
	 * @param year
	 * @return
	 */
	List<PaymentTransaction> findAllYearPaymentByYear(String year);
	
	PaymentTransaction findPaymentTransactionByTransactionId(String transactionId);
	
	List<PaymentTransaction> findPaymentTransactionByCustomerNumber(String customerNumber);
}
