/**
 * hkboateng
 */
package com.boateng.abankus.application.ws.svc;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.joda.time.DateTime;

import com.boateng.abankus.application.interfaces.PlatformRequest;
import com.boateng.abankus.domain.Product;
import com.boateng.abankus.exception.PlatformException;

/**
 * @author hkboateng
 *
 */
public class ProductRequest implements PlatformRequest {

	private String requestDate;
	
	private Product product;
	
	public ProductRequest(Product product){
		if(product != null){
			this.product = product;
		}
	}
	
	@Override
	public void buildRequest() throws PlatformException {
		this.requestDate = DateTime.now().toString();
	}

	@Override
	public void sendRequest() throws PlatformException {
		Client client = ClientBuilder.newClient();
		//client.t

	}

	@Override
	public String getConfirmationNumber() {
		// TODO Auto-generated method stub
		return null;
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
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	
}
