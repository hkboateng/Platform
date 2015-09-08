/**
 * hkboateng
 */
package com.boateng.abankus.domain.factory;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import com.boateng.abankus.customer.service.Client;
import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.domain.CustomerBilling;
import com.boateng.abankus.entity.validation.CustomerOrderUtils;
import com.boateng.abankus.utils.PlatformUtils;

/**
 * @author hkboateng
 *
 */
public class FactoryImpl extends Factory {

	Client client = null;

	@Override
	public Client construct(String domain, HttpServletRequest request) {
		if(domain.contentEquals(domain)){
			client = createCustomerBilling(request);
		}
		if(domain.equals("customerOrder")){
			client = createCustomerOrder(request);
		}
		return client;
	}


	/**
	 * @param request
	 * @return
	 */
	private Client createCustomerOrder(HttpServletRequest request) {
		String productCode = request.getParameter("product");
		String unitCost = request.getParameter("xUnitCost");
		String quantity = request.getParameter("quantity");
		//customerId = request.getParameter("customerId");
		String orderNumber = PlatformUtils.getClientOrderNumber();

		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setProductCode(productCode);
		customerOrder.setQuantity(Integer.parseInt(quantity));
		customerOrder.setOrderNumber(orderNumber);
		customerOrder.setUnitCost(unitCost);
		BigDecimal totalCost = CustomerOrderUtils.calculateTotalCost(unitCost, quantity);
		customerOrder.setTotalAmount(totalCost);
		//Date of Order
		Long dateInSecs = DateTime.now().getMillis();
		customerOrder.setOrderDate(dateInSecs);
		return customerOrder;
	}


	private CustomerBilling createCustomerBilling(HttpServletRequest request){
		return null;
	}

}
