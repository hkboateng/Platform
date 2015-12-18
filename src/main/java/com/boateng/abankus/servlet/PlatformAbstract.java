/**
 * hkboateng
 */
package com.boateng.abankus.servlet;


import com.boateng.abankus.domain.Employee;

/**
 * Contains methods and vairables that can be applied to every class.
 * @author hkboateng
 *
 */
public abstract class PlatformAbstract {

	public String logActivity(String activity,Employee employee){
		StringBuilder sbr = new StringBuilder();
		sbr.append("Staff ").append(employee.toString());
		sbr.append(" "+activity);
		return sbr.toString();
	}
}
