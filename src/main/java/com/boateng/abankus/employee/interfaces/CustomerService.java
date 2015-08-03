package com.boateng.abankus.employee.interfaces;

import java.util.List;

import com.boateng.abankus.domain.Address;
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerAccount;
import com.boateng.abankus.domain.Email;
import com.boateng.abankus.domain.Phone;

public interface CustomerService {

	Customer addNewCustomer(Customer customers, Email email, Phone phone, Address address);

	/**
	 * @param customerNo
	 * @return
	 */
	Customer findCustomerByCustomerNumber(String customerNo);

	/**
	 * @return 
	 * 
	 */
	List<Customer> getAllCustomers();

	/**
	 * @param id
	 */
	Customer findCustomerByCustomerId(int id);

	/**
	 * @param customerNo
	 * @return
	 */
	CustomerAccount findCustomerAccountByCustomerNumber(String customerNo);

	/**
	 * @param Id
	 * @return
	 */
	List<Address> findAddressByCustomerId(int Id);

}
