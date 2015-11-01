/**
 * hkboateng
 */
package com.boateng.abankus.fields;

/**
 * @author hkboateng
 *
 */
public class MessagingFields {
	public static final String PAYMENT_CENTER_ROUTING_KEY = "paymentcenter.billing.makepayment";
	
	public static final String PAYMENT_CENTER_EXCHANGE_NAME = "paymentcenter";
	
	/** Routing Key for Making a Payment **/
	public static final String PAYMENT_CENTER_MAKE_PAYMENT_KEY = "paymentcenter.billing.makepayment";
	
	public static final String PAYMENT_CENTER_MAKE_PAYMENT_QUEUE = "addCustomerBilling_Payment_Queue";
}
