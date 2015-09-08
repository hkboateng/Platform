package com.boateng.abankus.customer.service;

import java.util.List;

import com.boateng.abankus.domain.Address;
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerAccount;
import com.boateng.abankus.domain.Email;
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
}
