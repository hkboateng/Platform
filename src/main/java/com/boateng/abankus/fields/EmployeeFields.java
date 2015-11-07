package com.boateng.abankus.fields;

public class EmployeeFields {

	private EmployeeFields(){}
	
	public static final String EMPLOYEE_PROPERTY_NAME = "/Employee/EmployeeServices.properties";
	
	public static final String EMPLOYEE_PERSONAL_INFO_PAGE = "employeePersonal";
	
	public static final String EMPLOYEE_DEPARTMENT_PAGE = "employeeDepartmentInfo";	
	
	public static final String EMPLOYEE_SECURITY_PAGE = "employeeLoginCredential";
	public static final String ZIPCODE_REGEX = "^[0-9]{5}(?:-[0-9]{4})?$";
	
	public static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public static final String ALPHA_PATTERN = "^[a-zA-Z0-9]";
	
	public static final String EMPLOYEE_SESSION = "employee";
	
	public static final String EMPLOYEE_CUSTOMER_LIST = "employeeCustomerList";
}
