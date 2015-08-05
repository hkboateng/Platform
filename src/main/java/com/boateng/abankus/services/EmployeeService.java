package com.boateng.abankus.services;

import java.util.List;

import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerAccount;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.User;


public interface EmployeeService {

	Employee saveEmployee(Employee e,User u, String[] roleId);


	/**
	 * @return
	 */
	List<Employee> getAllEmployee();

	Employee getEmployeeById(int employeeId);

	/**
	 * @param employee
	 * @param customer
	 * @param industry
	 * @param notes
	 * @return
	 */
	CustomerAccount addEmployeeSalesAccount(Employee employee,Customer customer, String industry, String notes);
	
}
