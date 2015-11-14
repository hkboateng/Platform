/**
 * hkboateng
 */
package com.boateng.abankus.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.boateng.abankus.application.interfaces.Billing;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.processors.ProductServiceProcessor;
import com.boateng.abankus.services.ProductService;
import com.boateng.abankus.utils.SecurityUtils;

/**
 * Collection of all customer's orders and its payments
 * @author hkboateng
 *
 */
public class CustomerBilling implements Billing {

	@Autowired
	private ProductServiceProcessor productServiceProcessor;
	
	private String billingId;
	
	private Customer customer;
	
	private CustomerOrder clientOrderId;
	
	private DateTime orderDate;
	
	private String totalOrderAmount;
	
	private String productCode;
	
	private String productName;
	/**
	 * List of Payments for an Order
	 */
	private List<OrderPayment> payments;

	/**
	 * Map of Order Payments with orderNumber as Key.
	 */
	private Map<String, List<OrderPayment>> paymentMap;
	
	public CustomerBilling(CustomerOrder clientOrderId){
		paymentMap = new HashMap<String, List<OrderPayment>>();
		if(clientOrderId != null){
			this.clientOrderId = clientOrderId;
			this.orderDate = convertDateFromLong(clientOrderId);
			this.totalOrderAmount = clientOrderId.getTotalAmount().toString();
			this.customer = clientOrderId.getCustomer();
			this.productCode = clientOrderId.getProductCode();
		}
	}

	/**
	 * @param payments the payments to set
	 */
	public void setPayments(OrderPayment payments) {
		if(payments != null){
			 addPaymentListToMap(payments);
		}
	}

	private DateTime convertDateFromLong(CustomerOrder order){
		DateTime dateTime = new DateTime(order.getOrderDate());
		
		return dateTime;
	}
	/**
	 * @return the billingId
	 */
	public String getBillingId() {
		return billingId;
	}

	/**
	 * @param billingId the billingId to set
	 */
	public void setBillingId(String billingId) {
		this.billingId = billingId;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the clientOrderId
	 */
	public CustomerOrder getClientOrderId() {
		return clientOrderId;
	}

	/**
	 * @param clientOrderId the clientOrderId to set
	 */
	public void setClientOrderId(CustomerOrder clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	/**
	 * @return the orderDate
	 */
	public DateTime getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(DateTime orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the totalOrderAmount
	 */
	public String getTotalOrderAmount() {
		return totalOrderAmount;
	}

	/**
	 * @param totalOrderAmount the totalOrderAmount to set
	 */
	public void setTotalOrderAmount(String totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}

	/**
	 * @return the payments
	 */
	public List<OrderPayment> getPayments() {
		return payments;
	}



	/**
	 * @return the paymentMap
	 */
	public Map<String, List<OrderPayment>> getPaymentMap() {
		return paymentMap;
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

	public void addPaymentToList(OrderPayment payment){
		payments = new ArrayList<OrderPayment>();
		if(getClientOrderId().getOrderNumber().equals(payment.getClientorder().getOrderNumber())){
			payments.add(payment);
		}
	}
	
	public void addPaymentListToMap(OrderPayment payment){
		String orderNumber = payment.getClientorder().getOrderNumber();
		if(getPaymentMap().containsKey(orderNumber)){
			payments = getPaymentMap().get(orderNumber);
			payments.add(payment);
		}else{
			payments = new ArrayList<OrderPayment>();
			payments.add(payment);
			getPaymentMap().put(orderNumber, payments);
		}
	}
	public float totalAmountPaid(){
		float total = 0.0f;
		if(getPayments() != null && !getPayments().isEmpty()){
			
			for(OrderPayment payment: payments){
				total +=payment.getAmountPaid();
			}
		}
		
		return total;
	}
	
	public double totalAmountRemaining(){
		double total = 0.00;
		BigDecimal d = BigDecimal.ZERO;
		if(!finishPaying()){
			double amountPaid = totalAmountPaid();
			double totalAmount = Double.valueOf(getTotalOrderAmount());
			
			total = totalAmount - amountPaid;
			d = new BigDecimal(total);
			d = d.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		
		return d.doubleValue();
	}
	
	public boolean finishPaying(){
		boolean finished = false;
		float totalAmount = Float.valueOf(getTotalOrderAmount());
		if(totalAmount == totalAmountPaid()){
			finished = true;
		}
		return finished;
	}
	public  String convertOrderDate(){
		DateTime date = new DateTime(getOrderDate());

		return date.toString("MMMM d yyyy");
	}
	
	public Product getProductByProductCode(String productCode){
		Product product = productServiceProcessor.findProductByProductCode(productCode);
		return product;
	}
	
	public String encryptOrderNumber(String orderNumber)throws PlatformException{
		try {
			return SecurityUtils.encryptOrderNumber(orderNumber);
		} catch (Exception e) {
			PlatformException ace = new PlatformException(e);
			ace.logger(Level.SEVERE, e.getMessage(), ace);
			throw ace;
		}
	}
}
