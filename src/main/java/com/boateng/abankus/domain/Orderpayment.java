package com.boateng.abankus.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the orderpayment database table.
 * 
 */
@Entity
@NamedQuery(name="Orderpayment.findAll", query="SELECT o FROM Orderpayment o")
public class Orderpayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int orderPaymentId;

	private BigDecimal amountPaid;

	private Timestamp paymentDate;

	private int paymentMethodId;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="employeeId")
	private Employee employee;

	//bi-directional many-to-one association to Clientorder
	@ManyToOne
	@JoinColumn(name="clientOrderId")
	private Clientorder clientorder;

	public Orderpayment() {
	}

	public int getOrderPaymentId() {
		return this.orderPaymentId;
	}

	public void setOrderPaymentId(int orderPaymentId) {
		this.orderPaymentId = orderPaymentId;
	}

	public BigDecimal getAmountPaid() {
		return this.amountPaid;
	}

	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Timestamp getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getPaymentMethodId() {
		return this.paymentMethodId;
	}

	public void setPaymentMethodId(int paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Clientorder getClientorder() {
		return this.clientorder;
	}

	public void setClientorder(Clientorder clientorder) {
		this.clientorder = clientorder;
	}

}