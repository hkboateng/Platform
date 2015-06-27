package com.boateng.abankus.employees.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.User;
import com.boateng.abankus.domain.UserRole;
import com.boateng.abankus.employee.interfaces.EmployeeService;
import com.boateng.abankus.exception.AbankusException;
import com.boateng.abankus.utils.SecurityUtils;

@Component
public class EmployeeProcessor extends AbankusAbstract {


	@Autowired
	@Qualifier(value="employeeSvcImpl")
	private EmployeeService employeeSvcImpl;

	/**
	 * @param employeeSvcImpl the employeeSvcImpl to set
	 */
	public void setEmployeeSvcImpl(EmployeeService employeeSvcImpl) {
		this.employeeSvcImpl = employeeSvcImpl;
	}
	public static EmployeeProcessor getInstance(){
		return employeeService;
	}
	
	HttpSession session = null;
	private static final EmployeeProcessor employeeService = new EmployeeProcessor();
	private EmployeeProcessor(){}
	/**
	 * This method is used to validate New Employee
	 * Registration data.
	 * 
	 * @param request
	 * @param response
	 * @throws AbankusException 
	 */
	public void  processEmployeeRegistration(HttpServletRequest request,HttpServletResponse response){
		List<String> validEmployee = EmployeeValidation.getInstance().validPersonalInformation(request);
		if(validEmployee.size() > 0){
			session = request.getSession();
			session.setAttribute("errors", validEmployee);
			 nextPage(request,response,false,true);
			 return;
		}
		Employee employee = createEmployeeObject(request);

	}
	


	public Employee createEmployeeObject(HttpServletRequest request){
		String firstname = request.getParameter("firstname").trim();
		String lastname = request.getParameter("lastname").trim();
		String middlename = request.getParameter("lastname");
		String address1= request.getParameter("address1").trim();
		String address2= request.getParameter("address2").trim();
		String city= request.getParameter("city").trim();
		String state= request.getParameter("state").trim();
		String zipcode= request.getParameter("zip").trim();
		String month= request.getParameter("month").trim();
		String day= request.getParameter("day").trim();
		String year= request.getParameter("year").trim();
		String gender= request.getParameter("gender").trim();
		String email = request.getParameter("email").trim();
		String cellPhone = request.getParameter("cellPhone").trim();
		String jobTitle = request.getParameter("position");
		String depart = request.getParameter("department");
		
		StringBuffer sbr = new StringBuffer();
		sbr.append(month).append(day).append(year);
		
		Employee employee = new Employee();
		
		employee.setFirstname(firstname);
		employee.setLastname(lastname);
		if(middlename != null){
			employee.setMiddlename(middlename);
		}
		employee.setEmail(email);
		employee.setCellphone(cellPhone);
		employee.setGender(gender);
			
		employee.setAddress1(address1);
		if(address2 != null){
			employee.setAddress2(address2);
		}
		employee.setCity(city);
		employee.setState(state);
		employee.setDateOfBirth(sbr.toString());
		return employee;
	}

	
	public User saveEmployeeLogin(User user,Employee employee){
		//EmployeeProcessor.getInstance()
		if(user !=null && employee != null){
			user.setEmailAddress(employee.getEmail());
			
		}
		
		String hashPassword = SecurityUtils.generateStorngPasswordHash(user.getPassword());
		
		user.setPassword(hashPassword);
		user.setEnabled(true);		
		return user;
	}
	
	
	/**
	 * 
	 * @param username
	 * @param role
	 * @return
	 */
	public UserRole buildUserRole(String username, String[] role){
		UserRole userRole = null;
		for(int i=0;i< role.length;i++){
			userRole = new UserRole();
			userRole.setUsername(username);
			userRole.setRoletbl(role[i]);
			
				
		}
		return userRole;		
		
	}
}
