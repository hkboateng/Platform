package com.boateng.abankus.services;

import java.util.List;

import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerAccount;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.EmployeeCustomerAccount;
import com.boateng.abankus.domain.Salesemployee;
import com.boateng.abankus.domain.User;


public interface EmployeeService {

	Employee saveEmployee(Employee e,User u, String[] roleId);


	/**
	 * @return
	 */
	List<Employee> getAllEmployee();

	Employee getEmployeeById(int employeeId);


	/**
	 * @param Id
	 * @param employee
	 */
	void updateEmployeeByIdAndEmployeeId(Integer Id, Employee employee);


	/**
	 * @param employeeSale
	 * @return 
	 */
	Salesemployee saveEmployeeSales(Salesemployee employeeSale);


	
}
