/**
 * hkboateng
 */
package com.boateng.abankus.processors;

import java.io.IOException;
import java.math.BigInteger;
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
import com.boateng.abankus.application.ws.svc.AuthenticationCompanyRequest;
import com.boateng.abankus.application.ws.svc.AuthenticationEmployeeRequest;
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
import com.boateng.abankus.services.EmployeeService;
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
	
	@Autowired(required=true)
	private EmployeeService employeeServiceImpl;
	/**
	 * @param request
	 */
	public Company buildAndSubmitCompany(HttpServletRequest request){
		logger.info("Abankus Payment has started building Company and saving it..");
		CompanyBuilder builder = new CompanyBuilder(request);
		
		Company company = builder.buildCompany();
		
		/**
		Email email = builder.addEmailDetails();
		
		Phone phone = builder.addPhoneDetails();
		
		Address address = builder.addAddressDetails();
		
		ContactPerson person = builder.addContactPersonDetails();
		
		**/
		
		//employeeServiceProcessor.
		company = submitCompany(company);
		logger.info("Abankus Payment has completed building Company and saving it..");
		return company;
	}
	

	private Company submitCompany(Company company){
		
		
		company = companyServiceImpl.saveCompany(company);
		return company;
	}
	
	public AuthenticationResponse submitCompanyCredential(int companyId) throws PlatformException, IOException{
		AuthenticationResponse authenticationResponse = null;
		AuthenticationCompanyRequest companyRequest = new AuthenticationCompanyRequest();
		companyRequest.setCompanyId(companyId);
		//AuthenticationRequest  authenticationRequest = new AuthenticationRequest(request);
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/authenticationhub/authentication");
		WebTarget targetPath = target.path("/saveCustomerCredentials");
		String response = targetPath.request(MediaType.APPLICATION_JSON_TYPE)
								.put(Entity.entity(PlatformUtils.convertToJSON(companyRequest), MediaType.APPLICATION_JSON_TYPE),String.class);

		authenticationResponse = (AuthenticationResponse) PlatformUtils.convertFromJSON(AuthenticationResponse.class,response);	
		return authenticationResponse;
	}
}
