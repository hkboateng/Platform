package com.boateng.abankus.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * The persistent class for the userroletbl database table.
 * 
 */
@Entity
@Table(name="userroletbl")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userroleId;

	@NotNull
	private String username;

	@NotNull
	private String role;

	public UserRole() {
	}

	public int getUserroleId() {
		return this.userroleId;
	}

	public void setUserroleId(int userroleId) {
		this.userroleId = userroleId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String  getRoletbl() {
		return this.role;
	}

	public void setRoletbl(String  roletbl) {
		this.role = roletbl;
	}
	
	public UserRole(User user, Role role){
		this.username = user.getUsername();
		this.role = role.getRole();
	}
	public UserRole(User user,String role){
		this.username = user.getUsername();
		this.role = role;
	}
}