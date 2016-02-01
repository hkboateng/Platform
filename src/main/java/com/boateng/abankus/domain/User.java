package com.boateng.abankus.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * The persistent class for the login database table.
 * 
 */
@Entity
@Table(name="usrtbl")
@NamedQuery(name="User.findAll", query="SELECT l FROM User l")
public class User implements Serializable, UserDetails{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="userId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int loginId;

	private String password;

	private String username;
	
	@Transient
    private List<Permission> authorities;
        
    private String emailAddress;
    
    private String employeeId;
    
    private long companyId;
    
    private boolean accountNonExpired = true;
    
    private boolean accountNonLocked = true;
    
    private boolean credentialsNonExpired = true;
    
    private boolean enabled = true;

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
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
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
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}






}