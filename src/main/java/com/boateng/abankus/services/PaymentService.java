/**
 * hkboateng
 */
package com.boateng.abankus.services;

import com.boateng.abankus.domain.OrderPayment;
import com.boateng.abankus.domain.Paymentmethod;

/**
 * @author hkboateng
 *
 */
public interface PaymentService {

	String submitPayment(OrderPayment payment, Paymentmethod paymentMethod);
}