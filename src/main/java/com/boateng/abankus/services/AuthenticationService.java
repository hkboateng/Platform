package com.boateng.abankus.services;

import java.util.List;

import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.Permission;
import com.boateng.abankus.domain.Role;
import com.boateng.abankus.domain.User;


public interface AuthenticationService {

	User AuthenticateUser(String username,String passwd);
	
	List<Permission> findRoleByUser(String username);

	User findUserByUserName(String username);

	List<Role> getAllRoles();

	boolean doEmailExist(String email);
	
	boolean doUserNameExist(String username);

	boolean isEmployeeIdExist(String employeeId);
	
	Employee findEmployeeByUserName(String username);
}
