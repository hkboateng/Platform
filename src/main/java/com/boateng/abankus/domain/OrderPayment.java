package com.boateng.abankus.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the orderpayment database table.
 * 
 */
@Entity
@Table(name="Orderpayment")
@NamedQuery(name="Orderpayment.findAll", query="SELECT o FROM OrderPayment o")
public class OrderPayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int orderPaymentId;

	private double amountPaid;

	private Timestamp paymentDate;

	@OneToOne
	@JoinColumn(name="paymentMethodId")
	private Paymentmethod paymentmethod;

	private String confirmationNumber;
	
	private String paymentSchedule;
	
	private String datePaid;
	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="employeeId")
	private Employee employee;

	//bi-directional many-to-one association to Clientorder
	@ManyToOne
	@JoinColumn(name="clientOrderId")
	private CustomerOrder clientorder;
	
	/**
	 * @return the confirmationNumber
	 */
	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	/**
	 * @param confirmationNumber the confirmationNumber to set
	 */
	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public OrderPayment() {
	}

	/**
	 * @param amountPaid
	 * @param employee
	 * @param clientorder
	 */
	public OrderPayment(double amountPaid, Employee employee,CustomerOrder clientorder,String paymentchedule ) {
		this.amountPaid = amountPaid;
		this.employee = employee;
		this.clientorder = clientorder;
		this.paymentSchedule = paymentchedule ;
	}

	public int getOrderPaymentId() {
		return this.orderPaymentId;
	}

	public void setOrderPaymentId(int orderPaymentId) {
		this.orderPaymentId = orderPaymentId;
	}

	public double getAmountPaid() {
		return this.amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Timestamp getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}


	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public CustomerOrder getClientorder() {
		return this.clientorder;
	}

	public void setClientorder(CustomerOrder clientorder) {
		this.clientorder = clientorder;
	}

	/**
	 * @return the paymentSchedule
	 */
	public String getPaymentSchedule() {
		return paymentSchedule;
	}

	/**
	 * @param paymentSchedule the paymentSchedule to set
	 */
	public void setPaymentSchedule(String paymentSchedule) {
		this.paymentSchedule = paymentSchedule;
	}

	/**
	 * @return the paymentMethodId
	 */
	public Paymentmethod getPaymentMethod() {
		return paymentmethod;
	}

	/**
	 * @param paymentMethodId the paymentMethodId to set
	 */
	public void setPaymentMethod(Paymentmethod paymentMethodId) {
		this.paymentmethod = paymentMethodId;
	}
	
	public void getDate(){
		getPaymentDate().getTime();
	}

	public String getDatePaid() {
		return datePaid;
	}

	public void setDatePaid(String datePaid) {
		this.datePaid = datePaid;
	}

}

