/**
 * hkboateng
 */
package com.boateng.abankus.messaging.config;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeoutException;




import com.boateng.abankus.exception.PlatformException;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


/**
 * @author hkboateng
 *
 */
public class MessagingConfig {


	private static final MessagingConfig rabbitmq = new MessagingConfig();
	
	
	
	private MessagingConfig(){}
	
	public static MessagingConfig getInstance(){
		return rabbitmq;
	}
	
	/***
	 * RabbitMQ Consumer Connection
	 * @param es
	 * @return
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public Connection getConnection() throws PlatformException{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setPort(5672);
		Connection conn;
		try {
			conn = factory.newConnection();
		} catch (Exception e) {
			PlatformException ace = new PlatformException(e);
			throw ace;
		}
		
		return conn;
	}
}
