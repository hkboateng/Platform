package com.boateng.abankus.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.Role;
import com.boateng.abankus.domain.User;
import com.boateng.abankus.processors.EmployeeServiceProcessor;
import com.boateng.abankus.services.AuthenticationService;
import com.boateng.abankus.services.EmployeeService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	
	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	@Qualifier(value="employeeSvcImpl")
	EmployeeService employeeSvcImpl;

	@Autowired(required=true)
	@Qualifier(value="authenticationServiceImpl")
	private AuthenticationService authenticationServiceImpl;
	
	//private CustomerService customerSvc;
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public String employee(Locale locale,Model model) {
		logger.info("User view Registation page {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "Employee/AddNewEmployee";
	}
	
	@RequestMapping(value="addEmployee", method=RequestMethod.POST)
	public String addEmployee(@Valid Employee employee,BindingResult result,HttpServletRequest request){
		HttpSession session = request.getSession();
		if(result.hasErrors()){

			return "Employee/AddNewEmployee";
		}
		boolean isEmailUsed =  authenticationServiceImpl.doEmailExist((employee.getEmail()));
		if(isEmailUsed){
			request.setAttribute("errors", "Email Address already exist in system.");
			return "Employee/AddNewEmployee";
		}	
		
		logger.info("Saving New Employee data");
		List<Role> roleList = authenticationServiceImpl.getAllRoles();
		if(roleList.size() > 0){
			session.setAttribute("roleList", roleList);
		}
		session.setAttribute("employee", employee);
		return "Employee/SecurityInfo";
	}
	
	@RequestMapping(value="addEmployeeLogin", method=RequestMethod.POST)
	public String addEmployeeLogin(@Valid User login, BindingResult result,HttpServletRequest request){
		HttpSession session = request.getSession();
		String[] roleId = request.getParameterValues("role");
		if(result.hasErrors()){
			
			return "Employee/SecurityInfo";
		}
		
		boolean usernameExist =  authenticationServiceImpl.doEmailExist((login.getUsername()));
		if(usernameExist){
			request.setAttribute("errors", "Username already exist in system.");
			return "Employee/SecurityInfo";
		}
		
		Employee employee = (Employee) session.getAttribute("employee");
		
		if(employee !=null){
			User user = EmployeeServiceProcessor.getInstance().saveEmployeeLogin(login,employee);
			employee = employeeSvcImpl.saveEmployee(employee,user,roleId);
				if(employee == null){
					return "Employee/AddNewEmployee";
				}
				
		}else{
			
		}
		request.setAttribute("success", "Congratolations on registering. Please Login !!!");
		return "index";
	}
	
	@RequestMapping(value="newCustomer", method=RequestMethod.GET)
	public ModelAndView newCustomer(@Valid Customer customer, BindingResult result){
		ModelAndView model = new ModelAndView();
		model.setViewName("Customers/NewCustomer");
		logger.info("Viewing Adding New Customers page....");
		return model;
	}


}
