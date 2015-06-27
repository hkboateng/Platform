package com.boateng.abankus.domain;

import java.util.Date;

public class Person {

	private String firstname;
	
	private String lastname;
	
	private String middlename;
	
	private String namePrefix;
	
	private String nameSuffix;

	private Gender gender;
	
	private Date dateOfBirth;

	
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}




	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}





	/**
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param cellPhone
	 * @param gender
	 * @param dateOfBirth
	 */
	public Person() {

	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [firstname=");
		builder.append(firstname);
		builder.append(", lastname=");
		builder.append(lastname);
		builder.append("]");
		return builder.toString();
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getMiddlename() {
		return middlename;
	}



	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}


	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}




	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}




	public String getNamePrefix() {
		return namePrefix;
	}



	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}



	public String getNameSuffix() {
		return nameSuffix;
	}



	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}
	

	
	
}
