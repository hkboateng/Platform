package com.boateng.abankus.employee.interfaces;

import com.boateng.abankus.domain.Address;
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.Email;
import com.boateng.abankus.domain.Phone;

public interface CustomerService {

	Customer addNewCustomer(Customer customers, Email email, Phone phone, Address address);

}
