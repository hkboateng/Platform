/**
 * hkboateng
 */
package com.boateng.abankus.processors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.boateng.abankus.controller.OrderController;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.User;
import com.boateng.abankus.domain.UserRole;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.EmployeeFields;
import com.boateng.abankus.services.EmployeeService;
import com.boateng.abankus.utils.SecurityUtils;

/**
 * @author hkboateng
 *
 */
public class EmployeeServiceProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceProcessor.class);
	
	@Autowired
	@Qualifier(value="employeeSvcImpl")
	private EmployeeService employeeSvcImpl;

	/**
	 * @param employeeSvcImpl the employeeSvcImpl to set
	 */
	public void setEmployeeSvcImpl(EmployeeService employeeSvcImpl) {
		this.employeeSvcImpl = employeeSvcImpl;
	}
	public static EmployeeServiceProcessor getInstance(){
		return employeeService;
	}
	
	HttpSession session = null;
	private static final EmployeeServiceProcessor employeeService = new EmployeeServiceProcessor();
	
	private EmployeeServiceProcessor(){}

	
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
		employee.setZipcode(zipcode);
		employee.setDateOfBirth(sbr.toString());
		sbr = null;
		return employee;
	}


	public User saveEmployeeLogin(User user,Employee employee){
		
		if(user !=null && employee != null){
			user.setEmailAddress(employee.getEmail());
			
		}
		
		String hashPassword = SecurityUtils.generateStorngPasswordHash(user.getPassword());
		
		user.setPassword(hashPassword);
		user.setEnabled(true);	
		hashPassword = null;
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
	
	public String process(String action, HttpServletRequest request){
		if(action.equals("update")){
			updateEmployeeInformation(request);
		}
		return action;
		
	}
	
	private void updateEmployeeInformation(HttpServletRequest request){
		Integer Id  = Integer.valueOf(request.getParameter("jumbo"));
		String employeeId = request.getParameter("employeeId");
		Employee employee = buildEmployeeInstance(request,employeeId,Id);
		
		employeeSvcImpl.updateEmployeeByIdAndEmployeeId(Id, employee);
		session = request.getSession(false);
		logger.info("Loading new Employee into Session.");
		session.setAttribute(EmployeeFields.EMPLOYEE_SESSION,employee);	
	}
	
	private Employee buildEmployeeInstance(HttpServletRequest request,String employeeId,Integer Id){
		String firstname = request.getParameter("firstname").trim();
		String lastname = request.getParameter("lastname").trim();
		String middlename = request.getParameter("lastname");
		String address1= request.getParameter("address1").trim();
		String address2= request.getParameter("address2").trim();
		String city= request.getParameter("city").trim();
		String state= request.getParameter("state").trim();
		String zipcode= request.getParameter("zipcode").trim();
		String month= request.getParameter("month").trim();
		String day= request.getParameter("day").trim();
		String year= request.getParameter("year").trim();
		String gender= request.getParameter("gender").trim();
		String email = request.getParameter("email").trim();
		String cellPhone = request.getParameter("cellphone").trim();	
		
		/*******************************
		 * Building Employee Instance obtain from the search result
		 */
		Employee employee = employeeSvcImpl.getEmployeeById(Id);
		logger.info("Employee with Employee Id: "+employeeId+" is update Employee Object.");
		
		StringBuffer sbr = new StringBuffer();
		sbr.append(month).append(day).append(year);
		
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
		employee.setZipcode(zipcode);
		employee.setDateOfBirth(sbr.toString());
		return employee;
		
	}
	/**
	 * @param search
	 */
	public List<Employee> findEmployeeDetails(String search) {
		if(search == null){
			return null;
		}
		
		search = search.replaceAll("[^\\w\\s-]", "");
		List<Employee> employeeList = null;
		try {
			employeeList = employeeSvcImpl.findAllEmployeeByEmployeeNumber(search);
			if(employeeList == null){
				Integer employeeId = Integer.parseInt(search);
				employeeList = employeeSvcImpl.findAllEmployeeByEmployeeId(employeeId);
			}
			if(employeeList == null){
				String[] names = search.split(" ");
				String firstname = names[0];
				String lastname = names[1];
				employeeList = employeeSvcImpl.findAllEmployeeByFirstAndLastName(firstname, lastname);
				if(employeeList == null){
					//employeeList = employeeSvcImpl.
				}
			}
		} catch (PlatformException  | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		// TODO Auto-generated method stub
		
	}
}
