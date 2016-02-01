package com.boateng.abankus.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.Permission;
import com.boateng.abankus.domain.Role;
import com.boateng.abankus.domain.User;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.PlatformFields;
import com.boateng.abankus.processors.EmployeeServiceProcessor;
import com.boateng.abankus.services.AuthenticationService;
import com.boateng.abankus.services.EmployeeService;
import com.boateng.abankus.servlet.PlatformAbstractServlet;

@Controller
@RequestMapping("/registration")
public class RegistrationController  extends PlatformAbstractServlet  {
	
	private static final Logger logger = Logger.getLogger(RegistrationController.class.getCanonicalName());
	
	@Autowired
	@Qualifier(value="employeeSvcImpl")
	EmployeeService employeeSvcImpl;

	@Autowired(required=true)
	@Qualifier(value="authenticationServiceImpl")
	private AuthenticationService authenticationServiceImpl;
	
	//private CustomerService customerSvc;
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public String employee(HttpServletRequest request,Model model) throws PlatformException {
		logger.info(logActivity("is accessing the Add New Employee page.",getEmployeeInSession(request)));
		
		return "Employee/AddNewEmployee";
	}
	
	@RequestMapping(value="addEmployee", method=RequestMethod.POST)
	public String addEmployee(@Valid Employee employee,BindingResult result,HttpServletRequest request,RedirectAttributes redirectAttributess) throws IOException, PlatformException{
		HttpSession session = request.getSession();
		
		if(result.hasErrors()){
			redirectAttributess.addFlashAttribute("validationError", result.getAllErrors());
			return "redirect:/registration/employee";
		}
		boolean isEmailUsed =  authenticationServiceImpl.doEmailExist((employee.getEmailAddress()));
		if(isEmailUsed){
			redirectAttributess.addFlashAttribute("error_message", "Email Address already in use.");
			return "redirect:/registration/employee";
		}	
		
		logger.info(logActivity("is Saving New Employee: "+employee.toString(),getEmployeeInSession(request)));
		List<Permission> permissionList = authenticationServiceImpl.getEmployeePermissions();
		if(permissionList.size() > 0){
			redirectAttributess.addFlashAttribute("permissionList", permissionList);
		}
		session.setAttribute("employeeInstance", employee);
		return "redirect:/registration/security";
	}
	
	@RequestMapping(value="/addEmployeeLogin", method=RequestMethod.POST)
	public String addEmployeeLogin(@Valid User login, BindingResult result,HttpServletRequest request,RedirectAttributes redirectAttributess) throws PlatformException, IOException{
		HttpSession session = request.getSession();
		String[] roleId = request.getParameterValues("role");
		if(result.hasErrors()){
			redirectAttributess.addFlashAttribute("validationError", result.getAllErrors());
			return "redirect:/registration/security";
		}
		
		boolean usernameExist =  authenticationServiceImpl.doUserNameExist((login.getUsername()));
		if(usernameExist){
			redirectAttributess.addFlashAttribute(PlatformFields.ERROR_MESSAGE, "Username already exist.");
			redirectAttributess.addFlashAttribute("userInstance",login);
			return "redirect:/registration/security";
		}
		
		Employee employee = (Employee) session.getAttribute("employeeInstance");
		Employee currentUser = getEmployeeInSession(request);
		if(employee !=null){
			User user = EmployeeServiceProcessor.getInstance().saveEmployeeLogin(login,employee);
			user.setCompanyId(currentUser.getCompanyNumber());
			employee = employeeSvcImpl.saveEmployee(employee,user,roleId);
				if(employee == null){
					return "Employee/AddNewEmployee";
				}
				
		}else{
			
		}
		redirectAttributess.addAttribute(PlatformFields.SUCCESS_MESSAGE, "Congratulations on registering. Please Login !!!");
		return "redirect:/employee/listEmployee";
	}
	
	@RequestMapping(value="/security", method=RequestMethod.GET)
	public String securityInfo(HttpServletRequest request){
		return "Employee/SecurityInfo";
	}
	@RequestMapping(value="newCustomer", method=RequestMethod.GET)
	public ModelAndView newCustomer(@Valid Customer customer, BindingResult result){
		ModelAndView model = new ModelAndView();
		model.setViewName("Customers/NewCustomer");
		logger.info("Viewing Adding New Customers page....");
		return model;
	}


}
