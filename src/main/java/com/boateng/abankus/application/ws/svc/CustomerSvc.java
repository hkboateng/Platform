/**
 * hkboateng
 */
package com.boateng.abankus.application.ws.svc;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import boateng.abankus.customerws.objects.Customer;

/**
 * @author hkboateng
 *
 */
public class CustomerSvc {
	private static final String portname = "CustomerServiceImplPort";
	
	private static final String servicename = "CustomerServiceImplService";
	public void send(Customer customer) throws MalformedURLException{
		try {
			URL url = new URL("http://localhost:8404/customerws/services/CustomerServiceImpl?wsdl");
			

			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			jaxbMarshaller.marshal(customer, System.out);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
