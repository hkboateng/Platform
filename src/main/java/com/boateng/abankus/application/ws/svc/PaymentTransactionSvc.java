/**
 * hkboateng
 */
package com.boateng.abankus.application.ws.svc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * @author hkboateng
 *
 */
public class PaymentTransactionSvc {

	private static final Logger logger = Logger.getLogger(PaymentTransactionSvc.class.getName());
	
	private static String paymentServiceURL = "http://localhost:8080/rs/paymentservice";
	
	public String getPaymentTransaction(String month, String year) throws InterruptedException, ExecutionException{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PaymentTransactionSvc.paymentServiceURL);

		Future<String> response = target.path("/findPaymentTransactionByMonthAndYear")
				.queryParam("month", month)
				.queryParam("year", year)
				.request().async().get(new InvocationCallback<String>() {

			@Override
			public void completed(String response) {
				logger.info("");
			}

			@Override
			public void failed(Throwable e) {
				logger.severe("The following error occured while processing....."+e.getMessage());
				e.printStackTrace();
			}
			
		});
		return response.get();
	}
}
