package com.boateng.abankus.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boateng.abankus.domain.User;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.PlatformFields;
import com.boateng.abankus.services.AuthenticationService;
import com.boateng.abankus.servlet.PlatformAbstractServlet;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SecurityController extends PlatformAbstractServlet {
	@Autowired(required=true)
	@Qualifier(value="authenticationServiceImpl")
	private AuthenticationService authenticationServiceImpl;	
	
	private static final Logger logger = Logger.getLogger(SecurityController.class.getName());
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/security/login", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) {

		return "index";
	}

	
	@RequestMapping(value = "/platform/logout", method =  {RequestMethod.GET,RequestMethod.POST})
	public String logout(RedirectAttributes redirectAttributess,HttpServletRequest request,Model model) {
		HttpSession session = request.getSession(false);
		
		logger.info("Logging out... User: "+request.getUserPrincipal().getName());
		if(session != null ){
			session.removeAttribute("user");
			session.invalidate();
			logger.info("Loggout successful..");
		}
		redirectAttributess.addFlashAttribute("info", "You have logout successfully.");
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/security/accessdenied", method = {RequestMethod.GET,RequestMethod.POST})
	public String errorMessage(HttpServletRequest request, Model model,RedirectAttributes redirectAttributess) throws PlatformException {
		logger.info(logActivity(" does not have access to perform this operation.",getEmployeeInSession(request)));
		model.addAttribute(PlatformFields.ERROR_MESSAGE, "You donot have access to perform this operation.");
		logger.info(logActivity(" is viewing the Access Denied page.",getEmployeeInSession(request)));
		return "AbankusErrorMessage";
	}
}
