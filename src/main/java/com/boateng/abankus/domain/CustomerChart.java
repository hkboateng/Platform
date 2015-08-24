/**
 * hkboateng
 */
package com.boateng.abankus.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hkboateng
 *
 */
public class CustomerChart {

	private String customerId;
	
	//Key is CustomerId and Value is Product object
	private Map<String,List<Product>> customerChart = new HashMap<String, List<Product>>();
	
	private String employeeId;
	
	private String dateCreated;


	/**
	 * @return the customerChart
	 */
	public Map<String, List<Product>> getCustomerChart() {
		return customerChart;
	}

	/**
	 * @param customerChart the customerChart to set
	 */
	public void setCustomerChart(Map<String, List<Product>> customerChart) {
		this.customerChart = customerChart;
	}

	/**
	 * @return the dateCreated
	 */
	public String getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @param customerId
	 * @param employeeId
	 * @param dateCreated
	 */
	public CustomerChart(String customerId, String employeeId,String dateCreated) {
		this.customerId = customerId;
		this.employeeId = employeeId;
		this.dateCreated = dateCreated;
	}
	
	public void addProductToChart(String customerId, Product product){
		List<Product> productList = null;
		if(customerChart.containsKey(customerId)){
			productList = new ArrayList<Product>();
			if(!productList.contains(product)){
				productList.add(product);
			}
			customerChart.put(customerId, productList);
		}
	}
	
	public List<Product> getCustomerProducts(String customerId){
		if(!customerChart.containsKey(customerId)){
			return null;
		}
		return customerChart.get(customerId);
	}
	
	public boolean removeCustomerProductsFromChart(String customerId){
		boolean isRemovedItems = false;
		if(customerChart.containsKey(customerId)){
			List<Product> isRemoved = customerChart.remove(customerId);
			if(isRemoved == null || isRemoved.isEmpty()){
				isRemovedItems = true;
			}
		}	

		return isRemovedItems;
	}
}
