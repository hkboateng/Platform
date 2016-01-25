/**
 * hkboateng
 */
package com.boateng.abankus.authentication;

import org.springframework.security.access.vote.RoleVoter;
import org.springframework.stereotype.Component;

/**
 * @author hkboateng
 *
 */
@Component
public class CustomRoleVoter extends RoleVoter {

	private String rolePrefix = "";

	/**
	 * @return the rolePrefix
	 */
	public String getRolePrefix() {
		return rolePrefix;
	}

	/**
	 * @param rolePrefix the rolePrefix to set
	 */
	public void setRolePrefix(String rolePrefix) {
		this.rolePrefix = rolePrefix;
	}
	
}
