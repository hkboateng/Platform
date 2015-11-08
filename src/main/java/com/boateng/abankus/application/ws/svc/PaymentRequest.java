/**
 * hkboateng
 */
package com.boateng.abankus.application.ws.svc;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.joda.time.DateTime;

import com.boateng.abankus.application.builders.CustomerPaymentBuilder;
import com.boateng.abankus.application.interfaces.PlatformRequest;

/**
 * This POJO is used to create a request class that is sent to the
 * Payment Web Service
 * @author hkboateng
 *
 */
public class PaymentRequest implements PlatformRequest{

	
	private CustomerPaymentBuilder builder;
	
	private String requestDate;
	
	private String employeeId;
	
	private String orderId;
	
	@Override
	public void buildRequest(){
		this.requestDate = DateTime.now().toString();
		this.orderId = builder.getOrderNumber();
	}
	
	@Override
	public void sendRequest(){
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(PaymentRequest.class);
			Client client = ClientBuilder.newClient();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PaymentRequest(CustomerPaymentBuilder builder,String employeeNumber){
		this.builder = builder;
		if(employeeNumber !=null){
			this.employeeId = employeeNumber;
		}
	}

	/**
	 * @return the builder
	 */
	public CustomerPaymentBuilder getBuilder() {
		return builder;
	}

	/**
	 * @param builder the builder to set
	 */
	public void setBuilder(CustomerPaymentBuilder builder) {
		this.builder = builder;
	}

	/**
	 * @return the requestDate
	 */
	public String getRequestDate() {
		return requestDate;
	}

	/**
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.application.interfaces.PlatformRequest#getConfirmationNumber()
	 */
	@Override
	public String getConfirmationNumber() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
