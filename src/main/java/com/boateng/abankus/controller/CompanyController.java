/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boateng.abankus.application.ws.svc.AuthenticationResponse;
import com.boateng.abankus.domain.Company;
import com.boateng.abankus.entity.validation.CompanyValidation;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.PlatformFields;
import com.boateng.abankus.processors.CompanyProcessor;
import com.boateng.abankus.servlet.PlatformAbstractServlet;

/**
 * Controller for Company (Clients that will be using this application.)
 * @author hkboateng
 *
 */

@Controller
public class CompanyController extends PlatformAbstractServlet{

	private static final Logger logger = Logger.getLogger( CompanyController.class.getName());
	
	@Autowired
	private CompanyProcessor companyProcessor;
	
	@RequestMapping(value ="/Company/signup", method = RequestMethod.GET)
	public String signup(HttpServletRequest request,Model model) {
		try {
			logger.info(logActivity("Viewing Sign up page. ",getEmployeeInSession(request)));
		} catch (PlatformException e) {
			logger.warning(e.getMessage());
			logger.warning("Error occured while logging was getting Employee from session.");
		}

		return "SignUp";
	}

	@RequestMapping(value ="/Company/viewCompanyProfile", method = RequestMethod.GET)
	public String companyProfile(HttpServletRequest request,Model model) {
		try {
			logger.info(logActivity("is viewing Company Profile page.",getEmployeeInSession(request)));
		} catch (PlatformException e) {
			logger.warning(e.getMessage());
			logger.warning("Error occured while logging was getting Employee from session.");
		}
		return "Company/CompanyProfile";
	}
	
	@RequestMapping(value="/Company/saveCompany", method=RequestMethod.POST)
	public String saveNewCompany(HttpServletRequest request, Model model,RedirectAttributes redirectAttributess){
		logger.info("Platform is processing new Company Signup..");
		CompanyValidation validate = new CompanyValidation(request);
		
		List<String> validation = validate.validateCompany();
		
		if(!validation.isEmpty()){
			redirectAttributess.addFlashAttribute(PlatformFields.VALIDATION_ERROR, validation);
			return "redirect:/Company/signup";
		}
		
		Company company = null;

			try {
				
				company = companyProcessor.buildAndSubmitCompany(request);
				if(company != null){
					AuthenticationResponse authenticationResponse = companyProcessor.submitCompanyCredential(company.getCompanyId());
					if(authenticationResponse.isResult()){
						redirectAttributess.addFlashAttribute(PlatformFields.SUCCESS_MESSAGE,"Your Company has being recieved successfully. You will received an email shortly");			
					}else{
						redirectAttributess.addFlashAttribute(PlatformFields.ERROR_MESSAGE,authenticationResponse.getMessage());
						return "redirect:/Company/signup";					
					}				
				}else{
					redirectAttributess.addFlashAttribute(PlatformFields.SUCCESS_MESSAGE,"Your Company has being recieved successfully. You will received an email shortly");			
				}				
				
			} catch (PlatformException | IOException e) {
				logger.log(Level.SEVERE,e.getMessage());
				e.printStackTrace();
				redirectAttributess.addFlashAttribute(PlatformFields.ERROR_MESSAGE, "Error occured while processing the request.");
				return "redirect:/Company/signup";				
			}
		

		company = null;
		return "redirect:/Company/signup";
	}
}
