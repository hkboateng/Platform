package com.boateng.abankus.services;

import java.util.Date;
import java.util.List;

import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerAccount;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.EmployeeCustomerAccount;
import com.boateng.abankus.domain.OrderPayment;
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

	/**
	 * List All Customers that have being assigned to an employee.
	 * Current Logic assigned a Customer to employee when an employee create the Customer.
	 * 
	 * TODO: Create new business logic for a Sales Manager or Manager to Reassign Customers to Employee.
	 * @param employeeId
	 * @return - List of Customers assigned to an Employee
	 */
	List<Customer> findAllCustomerByEmployeeId(int employeeId);
	
	List<OrderPayment> findAllPaymentByEmployeeAndDate(int employeeId);
}
