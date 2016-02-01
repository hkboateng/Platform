/**
 * hkboateng
 */
package com.boateng.abankus.services;

import com.boateng.abankus.domain.Address;
import com.boateng.abankus.domain.Company;
import com.boateng.abankus.domain.ContactPerson;
import com.boateng.abankus.domain.Email;
import com.boateng.abankus.domain.Phone;

/**
 * @author hkboateng
 *
 */
public interface CompanyService {
	Company saveCompany(Company company);
	
	Company findCompanyByCompanyId(Integer comapnyId);
	
	Company findCompanyByEmailAddress(String emailAddress);
	
	Company updateCompany(Integer comanyId, Company company);
	
	boolean deleteCompany(Integer companyId);
}
