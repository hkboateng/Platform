package com.boateng.abankus.domain;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;


/**
 * The persistent class for the roletbl database table.
 * 
 */
@Entity
@Table(name="roletbl")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable, GrantedAuthority {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int roleId;

	private String notes;

	private String role;



	public Role() {
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @param role
	 */
	public Role(String role) {
		super();
		this.role = role;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.getRole();
	}



}