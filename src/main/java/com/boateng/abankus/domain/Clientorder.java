package com.boateng.abankus.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the clientorder database table.
 * 
 */
@Entity
@NamedQuery(name="Clientorder.findAll", query="SELECT c FROM Clientorder c")
public class Clientorder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long clientOrderId;

	private BigInteger orderDate;

	private String orderNumber;

	private String productCode;

	private int quantity;

	private BigDecimal totalAmount;

	private String unitCost;

	//bi-directional many-to-one association to Orderpayment
	@OneToMany(mappedBy="clientorder", fetch=FetchType.EAGER)
	private List<Orderpayment> orderpayments;

	public Clientorder() {
	}

	public long getClientOrderId() {
		return this.clientOrderId;
	}

	public void setClientOrderId(long clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public BigInteger getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(BigInteger orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getUnitCost() {
		return this.unitCost;
	}

	public void setUnitCost(String unitCost) {
		this.unitCost = unitCost;
	}

	public List<Orderpayment> getOrderpayments() {
		return this.orderpayments;
	}

	public void setOrderpayments(List<Orderpayment> orderpayments) {
		this.orderpayments = orderpayments;
	}

	public Orderpayment addOrderpayment(Orderpayment orderpayment) {
		getOrderpayments().add(orderpayment);
		orderpayment.setClientorder(this);

		return orderpayment;
	}

	public Orderpayment removeOrderpayment(Orderpayment orderpayment) {
		getOrderpayments().remove(orderpayment);
		orderpayment.setClientorder(null);

		return orderpayment;
	}

}