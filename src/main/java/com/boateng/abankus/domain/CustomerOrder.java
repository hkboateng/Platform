package com.boateng.abankus.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.boateng.abankus.customer.service.Client;

import java.util.List;


/**
 * The persistent class for the clientorder database table.
 * 
 */
@Entity
@Table(name="clientorder")
@NamedQuery(name="ClientOrder.findAll", query="SELECT c FROM CustomerOrder c")
public class CustomerOrder implements Client, Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long clientOrderId;

	private long orderDate;

	private int quantity;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customerId", referencedColumnName="customerId")
	private Customer customer;

	private String orderNumber;

	private String unitCost;


	private String productCode;

	private BigDecimal totalAmount;
	
	public CustomerOrder() {
	}
	
	/**
	 * @return the unitCost
	 */
	public String getUnitCost() {
		return unitCost;
	}



	/**
	 * @param unitCost the unitCost to set
	 */
	public void setUnitCost(String unitCost) {
		this.unitCost = unitCost;
	}


	public long getClientOrderId() {
		return this.clientOrderId;
	}

	public void setClientOrderId(long clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public long getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(long orderDate) {
		this.orderDate = orderDate;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getOrderNumbere() {
		return this.orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return the totalAmount
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}


}