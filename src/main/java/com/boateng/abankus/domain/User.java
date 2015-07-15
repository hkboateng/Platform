package com.boateng.abankus.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;


/**
 * The persistent class for the login database table.
 * 
 */
@Entity
@Table(name="usrtbl")
@NamedQuery(name="User.findAll", query="SELECT l FROM User l")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="userId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int loginId;

	private String password;

	private String username;
	
	private String employeeId;
	
	private boolean enabled;
	
	@Email
	@NotNull
	private String emailAddress;

	public User() {
	}
	public User(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.setEnabled(enabled);
	}
 
	public User(String username, String password, String emailAddress,
		boolean enabled) {
		if(username !=null && !username.isEmpty()){
		this.username = username;
		}
		this.password = password;
		this.setEnabled(enabled);
		this.emailAddress = emailAddress;
	}

	/**
	 * @param password
	 * @param username
	 */
	public User(String password, String username) {
		super();
		this.password = password;
		this.username = username;
	}

	public int getLoginId() {
		return this.loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}




}