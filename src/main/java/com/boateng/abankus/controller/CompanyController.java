/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import java.io.IOException;
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

/**
 * Controller for Company (Clients that will be using this application.)
 * @author hkboateng
 *
 */

@Controller
public class CompanyController {

	private static final Logger logger = Logger.getLogger( CompanyController.class.getName());
	
	@Autowired
	private CompanyProcessor companyProcessor;
	
	@RequestMapping(value ="/Company/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		logger.info("Viewing Sign up page. ");

		return "SignUp";
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
				AuthenticationResponse authenticationResponse = companyProcessor.submitCompanyCredential(request);
				if(authenticationResponse.isResult()){
					company = companyProcessor.buildAndSubmitCompany(request);
				}else{
					redirectAttributess.addFlashAttribute(PlatformFields.ERROR_MESSAGE,authenticationResponse.getMessage());
					return "redirect:/Company/signup";					
				}
			} catch (PlatformException | IOException e) {
				logger.log(Level.SEVERE,e.getMessage());
				e.printStackTrace();
				redirectAttributess.addFlashAttribute(PlatformFields.ERROR_MESSAGE, "Error occured while processing the request.");
				return "redirect:/Company/signup";				
			}
		
		if(company != null){
			redirectAttributess.addFlashAttribute(PlatformFields.SUCCESS_MESSAGE,"Your Company has being recieved successfully. You will received an email shortly at this address: "+ company.getContactperson().getEmail());
		}
			else{
			redirectAttributess.addFlashAttribute(PlatformFields.SUCCESS_MESSAGE,"Your Company has being recieved successfully. You will received an email shortly");			
		}
		company = null;
		return "redirect:/Company/signup";
	}
}
