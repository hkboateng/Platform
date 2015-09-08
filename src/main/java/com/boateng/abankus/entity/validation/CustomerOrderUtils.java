/**
 * hkboateng
 */
package com.boateng.abankus.entity.validation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hkboateng
 *
 */
public class CustomerOrderUtils {
	private static final Logger logger = LoggerFactory.getLogger(CustomerOrderUtils.class);
	
	private List<String> validationList = null;
	
	private HttpServletRequest request = null;
	
	private String productCode  = null;
	
	private String unitCost  = null;
	
	private String quantity = null;
	
	private String customerId = null;
	
	public CustomerOrderUtils(HttpServletRequest request){
		this.request = request;
	}
	
	public List<String> validateOrder(){
		validationList = new ArrayList<String>();
		productCode = request.getParameter("product");
		unitCost = request.getParameter("xUnitCost");
		quantity = request.getParameter("quantity");
		customerId = request.getParameter("customerId");

		String productCodeMsg = validateProductCode(productCode);
		String quantityMsg = validateQuantity(quantity);
		String unitCostMsg = validateUnitCost(unitCost);
		String customerMsg = validateCustomerId(customerId);
		if(productCodeMsg != null){
			validationList.add( productCodeMsg);
		}
		if(quantityMsg != null){
			validationList.add(quantityMsg);
		}
		if(unitCostMsg != null){
			validationList.add(unitCostMsg);
		}
		return validationList;
	}
	
	private String validateProductCode(String productCode){
		if(productCode != null && !productCode.isEmpty()){
			return null;
		}
		
		return "Product Code cannot be null.";
		
	}
	
	private String validateQuantity(String quantity){
		if(quantity != null && !quantity.isEmpty()){
			try{
				Integer.parseInt(quantity);
				return null;
			}catch(NumberFormatException e){
				return e.getMessage();
			}
		}

		return "Order Quantity cannot be null or empty.";
	}
	
	private String validateUnitCost(String unitCost){
		if(unitCost != null || !" ".equals(null)){
			try{
				Float.parseFloat(unitCost);
				return null;
			}catch(NumberFormatException e){
				return e.getMessage();
			}
			
		}
		
		return "Unit Cost cannot be null or empty.";
		
	}
	
	private String validateCustomerId(String customer){
		
		return (customer != null && customer.equals(" ") ) ? null : "Error in processing your request.";
	}
	
	public static BigDecimal calculateTotalCost(String unitCost, String quant){
		BigDecimal cost = new BigDecimal(unitCost);
		BigDecimal quantity = new BigDecimal(quant);
		BigDecimal totalCost = cost.multiply(quantity);
		return totalCost;
	}
}
