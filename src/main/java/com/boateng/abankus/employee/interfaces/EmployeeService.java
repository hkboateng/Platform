package com.boateng.abankus.employee.interfaces;

import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerAccount;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.User;


public interface EmployeeService {

	Employee saveEmployee(Employee e,User u, String[] roleId);

	/**
	 * @param employee
	 * @param customer
	 * @return
	 */
	CustomerAccount addEmployeeSalesAccount(Employee employee, Customer customer);


	
}
