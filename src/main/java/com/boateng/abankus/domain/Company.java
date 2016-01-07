package com.boateng.abankus.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;


/**
 * The persistent class for the company database table.
 * 
 */
@Entity
@DynamicUpdate(value=true)
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int companyId;

	private String businessname;

	private String businessType;

	private String companyName;

	private String taxId;

	private int companyNumber;
	
	//bi-directional one-to-one association to Address
	@OneToOne
	@JoinColumn(name="addressId")
	private Address addressBean;

	//bi-directional one-to-one association to Contactperson
	@OneToOne
	@JoinColumn(name="contactPersonId")
	private ContactPerson contactperson;

	//bi-directional one-to-one association to Email
	@OneToOne
	@JoinColumn(name="emailId")
	private Email emailBean;

	//bi-directional one-to-one association to Phone
	@OneToOne
	@JoinColumn(name="phoneId")
	private Phone phoneBean;

	public Company() {
	}

	public int getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getBusinessname() {
		return this.businessname;
	}

	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}

	public String getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getTaxId() {
		return this.taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public Address getAddressBean() {
		return this.addressBean;
	}

	public void setAddressBean(Address addressBean) {
		this.addressBean = addressBean;
	}

	public ContactPerson  getContactperson() {
		return this.contactperson;
	}

	public void setContactperson(ContactPerson  contactperson) {
		this.contactperson = contactperson;
	}

	public Email getEmailBean() {
		return this.emailBean;
	}

	public void setEmailBean(Email emailBean) {
		this.emailBean = emailBean;
	}

	public Phone getPhoneBean() {
		return this.phoneBean;
	}

	public void setPhoneBean(Phone phoneBean) {
		this.phoneBean = phoneBean;
	}

	public int getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(int companyNumber) {
		this.companyNumber = companyNumber;
	}

}