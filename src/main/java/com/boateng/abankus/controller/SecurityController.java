package com.boateng.abankus.controller;

import java.util.Locale;
import java.util.UUID;
import java.util.logging.Level;

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
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.services.AuthenticationService;
import com.boateng.abankus.servlet.PlatformAbstractServlet;
import com.boateng.abankus.users.UserCollection;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SecurityController extends PlatformAbstractServlet {
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
		HttpSession session = request.getSession(false);
		if(session != null){
			session.invalidate();
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/platform/index", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model) {
		logger.info("Welcome home! The client locale is gsfdg");

		
		return "dashboard/dashboard";
	}

	
	
	@RequestMapping(value = "/employee/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);
		if(!session.isNew() ){
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
				
		try {
			loadUserIntoSession(request);
			loadEmployeeIntoSessionByUsername(request);
			loadProductIntoMap();
		} catch (Exception e) {
			PlatformException ace  = new PlatformException();
			ace.logger(Level.WARNING,e.getMessage(), e);

		}
		
		logger.info("User data has being saved into the current session.");
		return "dashboard/dashboard";
	}
	
	@RequestMapping(value = "/platform/logout", method =  {RequestMethod.GET,RequestMethod.POST})
	public String logout(RedirectAttributes redirectAttributess,HttpServletRequest request,Model model) {
		HttpSession session = request.getSession(false);
		logger.info("Logging out");
		if(session != null ){
			session.removeAttribute("user");
			session.invalidate();
		}
		redirectAttributess.addFlashAttribute("info", "You have logout successfully.");
		return "redirect:/login";
	}		
}
