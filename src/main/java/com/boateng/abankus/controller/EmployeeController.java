/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.employee.interfaces.AuthenticationService;
import com.boateng.abankus.employee.interfaces.EmployeeService;

/**
 * @author hkboateng
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired(required=true)
	@Qualifier(value="employeeServiceImpl")
	private EmployeeService employeeServiceImpl;	
	
	@RequestMapping(value="/listEmployee", method=RequestMethod.GET)
	public String getAllEmployees(Model model){
		List<Employee> listEmployee = employeeServiceImpl.getAllEmployee();
		model.addAttribute("employeeList", listEmployee);
		return "Employee/EmployeeList";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public String editEmployeeProfile(@RequestParam int employeeId,Model model){
		Employee employee = employeeServiceImpl.getEmployeeById(employeeId);
		model.addAttribute("employeeInstance", employee);
		
		return "Employee/EmployeeEditInfo";
	}	
}
