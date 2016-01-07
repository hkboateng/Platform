/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.boateng.abankus.domain.Company;
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
	public void saveNewCompany(HttpServletRequest request, Model model){
		logger.info("Platform is processing new Company Signup..");
		List<String> validation = companyProcessor.validateCompany(request);
		if(!validation.isEmpty()){
			model.addAttribute("validationError", validation);
		}
		
		Company company = companyProcessor.buildAndSubmitCompany(request);
	}
}
