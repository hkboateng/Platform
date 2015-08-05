package com.boateng.abankus.database.configuration;

import java.io.IOException;

import com.boateng.abankus.exception.PlatformException;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MessagingConfig {

	Connection conn = null;
	private MessagingConfig(){}
	
	private static final MessagingConfig rabbit  = new MessagingConfig();
	
	public static MessagingConfig getInstance(){
		return rabbit;
	}
	
	public Connection getConnection(String username, String password, String host, int port){
		
		try{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername(username);
		factory.setPassword(password);
		factory.setHost( host);
		factory.setPort(port);
		
		//When connection is lost, the fallback
		factory.setAutomaticRecoveryEnabled(true);
		factory.setNetworkRecoveryInterval(10000);
		//Creating Rabbit Connection
		conn = factory.newConnection();
		
		
		}catch(Exception e){
			PlatformException ace = new PlatformException(e);
			ace.logger(e.getMessage(), e);
		}
		return conn;
	}
	
	/**
	 * Method closes MEssaging Connection if connection 
	 * is open.
	 * @throws IOException
	 */
	public void closeConnection() throws IOException{
		if(conn.isOpen()){
			conn.close();
		}
	}
	
	/**
	 * This methods aborts the Connection of
	 * RabbitMQ.
	 */
	public void abortConnection(){
		if(conn.isOpen()){
			conn.abort(100);
		}
	}
	
}
