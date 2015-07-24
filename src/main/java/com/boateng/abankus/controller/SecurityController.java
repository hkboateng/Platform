package com.boateng.abankus.controller;

import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.User;
import com.boateng.abankus.employee.interfaces.AuthenticationService;
import com.boateng.abankus.users.UserCollection;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SecurityController {
	@Autowired(required=true)
	@Qualifier(value="authenticationServiceImpl")
	private AuthenticationService authenticationServiceImpl;	
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/security/login", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) {
		logger.info("Welcome home! The client locale is gsfdg");

		
		return "index";
	}
	
	@RequestMapping(value = "/platform/index", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model) {
		logger.info("Welcome home! The client locale is gsfdg");

		
		return "dashboard/dashboard";
	}

	
	
	@RequestMapping(value = "/security/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);
		if(!session.isNew() || session.getId() != null){
			session.invalidate();
		}
		return "home";
	}
	@RequestMapping(value = "/security/authenticate",method = RequestMethod.POST)
	public String login(RedirectAttributes redirectAttributess,HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		logger.info("Loggin into Platform app ");

		User user =authenticationServiceImpl.AuthenticateUser(username, password);
		if(user == null){
			redirectAttributess.addFlashAttribute("errors", "Username or Password is Invalid!!. Try again");
			return "redirect:/login";
		}

		logger.info("User Login validation passed. "+user.getEmailAddress());
		
		return "redirect:/abankus/dashboard";
	}
	
	@RequestMapping(value = "/platform/dashboard", method = RequestMethod.GET)
	public String dashbaord(Locale locale, Model model,HttpServletRequest request) {
		
		logger.info("Welcome home! {}.",request.getUserPrincipal().getName());


		
		return "dashboard/dashboard";
	}
	
	@RequestMapping(value = "/platform/logout", method =  {RequestMethod.GET,RequestMethod.POST})
	public String logout(RedirectAttributes redirectAttributess,HttpServletRequest request,Model model) {
		HttpSession session = request.getSession(false);
		logger.info("Logging out");
		if(session != null){
			session.invalidate();
		}
		String Id = UUID.randomUUID().toString();
		redirectAttributess.addFlashAttribute("info", "You have logout successfully.");
		return "redirect:/login?info="+Id;
	}		
}
