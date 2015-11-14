package com.boateng.abankus.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import org.joda.time.DateTime;

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
	
	private static final int TIME_TO_CANCEL_ORDER  =1800000;
	
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

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}


	private String productName;

	private String productCode;

	private BigDecimal totalAmount;
	
	//bi-directional many-to-one association to Orderpayment
	@OneToMany(mappedBy="clientorder", fetch=FetchType.EAGER)
	private List<OrderPayment> orderpayments;
	
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

	public String getOrderNumber() {
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

	public List<OrderPayment> getOrderpayments() {
		return this.orderpayments;
	}
	
	public OrderPayment addOrderpayment(OrderPayment orderpayment) {
		getOrderpayments().add(orderpayment);
		orderpayment.setClientorder(this);

		return orderpayment;
	}

	public boolean removeOrderpayment(OrderPayment orderpayment) {
		
		if(getOrderpayments().contains(orderpayment)){
			getOrderpayments().remove(orderpayment);
			return true;
		}

		return false;
	}
	public boolean isOrderPending(){

		DateTime dt = new DateTime(getOrderDate());
		boolean isPending  = false;
		DateTime sdt = dt.plusMillis(TIME_TO_CANCEL_ORDER);
		DateTime now = DateTime.now();
		if (!now.isAfter(sdt.getMillis())){
			if(now.isAfter(dt.getMillis()) && now.isBefore(sdt.getMillis())){
				isPending = true;
				System.out.println(isPending);
			}
		}

		return isPending;
	}
	
	public  String convertOrderDate(){
		DateTime date = new DateTime(getOrderDate());

		return date.toString("MMMM d, yyyy");
	}
	
	
}