/**
 * hkboateng
 */
package com.boateng.abankus.customerorders.utils;

import org.joda.time.DateTime;

import com.boateng.abankus.domain.CustomerOrder;

/**
 * @author hkboateng
 *
 */
public class CustomerOrderUtils {

	private static final long TIME_TO_CANCEL_ORDER  =1800000;
	
	public static boolean isOrderPending(CustomerOrder order){

		DateTime dt = new DateTime(order.getOrderDate());
		boolean isPending  = false;
		if(order != null){
			isPending = dt.isAfter(TIME_TO_CANCEL_ORDER);
		}
		return isPending;
	}
	
	
}
