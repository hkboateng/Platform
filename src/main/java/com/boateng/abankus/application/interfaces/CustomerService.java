package com.boateng.abankus.application.interfaces;

import java.util.List;

import org.hibernate.Session;

import com.boateng.abankus.domain.Address;
import com.boateng.abankus.domain.Authenticatecustomer;
import com.boateng.abankus.domain.ContactPerson;
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerAccount;
import com.boateng.abankus.domain.Email;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.Phone;

public interface CustomerService {

	Customer addNewCustomer(Customer customers, Email email, Phone phone, Address address);

	/**
	 * Returns an instance of a Customer based on a Customer Number.
	 * 
	 * @param customerNo - Unique Customer Number
	 * @return
	 */
	Customer findCustomerByCustomerNumber(String customerNo);

	Customer findCustomerByCustomerIdAndPinCode(int customerId,String pinCode);
	
	/**
	 * Returns an Instance of a CustomerAccount by searching using customer's Account Number.
	 * @param customerNumber
	 * @return
	 */
	CustomerAccount findCustomerAccountByCustomerId(int customerId);
	
	/**
	 * Returns a list of all Customers
	 * @return 
	 * 
	 */
	
	List<Customer> getAllCustomers();

	/**
	 * Returns an instance of a Customer by searching for the Customer Id.
	 * @param id
	 */
	Customer findCustomerByCustomerId(int id);

	/**
	 * 
	 * @param customerNo
	 * @return
	 */
	CustomerAccount findCustomerAccountByAccountNumber(String customerNo);

	/**
	 * @param Id
	 * @return
	 */
	List<Address> findCustomerAddressByCustomerId(int Id);
	

	Email findCustomerByEmailAddress(String email);

	Customer findCustomerByEmail(String email);
	/**
	 * @param customerId
	 * @return
	 */
	List<Email> findCustomerEmailByCustomerId(int customerId);

	/**
	 * @param customerId
	 * @return
	 */
	List<Phone> findCustomerPhoneByCustomerId(int customerId);

	/**
	 * @param employee
	 * @param customer
	 * @param industry
	 * @param notes
	 * @return
	 */
	CustomerAccount addEmployeeSalesAccount(Employee employee,
			Customer customer, String industry, String notes);

	/**
	 * @param pin
	 * @param customer
	 * @throws Exception
	 */
	void saveCustomerPin(String pin, Customer customer,Session session) throws Exception;
	
	Authenticatecustomer findCustomerById(int id);
	
	Customer findCustomerByFirstNameAndLastName(String firstname,String lastname);

	/**
	 * @param lastname
	 * @return
	 */
	List<Customer> findCustomerByLastName(String lastname);
	
	/**
	 * 
	 * @param firstname
	 * @return
	 */
	List<Customer> findCustomerByFirstName(String firstname);
	
	List<Customer> findCustomerLikeFirstNameAndLastName(String firstname, String lastname);
	
	void updateCustomerContactPerson(ContactPerson person,int customerId);

	/**
	 * @param customerId
	 * @return
	 */
	ContactPerson findCustomerContactByCustomerId(int customerId);
	
	Customer findCustomerByAccountNumber(String accountNumber);
	
	Integer countTotalCustomer();
}
