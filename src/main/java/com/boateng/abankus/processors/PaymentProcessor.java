/**
 * hkboateng
 */
package com.boateng.abankus.processors;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.utils.ValidationUtils;

/**
 * @author hkboateng
 *
 */
public class PaymentProcessor {

	private List<String> errors = null;
	
	public List<String> validateOrderPayment(HttpServletRequest request){
		String customerPin = request.getParameter("customerPIN");
		String amount = request.getParameter("paymentAmount");
		String orderAmount = request.getParameter("orderTotalAmount");
		String orderNumber = request.getParameter("orderNumber");
		String typeOfPayment = request.getParameter("paymentSchedule");
		String formOfPayment = request.getParameter("paymentForm");
		List<String> validation = validPayment(customerPin,amount,orderAmount,orderNumber,typeOfPayment,formOfPayment);

		
		return validation;
	}
	
	/**
	 * @param customerPin
	 * @param amount
	 * @param orderAmount
	 * @param orderNumber
	 * @param typeOfPayment
	 * @param formOfPayment
	 * @return
	 */
	private List<String> validPayment(String customerPin, String amount,String orderAmount, String orderNumber, String typeOfPayment,String formOfPayment) {
		errors = new ArrayList<String>();
		
		if(ValidationUtils.isNullOrBlank(customerPin)){
			errors.add("Customer Pin cannot be null");
		}
		if(ValidationUtils.isNullOrBlank(amount)){
			errors.add("Amount to be paid cannot be null.");
		}
		if(ValidationUtils.isNullOrBlank(typeOfPayment)){
			errors.add("Type of Payment cannot be null");
		}
		if(ValidationUtils.isNullOrBlank(orderNumber)){
			errors.add("Amount to be paid cannot be null.");
		}		
		if(ValidationUtils.isNullOrBlank(formOfPayment)){
			errors.add("Form of Payment to be paid cannot be null.");
		}		
		
		String valids = comparePaymentAndAmountDue(amount,orderAmount);
		if(valids != null){
			errors.add(valids);
		}
		return errors;
	}
	
	public String comparePaymentAndAmountDue(String amountPaid,String orderAmount){
		double paidAmount = 0.0;
		double totalAmount = 0.0;
		StringBuilder sbr = new StringBuilder();
		try{
			totalAmount = Double.parseDouble(orderAmount);
			paidAmount = Double.parseDouble(amountPaid);
			if(paidAmount > totalAmount){
				sbr.append("You cannot pay more than what you owe or more than the Order amount.");
			}
			
		}catch(NumberFormatException e){
			PlatformException ace = new PlatformException(e);
			sbr.append("Amount Entered contains invalid characters!!. Please check and try again!!!.");
		}
		return sbr.toString();
	}
}
