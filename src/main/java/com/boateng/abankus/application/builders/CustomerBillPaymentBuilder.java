/**
 * hkboateng
 */
package com.boateng.abankus.application.builders;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.boateng.abankus.domain.CustomerBilling;
import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.OrderPayment;
import com.boateng.abankus.domain.Paymentmethod;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.EmployeeFields;

/**
 * @author hkboateng
 *
 */
public class CustomerBillPaymentBuilder {

	private static final Logger logger = Logger.getLogger(CustomerBillPaymentBuilder.class.getName());
	
	public CustomerBillPaymentBuilder(){
		
	}
	public CustomerBilling buildBillPayment(){
		return null;
	}
	public String submitPayment(HttpServletRequest request) throws PlatformException{
		String status = null;
		
		String amount = request.getParameter("paymentAmount");
		String orderAmount = request.getParameter("orderTotalAmount");
		String orderNumber = request.getParameter("orderNumber");
		String typeOfPayment = request.getParameter("paymentSchedule");
		String formOfPayment = request.getParameter("paymentType");
		HttpSession session = request.getSession(false);
		
		Employee employee = (Employee) session.getAttribute(EmployeeFields.EMPLOYEE_SESSION);
		double paidAmount = Double.parseDouble(amount);
		
		//CustomerOrder order = customerOrderServiceImpl.findCustomerOrderByOrderNumber(orderNumber);

		OrderPayment orderPayment = new OrderPayment(paidAmount,employee,null,typeOfPayment);
		Paymentmethod paymentMethod = new Paymentmethod();
		/**
		 * Whether payment is made in cash or CC or Bank
		 */
		if(typeOfPayment.equalsIgnoreCase("cash")){
			paymentMethod.setPaymentType(typeOfPayment);
		}else if(formOfPayment.equalsIgnoreCase("bank")){
			
		}
		//paymentMethod.setOrderNumber(order.getOrderNumber());
		logger.info("Employee: "+employee.getEmployeeId()+ " is submitting a payment of: "+paidAmount+" for order number: "+orderNumber);
		//status = paymentProcessor.submitPayment(amount, orderAmount, orderNumber, formOfPayment, typeOfPayment,employee);
		return status;
	}
}
