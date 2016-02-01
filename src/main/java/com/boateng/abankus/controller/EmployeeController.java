/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.EmployeeFields;
import com.boateng.abankus.processors.EmployeeServiceProcessor;
import com.boateng.abankus.services.EmployeeService;
import com.boateng.abankus.servlet.PlatformAbstractServlet;

/**
 * @author hkboateng
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController extends PlatformAbstractServlet{

	private static final Logger logger = Logger.getLogger(EmployeeController.class.getName());
	
	@Autowired(required=true)
	@Qualifier(value="employeeServiceImpl")
	private EmployeeService employeeServiceImpl;	

	@Autowired(required=true)
	private EmployeeServiceProcessor employeeServiceProcessor;
	
	@RequestMapping(value="/listEmployee", method=RequestMethod.GET)
	public String getAllEmployees(Model model,HttpServletRequest request) throws PlatformException{
		Employee employee = getEmployeeInSession(request);
		if(employee != null){
			long companyId = employee.getCompanyNumber();
			List<Employee> listEmployee = employeeServiceImpl.getAllEmployee(companyId);
			model.addAttribute("employeeList", listEmployee);			
		}else{
			
		}
		
		return "Employee/EmployeeList";
	}
	
	@RequestMapping(value="/editEmployee", method=RequestMethod.GET)
	public String editEmployeeProfile(HttpServletRequest request,Model model,RedirectAttributes redirectAttributess){
		logger.info("Loading and viewing Editing Employee Information...");
		Employee employee = null;
		try {
			employee = getEmployeeInSession(request);
			 model.addAttribute("employeeInstance", employee);
		} catch (PlatformException e) {
			 model.addAttribute("error_message","Error occured getting Employee information.");
			employee = null;
			e.printStackTrace();
		}
		
		
		return "Employee/EmployeeProfile";
	}	
	
	@RequestMapping(value="/updateEmployee", method=RequestMethod.POST)
	public String updateEmployee(HttpServletRequest request,Model model,RedirectAttributes redirectAttributess){
		logger.info("Updating Employee Information.....");
		employeeServiceProcessor.process("update", request);
		
		redirectAttributess.addFlashAttribute("info", "Employee has being added successfully.");
		return "redirect:/platform/dashboard";
	}

	
	@RequestMapping(value="/employeSearchView", method=RequestMethod.GET)
	public String employeSearchView(HttpServletRequest request,Model model,RedirectAttributes redirectAttributess){

		return "Employee/EmployeeSearch";
	}
	
	@RequestMapping(value="/findEmployee", method=RequestMethod.POST)
	public String findEmployee(HttpServletRequest request,Model model,RedirectAttributes redirectAttributess){
		String search = request.getParameter("employeeSearch");
		employeeServiceProcessor.findEmployeeDetails(search);
		return "Employee/EmployeeSearch";
	}

}
