package com.boateng.abankus.service.processor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.employee.interfaces.AuthenticationService;
import com.boateng.abankus.employee.interfaces.EmployeeService;

public class EmployeeServiceProcessor implements Runnable{

	@Autowired(required=true)
	@Qualifier(value="authenticationServiceImpl")
	private AuthenticationService authenticationServiceImpl;	

	@Autowired(required=true)
	@Qualifier(value="employeeServiceImpl")
	private EmployeeService employeeServiceImpl;
	ExecutorService executorService = Executors.newFixedThreadPool(10);
	
	public  Employee findEmployee(HttpServletRequest request){
		String username = request.getUserPrincipal().getName();
		Employee emp = authenticationServiceImpl.findEmployeeByUserName(username);
		
		return emp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public void process(HttpServletRequest request,Customer cus){
		
	}
	
	public List<Employee> getAllEmployee(){
		List<Employee> listEmployee = employeeServiceImpl.getAllEmployee();
		
		return listEmployee;
	}
}
