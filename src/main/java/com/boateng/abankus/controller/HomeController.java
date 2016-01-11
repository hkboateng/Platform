package com.boateng.abankus.controller;

import java.util.Locale;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	
	private static final Logger logger = Logger.getLogger(HomeController.class.getName());
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model,HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.");

	
		return "index";
	}

	@RequestMapping(value ="/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.");

		
		return "index";
	}

	@RequestMapping(value ="/error", method = RequestMethod.GET)
	public String error(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.");

		
		return "index";
	}

}
