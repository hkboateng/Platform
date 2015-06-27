package com.boateng.abankus.employees.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.boateng.abankus.exception.AbankusException;
import com.boateng.abankus.utils.PropertyUtils;
import com.boateng.abankus.fields.EmployeeFields;;
public abstract class AbankusAbstract {
	HttpSession session  = null;
	
	HttpServletRequest request = null;
	/**
	 * This method sends the user to the next page
	 * based on the value of the parameters
	 * 
	 * @param request
	 * @param response
	 * @param nextpage - If the application should send the User 
	 * 					the next page
	 * @param currentpageIf the application should send the User 
	 * 					the current page
	 * @throws AbankusException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void nextPage(HttpServletRequest request, HttpServletResponse response,boolean nextpage, boolean currentpage){
		String next = request.getParameter("punt");
		String current = request.getParameter("currentpage");
		StringBuffer sbr = new StringBuffer();
		sbr.append( "/WEB-INF/");
		if(nextpage){
			String path = PropertyUtils.getInstance().getApplicationPrperty(EmployeeFields.EMPLOYEE_PROPERTY_NAME, next);
			sbr.append(path);
		}
		if(currentpage){
			String path = PropertyUtils.getInstance().getApplicationPrperty(EmployeeFields.EMPLOYEE_PROPERTY_NAME, current);
			sbr.append(path);			
		}
		try {
			request.getRequestDispatcher(sbr.toString()).forward(request, response);
		} catch (ServletException e) {
			AbankusException ace = new AbankusException(e);

		} catch (IOException e) {
			AbankusException ace = new AbankusException(e);

		}
	}
	
	public void test(){
		session  = request.getSession();
	}
}
