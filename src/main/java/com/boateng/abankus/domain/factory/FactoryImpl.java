/**
 * hkboateng
 */
package com.boateng.abankus.domain.factory;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import com.boateng.abankus.application.interfaces.Billing;
import com.boateng.abankus.customer.service.Client;
import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.domain.CustomerBilling;
import com.boateng.abankus.domain.Email;
import com.boateng.abankus.domain.PaymentStatus;
import com.boateng.abankus.entity.validation.CustomerOrderUtils;
import com.boateng.abankus.fields.EmailFields;
import com.boateng.abankus.utils.PlatformUtils;

/**
 * @author hkboateng
 *
 */
public class FactoryImpl extends Factory {

	Client client = null;

	Billing billing = null;
	
	private static final FactoryImpl factory = new FactoryImpl();
	
	private FactoryImpl(){}
	
	public static FactoryImpl getFactory(){
		return factory;
	}
	
	@Override
	public CustomerBilling customerBilling(CustomerOrder customerOrder) {
		CustomerBilling billing = null;
		if(customerOrder != null){
			billing = new CustomerBilling(customerOrder);
		}

		return billing;
	}

	public Email addEmail(String emailAddress,String emailType){
		Email email = new Email(emailAddress);
		email.setEmailType(emailType);
		if(email.getEmailType() == null || email.getEmailType().isEmpty()){
			email.setEmailType(EmailFields.PRIMARY_EMAIL);
		}
		return email;
	}

	/**
	 * @param request
	 * @return
	 */
	private Client createCustomerOrder(HttpServletRequest request) {
		String productCode = request.getParameter("product");
		String unitCost = request.getParameter("xUnitCost");
		String quantity = request.getParameter("quantity");
		String orderNumber = PlatformUtils.getClientOrderNumber();
		String paymentSchedule = request.getParameter("paymentSchedule");
		String category = request.getParameter("productCategory");
		String nextPaymentDate = null;
		String paymentStatus = request.getParameter("paymentStatus");
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setProductCode(productCode);
		
		if(paymentStatus == null){
			customerOrder.setPaymentStatus(PaymentStatus.REPAYMENT.name());
		}
		if(category == "Product"){
			if(quantity != null){
				customerOrder.setQuantity(Integer.parseInt(quantity));
			}	
			if(quantity != null && unitCost != null){
				BigDecimal totalCost = CustomerOrderUtils.calculateTotalCost(unitCost, quantity);
				customerOrder.setTotalAmount(totalCost);
			}			
		}
		if(quantity != null){
			customerOrder.setQuantity(Integer.parseInt(quantity));
		}	
		if(quantity != null && unitCost != null){
			BigDecimal totalCost = CustomerOrderUtils.calculateTotalCost(unitCost, quantity);
			customerOrder.setTotalAmount(totalCost);
		}
		customerOrder.setOrderNumber(orderNumber);
		if(paymentSchedule != null){
			customerOrder.setPaymentFrequency(paymentSchedule);
		}
		nextPaymentDate=calculateNextPaymentDate(paymentSchedule);
		customerOrder.setNextPaymentDate(nextPaymentDate);
		

		//Date of Order
		Long dateInSecs = DateTime.now().getMillis();
		customerOrder.setOrderDate(dateInSecs);
		

		return customerOrder;
	}


	/* (non-Javadoc)
	 * @see com.boateng.abankus.domain.factory.Factory#construct(java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Client construct(String domain, HttpServletRequest request) {
		if(domain.equals("customerOrder")){
			client = createCustomerOrder(request);
		}
		return client;
	}

	private String calculateNextPaymentDate(String paymentSchedule){
		DateTime dateTime = new DateTime();
		String nextPaymentDate = null;
		if(paymentSchedule != null){
			if(paymentSchedule.equals("daily")){
				dateTime = dateTime.plusDays(1);
			}else if(paymentSchedule.equals("monthly")){
				dateTime = dateTime.plusDays(30);
			}else if(paymentSchedule.equals("fullPayment")){
				dateTime = dateTime.plusDays(0);
			}
		}else{
			//If paymentSchedule is null, payment is due in the next 30 days
			dateTime = dateTime.plusDays(30);			
		}
		nextPaymentDate = dateTime.toString("MM/dd/yyyy");
		return nextPaymentDate;
	}
}
