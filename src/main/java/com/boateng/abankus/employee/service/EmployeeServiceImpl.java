package com.boateng.abankus.employee.service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.User;
import com.boateng.abankus.domain.UserRole;
import com.boateng.abankus.employee.interfaces.AuthenticationService;
import com.boateng.abankus.employee.interfaces.EmployeeService;
import com.boateng.abankus.employees.utils.EmployeeProcessor;
import com.boateng.abankus.employees.utils.EmployeeUtils;
import com.boateng.abankus.utils.SecurityUtils;

@Component
public class EmployeeServiceImpl implements EmployeeService{
	private final Logger log = Logger.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired(required=true)
	@Qualifier(value="authenticationServiceImpl")
	private AuthenticationService authenticationServiceImpl;
	
	 private static final EmployeeServiceImpl emp = new EmployeeServiceImpl();
	
	 public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }
	 public SessionFactory getSessionFactory() {
		 return sessionFactory;
		 }
	 /*
	 public static EmployeeServiceImpl getInstance(){
		 return emp;
	 }
	
	 * 
	 */

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
		//session.beginTransaction();
		session.persist(e);
		session.persist("User", user);
		
		/** Saving Employee UserRole **/

		for(int i=0;i< role.length;i++){
			UserRole userRole = new UserRole(user,role[i]);
			session.save(userRole);
			session.flush();	
			session.clear();
		}
		//UserRole userRole = EmployeeProcessor.getInstance().buildUserRole(user.getUsername(), role);
		
		session.flush();
		
		return e;	
		}catch(HibernateException ex){
			ex.getMessage();
			return null;
		}
	}

}
