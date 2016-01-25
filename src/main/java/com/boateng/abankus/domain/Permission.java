package com.boateng.abankus.domain;

import org.springframework.security.core.GrantedAuthority;

public class Permission implements GrantedAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String permission;

	private int permissionId;
	
	public Permission(){
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.permission;
	}

	/**
	 * @return the permission
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * @param permission the permission to set
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}


	
	
	
}
