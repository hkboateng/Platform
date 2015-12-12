/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for Company (Clients that will be using this application.)
 * @author hkboateng
 *
 */

@Controller
public class CompanyController {

	private static final Logger logger = Logger.getLogger( CompanyController.class.getName());
	
	@RequestMapping(value="/company/AddNewCompany", method=RequestMethod.GET)
	public String addNewCompany(){
		
		return "Company/AddNewCompany";
	}
	
	@RequestMapping(value="/company/SaveCompany", method=RequestMethod.POST)
	public void saveNewCompany(){
		
		
	}
}
