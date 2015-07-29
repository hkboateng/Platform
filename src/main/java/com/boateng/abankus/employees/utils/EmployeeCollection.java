/**
 * hkboateng
 */
package com.boateng.abankus.employees.utils;

import java.util.HashMap;
import java.util.Map;

import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.exception.PlatformException;

/**
 * @author hkboateng
 *
 */
public class EmployeeCollection {

	private Map<String,Employee> employeeMap = new HashMap<String, Employee>();
	
	private static final EmployeeCollection employeeCollection = new EmployeeCollection();
	
	private EmployeeCollection(){}
	
	public static EmployeeCollection getInstance(){
		return employeeCollection;
	}
	
	public void addEmployee(String username,Employee e){
		if(username != null && e != null){
			employeeMap.put(username, e);
		}
	}
	
	public Employee getEmployeeCollectionByUsername(String username){
		if(employeeMap.containsKey(username)){
			return employeeMap.get(username);
		}
		return null;
	}

	public boolean isEmployeeSaved(String username){
		return employeeMap.containsKey(username);
	}
	
	public void removeEmployeeFromCollection(String username){
		if(employeeMap.containsKey(username)){
			employeeMap.remove(username);
		}else{
			PlatformException e = new PlatformException();
			e.printStackTrace();
		}
	}
}
