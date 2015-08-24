/**
 * hkboateng
 */
package com.boateng.abankus.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author hkboateng
 *
 */
@Table
@Entity
public class Team  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5080090467892919355L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int teamId;
	
	@NotNull
	private String teamname;
	
	@NotNull
	private String teamNumber;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="employeeId", referencedColumnName="employeeId")		
	private Employee employee;
	
	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the teamId
	 */
	public int getTeamId() {
		return teamId;
	}

	/**
	 * @param teamId the teamId to set
	 */
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	/**
	 * @return the teamname
	 */
	public String getTeamname() {
		return teamname;
	}

	/**
	 * @param teamname the teamname to set
	 */
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	/**
	 * @return the teamNumber
	 */
	public String getTeamNumber() {
		return teamNumber;
	}

	/**
	 * @param teamNumber the teamNumber to set
	 */
	public void setTeamNumber(String teamNumber) {
		this.teamNumber = teamNumber;
	}

	/**
	 * no-argument default constructor
	 */
	public Team() {
	}

	/**
	 * @param teamname
	 * @param teamNumber
	 */
	public Team(String teamname, String teamNumber) {
		this.teamname = teamname;
		this.teamNumber = teamNumber;
	}
	public static synchronized String generateTeamId(){
		StringBuffer sbr = new StringBuffer();
		sbr.append("T");
		String numbers = RandomStringUtils.randomNumeric(8);
		sbr.append(numbers);
		return sbr.toString();	
	}

	
}
