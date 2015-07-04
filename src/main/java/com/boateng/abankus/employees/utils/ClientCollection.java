/**
 * hkboateng
 */
package com.boateng.abankus.employees.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boateng.abankus.domain.Customer;

/**
 * This Collection is use to store all the clients or customers assigned to an employee.
 * @author hkboateng
 *
 */
public class ClientCollection {

	private String employeeId;
	
	private Map<String, List<Customer>> employeeClientMap = new HashMap<String,List<Customer>>();
	
	public ClientCollection(){
		//Do Nothing
	}
	
	public ClientCollection(String employeeId){
		this.employeeId = employeeId;
	}
	
	/**
	 * 
	 * @return List of Client assigned to an Employee
	 */
	public List<Customer> getAllClients(){
		if(!employeeClientMap.containsKey(employeeId)){
			return null;
		}
		return employeeClientMap.get(employeeId);
	}
	
	public boolean hasClient(String customerId){
		if(employeeClientMap.isEmpty()){
			return false;
		}
		
		for(Customer clientList: employeeClientMap.values().iterator().next()){
			if(clientList.getCustomerNumber().equals(customerId)){
				return true;
			}
		}
		return false;
	}
}
