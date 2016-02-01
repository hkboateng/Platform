package com.boateng.abankus.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.boateng.abankus.application.ws.svc.AuthenticationEmployeeRequest;
import com.boateng.abankus.authentication.AuthenticationResponse;
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerAccount;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.EmployeeCustomerAccount;
import com.boateng.abankus.domain.OrderPayment;
import com.boateng.abankus.domain.Salesemployee;
import com.boateng.abankus.domain.Team;
import com.boateng.abankus.domain.User;
import com.boateng.abankus.domain.UserRole;
import com.boateng.abankus.employees.utils.EmployeeUtils;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.services.AuthenticationService;
import com.boateng.abankus.services.EmployeeService;
import com.boateng.abankus.utils.PlatformUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component

public class EmployeeServiceImpl implements EmployeeService{
	private final Logger log = Logger.getLogger(EmployeeServiceImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired(required=true)
	@Qualifier(value="authenticationServiceImpl")
	private AuthenticationService authenticationServiceImpl;
	
	
	
	 public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }
	 public SessionFactory getSessionFactory() {
		 return sessionFactory;
		 }

	@Override
	@Transactional
	public Employee saveEmployee(Employee e,User user, String[] role) throws PlatformException, IOException{
		try{
			if (e == null) {
				return null;
			}
			
			AuthenticationEmployeeRequest request = new AuthenticationEmployeeRequest();
			request.setAddress1(e.getAddress1());
			request.setAddress2(e.getAddress2());
			request.setPhoneNumber(e.getPhoneNumber());
			request.setCity(e.getCity());
			request.setCompanyNumber(e.getCompanyNumber());
			request.setFirstname(e.getFirstname());
			request.setGender(e.getGender());
			request.setLastname(e.getLastname());
			request.setMiddlename(e.getMiddlename());
			request.setState(e.getState());
			request.setZipcode(e.getZipcode());
			request.setRoles(role);
			request.setCompanyId(user.getCompanyId());
			request.setEmailAddress(e.getEmailAddress());
			request.setEnabled(true);
			request.setUsername(user.getUsername());
			request.setHashpassword(user.getPassword());
			
			
			
			String result = saveLoginCredentials(request);
			AuthenticationResponse response = PlatformUtils.convertFromJSON(AuthenticationResponse.class, result);
			log.info(response.getMessage());

			return e;	
		
		}catch(HibernateException ex){
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public String saveLoginCredentials(AuthenticationEmployeeRequest request ){
		Client client = ClientBuilder.newClient();
		
		String response = null;
		String requestString = null;
		try {
			requestString = PlatformUtils.convertToJSON(request);
			response = client.target("http://localhost:8080/authenticationhub/authentication/saveEmployeeInformation")
									.request()
									.put(Entity.entity(requestString, MediaType.APPLICATION_JSON), String.class);
		} catch ( IOException e) {
			log.log(Level.INFO,e.getMessage());
			e.printStackTrace();
		}finally{
			requestString = null;			
		}
		
		return response;
	}
	
	@Override
	public List<Employee> getAllEmployee(long companyId){
		Client client = ClientBuilder.newClient();
		List<Employee> listEmployee = null;
		Response response = null;
		String result = null;		
		try {
			 result = "";
			response = client.target("http://localhost:8080/authenticationhub/authentication/getAllEmployeeByCompanyId")
								.queryParam("companyId", companyId)
									.request()
									.get();
			if(response.getStatus() == 200){
				result = response.readEntity(String.class);
				try {
					listEmployee = PlatformUtils.convertFromJson(new TypeReference<List<Employee>>(){}, result);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} finally{
			response = null;
			 result = null;			
		}		
		
		return listEmployee;
	}
	
	@Transactional
	public Employee getEmployeeById(int employeeId){
		if(employeeId < 1){
			log.warning("Employee Id is Null.");
			return null;
		}
		Session session = getSessionFactory().getCurrentSession();
		Employee employee  = (Employee) session.get(Employee.class, employeeId);
		log.info("Employee Instance has being reteived from Database.");
		return employee;
	}
	
	public Team generateSaleTeam(Employee employee){
		Team team = new Team();
		team.setTeamNumber(Team.generateTeamId());
		team.setEmployee(employee);
		team.setTeamname("Default");
		return team;
	}
	
	@Transactional
	@Override
	public void updateEmployeeByIdAndEmployeeId(Integer Id,Employee employee){
		Session session = getSessionFactory().getCurrentSession();
						session.update("employee", employee);
						
	}
	/* (non-Javadoc)
	 * @see com.boateng.abankus.services.EmployeeService#saveEmployeeSales(com.boateng.abankus.domain.Salesemployee)
	 */
	@Transactional	
	@Override
	public Salesemployee saveEmployeeSales(Salesemployee employeeSale) {
		Session session = getSessionFactory().getCurrentSession();
				session.save("Salesemployee", employeeSale);
				
				return employeeSale;
	}

	@SuppressWarnings("unchecked")
	@Transactional	
	@Override
	public List<Customer> findAllCustomerByEmployeeId(int employeeId) {
		Session session = getSessionFactory().getCurrentSession();
		
		String query = "From CustomerAccount ac where ac.employee.id =:employeeId";
		List<Customer> list =	session.createQuery(query)
				.setParameter("employeeId", employeeId)
				.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional	
	public List<OrderPayment> findAllPaymentByEmployeeAndDate(int employeeId) {
		Session session = getSessionFactory().getCurrentSession();
		List<OrderPayment> payments = session.createQuery("Select p From OrderPayment p where p.employee.id =:employeeId")
						.setParameter("employeeId", employeeId)
						.list();
		return payments;
	}
	/* (non-Javadoc)
	 * @see com.boateng.abankus.services.EmployeeService#findAllEmployeeByEmployeeNumber(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional	
	public List<Employee> findAllEmployeeByEmployeeNumber(String employeeNumber) throws PlatformException{
		Session session = getSessionFactory().getCurrentSession();
		List<Employee> employeeList = session.createQuery("from Employee e where e.employeeId =:employeeId")
				.setParameter("employeeId", employeeNumber)
				.list();
		return employeeList;
	}
	/* (non-Javadoc)
	 * @see com.boateng.abankus.services.EmployeeService#findAllEmployeeByFirstAndLastName(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Employee> findAllEmployeeByFirstAndLastName(String firstname, String lastname) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.boateng.abankus.services.EmployeeService#findAllEmployeeByEmailAddress(java.lang.String)
	 */
	@Override
	public List<Employee> findAllEmployeeByEmailAddress(String emaillAddress) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.boateng.abankus.services.EmployeeService#findAllEmployeeListSearchString(java.lang.String)
	 */
	@Override
	public List<Employee> findAllEmployeeListSearchString(String search) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.boateng.abankus.services.EmployeeService#findAllEmployeeByEmployeeId(java.lang.Integer)
	 */
	@Override
	public List<Employee> findAllEmployeeByEmployeeId(Integer employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public Employee addEmployeeInformation(Employee employee) throws PlatformException {
		Session session = getSessionFactory().getCurrentSession();
		session.save("employee", employee);
		return employee;
	}

}
