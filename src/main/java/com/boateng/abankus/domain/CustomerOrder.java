package com.boateng.abankus.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.joda.time.DateTime;

import com.boateng.abankus.customer.service.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the clientorder database table.
 * 
 */
@Entity
@Table(name="clientorder")
@DynamicUpdate(value=true)
@NamedQuery(name="ClientOrder.findAll", query="SELECT c FROM CustomerOrder c")
public class CustomerOrder implements Client, Serializable{
	private static final long serialVersionUID = 1L;
	
	private static final int TIME_TO_CANCEL_ORDER  =1800000;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long clientOrderId;

	@NotNull
	private long orderDate;

	private int quantity;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customerId", referencedColumnName="customerId")
	private Customer customer;

	
	private String orderNumber;

	private String paymentStatus;

	
	private String productCode;

	@NotNull
	private BigDecimal totalAmount;
	
	private String nextPaymentDate;
	
	private BigDecimal amountRemaining;
	
	private BigDecimal amountPaid;
	
	
	/**
	 * Whether payment is daily, monthly,bi-weekly, yearly etc.
	 */
	private String paymentFrequency;
	
	@JsonIgnore
	@OneToMany(mappedBy="clientorder", fetch=FetchType.EAGER)
	private List<OrderPayment> orderpayments;
	
	public CustomerOrder() {
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

	
	
	public String getPaymentStatus() {
		if(amountRemaining().doubleValue() <=0.00){
			return PaymentStatus.valueOf("paid".toUpperCase()).name();
		}else{
			return PaymentStatus.valueOf("repayment".toUpperCase()).name();					
		}		

			

	}

	public double amountPaid(){
		double total = 0.0;
		if( getOrderpayments() != null){
			for(OrderPayment o: getOrderpayments()){
				total +=o.getAmountPaid();
			}
		}		
		return total;
	}
	
	/**
	 * Default value for paymentStatus is "Repayment"
	 * @param paymentStatus
	 */
	public void setPaymentStatus(String paymentStatus) {
		if(amountRemaining() != null){
			if(amountRemaining().doubleValue() <=0.00){
				this.paymentStatus = "paid";
			}else{
				if(paymentStatus !=null){
					this.paymentStatus = paymentStatus;
				}else{
					this.paymentStatus = "repayment";
				}					
			}
		}else{
			this.paymentStatus = "repayment";
		}

	}

	public void setOrderpayments(List<OrderPayment> orderpayments) {
		this.orderpayments = orderpayments;
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
			}
		}

		return isPending;
	}
	
	public  String convertOrderDate(){
		DateTime date = new DateTime(getOrderDate());
		return date.toString("MMMM d, yyyy");
	}
	
	public BigDecimal amountRemaining(){
		BigDecimal total = null;
		BigDecimal amtRemaining = BigDecimal.valueOf(amountPaid());
		total = getTotalAmount() != null ? getTotalAmount().subtract(amtRemaining) : null; 
		return total;
		
	}

	public String getNextPaymentDate() {
		return nextPaymentDate;
	}

	public void setNextPaymentDate(String nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}

	public String getPaymentFrequency() {
		return paymentFrequency;
	}

	public void setPaymentFrequency(String paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}

	public BigDecimal getAmountRemaining() {
		return amountRemaining();
	}

	public void setAmountRemaining(BigDecimal amountRemaining) {
		this.amountRemaining = amountRemaining;
	}

	public BigDecimal getAmountPaid() {
		return new BigDecimal(amountPaid());
	}

	/*
	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}
	*/
	
}