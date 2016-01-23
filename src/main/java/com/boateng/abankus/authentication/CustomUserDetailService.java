/**
 * hkboateng
 */
package com.boateng.abankus.authentication;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.boateng.abankus.service.impl.AuthenticationServiceImpl;
import com.boateng.abankus.services.AuthenticationService;

/**
 * @author hkboateng
 *
 */

@Component
public class CustomUserDetailService implements UserDetailsService{

	private static final Logger logger = Logger.getLogger(CustomUserDetailService.class.getName());
	@Autowired
	private AuthenticationService authenticationService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Authenticating username: "+username);
		return authenticationService.findUserByUserName(username);
	}

}
