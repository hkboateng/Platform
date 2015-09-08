/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.fields.EmployeeFields;
import com.boateng.abankus.processors.EmployeeServiceProcessor;
import com.boateng.abankus.services.EmployeeService;

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

	@Autowired(required=true)
	private EmployeeServiceProcessor employeeServiceProcessor;
	
	@RequestMapping(value="/listEmployee", method=RequestMethod.GET)
	public String getAllEmployees(Model model){
		List<Employee> listEmployee = employeeServiceImpl.getAllEmployee();
		model.addAttribute("employeeList", listEmployee);
		return "Employee/EmployeeList";
	}
	
	@RequestMapping(value="/editEmployee", method=RequestMethod.GET)
	public String editEmployeeProfile(HttpServletRequest request,Model model){

		Employee employee = getEmployeeInSession(request);
		model.addAttribute("employeeInstance", employee);
		
		return "Employee/UpdateEmployee";
	}	
	@RequestMapping(value="/updateEmployee", method=RequestMethod.POST)
	public String updateEmployee(HttpServletRequest request,Model model,RedirectAttributes redirectAttributess){
		employeeServiceProcessor.process("update", request);
		
		redirectAttributess.addAttribute("info", "Employee has being added successfully.");
		return "redirect:/platform/dashboard";
	}
	
	private Employee getEmployeeInSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		Employee employee = (Employee) session.getAttribute(EmployeeFields.EMPLOYEE_SESSION) ;
		
		return employee;
	}
}
