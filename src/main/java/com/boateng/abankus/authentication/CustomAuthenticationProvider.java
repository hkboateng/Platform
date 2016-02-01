/**
 * hkboateng
 */
package com.boateng.abankus.authentication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.boateng.abankus.domain.Permission;
import com.boateng.abankus.exception.CustomAuthenticationException;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.utils.PlatformUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author hkboateng
 *
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final Logger logger = Logger.getLogger(CustomAuthenticationProvider.class.getName());
	
	@Autowired
    private CustomUserDetailService userService;

	@Override
	public Authentication authenticate(Authentication authenticate) throws AuthenticationException {
		String username = authenticate.getName();
		String password = authenticate.getCredentials().toString();

		AuthenticationResponse authenticationResponse = null;
		String status = null;
		Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target("http://localhost:8080/authenticationhub/authentication");
		Response response = target.path("/authenticateUser").queryParam("username", username)
										.queryParam("password", password)
										.request(MediaType.APPLICATION_JSON)
										.get();
		ObjectMapper mapper = new ObjectMapper();
		if(response.getStatus() == 404){
			CustomAuthenticationException ace = new CustomAuthenticationException("Authentication Web Service is currently down or offline.");
			
			throw ace;
		}
		try {
			status = response.readEntity(String.class);
			authenticationResponse = mapper.readValue(status, AuthenticationResponse.class);
		} catch (JsonParseException e) {
			PlatformException ace = new PlatformException(e);
			
			
		} catch (JsonMappingException e) {
			PlatformException ace = new PlatformException(e);
		} catch (IOException e) {
			PlatformException ace = new PlatformException(e);
		}
		logger.info("Retreiving User Detail information for authentication and authorization...");
		if(!authenticationResponse.isResult() &&  authenticationResponse.getMessage().equalsIgnoreCase("Username is invalid")){
			logger.warning("User:"+username+" authentication failed. Message = "+authenticationResponse.getMessage());
			 throw new BadCredentialsException("Username and/or Password is invalid.");
		}
		
		if(!authenticationResponse.isResult() && authenticationResponse.getMessage().equalsIgnoreCase("invalid username and password")){
			logger.warning("User:"+username+" authentication falied. Message = "+authenticationResponse.getMessage());
			 throw new BadCredentialsException("Username and/or Password is invalid.");
		}
		
		List<String> permissionList = getUserPermissions(authenticationResponse.getUserId());
		Collection<? extends GrantedAuthority> authorities = getAllUserPermission(permissionList);
		for(GrantedAuthority permission: authorities){
			logger.info(permission.getAuthority());
		}
		logger.info("User "+username+" authentication is completed and valid.");
		return new UsernamePasswordAuthenticationToken(username,password,authorities);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	private List<Permission> getAllUserPermission(List<String> permissionList){
		List<Permission> permissions = null;
		if(permissionList != null){
			permissions = new ArrayList<Permission>();
			for(String permission: permissionList){
				Permission p = new Permission();
				p.setPermission("ROLE_"+permission);
				permissions.add(p);
			}
		}
		return permissions;
	}
	public List<String> getUserPermissions(int userId){
		logger.info("Retreiving user's permissions.....");
		Client client = ClientBuilder.newClient();
		String results = null;
		List<String> permissionList = null;
		WebTarget target = client.target("http://localhost:8080/authenticationhub/authentication");
		Response response = target.path("/getUserPermissions").queryParam("userId", userId)
										.request(MediaType.APPLICATION_JSON)
										.get();
		results = response.readEntity(String.class);
		if(results != null){
			try {
				permissionList = PlatformUtils.convertFromJson(new TypeReference<List<String>>() {}, results);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.info("Retreiving user's permission completed.....");
		return permissionList;
	}


}
