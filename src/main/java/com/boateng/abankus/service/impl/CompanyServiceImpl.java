/**
 * hkboateng
 */
package com.boateng.abankus.service.impl;

import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boateng.abankus.domain.Address;
import com.boateng.abankus.domain.Company;
import com.boateng.abankus.domain.ContactPerson;
import com.boateng.abankus.domain.Email;
import com.boateng.abankus.domain.Phone;
import com.boateng.abankus.services.CompanyService;

/**
 * @author hkboateng
 *
 */
@Component
public class CompanyServiceImpl implements CompanyService {

	private static final Logger logger = Logger.getLogger(CompanyServiceImpl.class.getName());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public Company saveCompany(Company company, Email email, Phone phone, Address address, ContactPerson person) {
		Session session = null;
		if(getSessionFactory().isClosed()){
			session = getSessionFactory().openSession();
		}else{
			session = getSessionFactory().getCurrentSession();
		}
		session.save(email);
		session.save(phone);
		session.save(address);
		session.save(person);
		
		company.setAddressBean(address);
		company.setContactperson(person);
		company.setPhoneBean(phone);
		company.setEmailBean(email);
		
		session.save(company);
		session.flush();
		return company;
	}

	@Override
	public Company findCompanyByCompanyId(Integer companyId) {
		Session session = null;
		if(getSessionFactory().isClosed()){
			session = getSessionFactory().openSession();
		}else{
			session = getSessionFactory().getCurrentSession();
		}
		Company company = null;
		company = (Company) session.get(Company.class, companyId);
		return company;
	}

	@Override
	public Company findCompanyByEmailAddress(String emailAddress) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Company updateCompany(Integer comanyId, Company company) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean deleteCompany(Integer companyId) {
		// TODO Auto-generated method stub
		return false;
	}

}
