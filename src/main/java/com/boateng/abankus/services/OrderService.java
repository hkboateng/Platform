/**
 * hkboateng
 */
package com.boateng.abankus.services;

import javax.servlet.http.HttpServletRequest;

import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.domain.Salesemployee;

/**
 * @author hkboateng
 *
 */
public interface OrderService {

	CustomerOrder orderService(HttpServletRequest request, String action);
	
	Salesemployee employeeSales(HttpServletRequest request,CustomerOrder customerOrder);
}
