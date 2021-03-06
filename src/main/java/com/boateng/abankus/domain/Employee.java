package com.boateng.abankus.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonValue;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicUpdate(value=true)
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String address1;

	private String address2;
	

	private String phoneNumber;
	

	private String city;


	private String emailAddress;

	private String firstname;


	private String gender;


	private String lastname;

	private String middlename;


	private String state;


	private String zipcode;

	private String  employeeId;
	
	private long companyNumber;

	public Employee() {
	}

	
	/**
	 * @return the employeeId
	 */

	public String getEmployeeId() {
		return employeeId;
	}


	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	@JsonValue
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMiddlename() {
		return this.middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getFirstname());
		if(getMiddlename() !=null){
			builder.append(" ");
			builder.append(getMiddlename());
		}
		builder.append(" ");
		builder.append(getLastname());		
		return builder.toString();
	}


	public  long getCompanyNumber() {
		return companyNumber;
	}


	public void setCompanyId( long companyNumber) {
		this.companyNumber = companyNumber;
	}



}