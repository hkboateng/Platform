package com.boateng.abankus.customer.service;


/** Client is an Interface for all Customers of the Business/Company ***/

public interface Client{

	/**
	 * Checks to see if Customer is allowed to place an order.
	 * 
	 * @return true if Customer is in good standing to make purchase or order, else false.
	 
     boolean isCustomerInGoodStanding();
     
     void setCustomerInGoodStanding();
     
     CustomerCollection getCustomerCollection();
     
     void setCustomerCollection(CustomerCollection collection);
     
     String getCustomerNumber();
	
 	 void setCustomerNumber(String customerNumber);
 	
 	 Long getCustomerId();   
 	 */  
}
