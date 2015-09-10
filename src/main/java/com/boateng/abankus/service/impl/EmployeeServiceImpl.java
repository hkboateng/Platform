package com.boateng.abankus.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerAccount;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.EmployeeCustomerAccount;
import com.boateng.abankus.domain.Salesemployee;
import com.boateng.abankus.domain.Team;
import com.boateng.abankus.domain.User;
import com.boateng.abankus.domain.UserRole;
import com.boateng.abankus.employees.utils.EmployeeUtils;
import com.boateng.abankus.services.AuthenticationService;
import com.boateng.abankus.services.EmployeeService;

@Component
public class EmployeeServiceImpl implements EmployeeService{
	private final Logger log = Logger.getLogger(EmployeeServiceImpl.class);

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



	@Transactional
	@Override
	public Employee saveEmployee(Employee e,User user, String[] role){
		try{
		if (e == null) {
			return null;
		}
		
		if (e.getEmployeeId() == null) {
			e.setEmployeeId(EmployeeUtils.generateEmployeeId());
		}
		Session session = getSessionFactory().getCurrentSession();

		session.persist(e);
		user.setEmployeeId(e.getEmployeeId());
		session.save(user);
		
		/** Saving Employee UserRole **/
		UserRole userRole = null;
		for(int i=0;i< role.length;i++){
			userRole = new UserRole(user,role[i]);
			session.save(userRole);

		}
		
		/**
		 * Add Employee to default teams
		 */
		Team team = new Team();
		team.setTeamNumber(Team.generateTeamId());
		team.setEmployee(e);
		team.setTeamname("Default");
		session.save(team);
		session.flush();
		return e;	
		
		}catch(HibernateException ex){
			ex.getMessage();
			return null;
		}
	}


	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	@Cacheable(value="allEmployee")
	public List<Employee> getAllEmployee(){
		Session session = getSessionFactory().getCurrentSession();
		List<Employee> listEmployee = 	session.createQuery("From Employee")
						.setCacheMode(CacheMode.NORMAL)
						.list();
		
		return listEmployee;
	}
	
	@Transactional
	@Cacheable(value="employee" ,key="#employeeId")
	public Employee getEmployeeById(int employeeId){
		if(employeeId < 1){
			log.warn("Employee Id is Null.");
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
	
	@Override
	public EmployeeCustomerAccount saveEmployeeCustomer(EmployeeCustomerAccount eca){
		Session session = getSessionFactory().getCurrentSession();
				session.save("Employeecustomeraccount", eca);
		return eca;
	}
	/* (non-Javadoc)
	 * @see com.boateng.abankus.services.EmployeeService#addEmployeeSalesAccount(com.boateng.abankus.domain.Employee, com.boateng.abankus.domain.Customer, java.lang.String, java.lang.String)
	 */
	@Override
	public CustomerAccount addEmployeeSalesAccount(Employee employee,
			Customer customer, String industry, String notes) {
		// TODO Auto-generated method stub
		return null;
	}
}
