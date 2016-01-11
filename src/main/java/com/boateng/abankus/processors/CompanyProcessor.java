/**
 * hkboateng
 */
package com.boateng.abankus.processors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;

import com.boateng.abankus.application.builders.CompanyBuilder;
import com.boateng.abankus.application.ws.svc.AuthenticationRequest;
import com.boateng.abankus.application.ws.svc.AuthenticationResponse;
import com.boateng.abankus.domain.Address;
import com.boateng.abankus.domain.Company;
import com.boateng.abankus.domain.ContactPerson;
import com.boateng.abankus.domain.Email;
import com.boateng.abankus.domain.Phone;
import com.boateng.abankus.entity.validation.CompanyValidation;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.services.CompanyService;
import com.boateng.abankus.utils.PlatformUtils;
import com.boateng.abankus.utils.SecurityUtils;

/**
 * @author hkboateng
 *
 */
public class CompanyProcessor {

	private static final Logger logger = Logger.getLogger(CompanyProcessor.class.getName());
	
	@Autowired
	private CompanyService companyServiceImpl;
	
	/**
	 * @param request
	 */
	public Company buildAndSubmitCompany(HttpServletRequest request){
		logger.info("Abankus Payment has started building Company and saving it..");
		CompanyBuilder builder = new CompanyBuilder(request);
		
		Company company = builder.buildCompany();
		
		Email email = builder.addEmailDetails();
		
		Phone phone = builder.addPhoneDetails();
		
		Address address = builder.addAddressDetails();
		
		ContactPerson person = builder.addContactPersonDetails();
		
		company = submitCompany(company,email,phone,address,person);
		logger.info("Abankus Payment has completed building Company and saving it..");
		return company;
	}
	
	private Company submitCompany(Company company, Email email, Phone phone, Address address, ContactPerson person){
		/**
		 * company.setEmailId(email);
		company.setPhoneId(phone);
		company.setAddressId(address);
		company.setContactId(contactPerson);
		 */
		Integer companyId = SecurityUtils.generateCompanyNumber();
		company.setCompanyNumber(companyId);
		company = companyServiceImpl.saveCompany(company,email,phone,address,person);
		
		return company;
	}
	
	public boolean submitCompanyCredential(HttpServletRequest request) throws PlatformException, IOException{
		AuthenticationResponse authenticationResponse = null;
		AuthenticationRequest  authenticationRequest = new AuthenticationRequest(request);
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/AuthenticationHub/rs/authentication");
		WebTarget targetPath = target.path("/saveCustomerCredentials");
		Future<String> response = targetPath.request(MediaType.APPLICATION_JSON_TYPE)
								.async()
								.put(Entity.entity(PlatformUtils.convertToJSON(authenticationRequest), MediaType.APPLICATION_JSON_TYPE),String.class);
		boolean status  = false;
		if(response.isDone()){
			try {
				String respond = response.get();
				 
				authenticationResponse = (AuthenticationResponse) PlatformUtils.convertFromJSON(AuthenticationResponse.class,respond);
				status = authenticationResponse.isResult();
			} catch (InterruptedException | ExecutionException e) {
				PlatformException ace = new PlatformException(e);
				throw ace;
			}
		}
		
		return status;
	}
}
