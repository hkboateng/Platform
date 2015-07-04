package com.boateng.abankus.configuration;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQConfig {

	private RabbitMQConfig(){}
	
	private static final RabbitMQConfig rabbit  = new RabbitMQConfig();
	
	public static RabbitMQConfig getInstance(){
		return rabbit;
	}
	
	public Connection getConnection(String username, String password, String host, int port){
		Connection conn = null;
		try{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername(username);
		factory.setPassword(password);
		factory.setHost( host);
		factory.setPort(port);
		//Creating Rabbit Connection
		conn = factory.newConnection();
		
		
		}catch(Exception e){
			
		}
		return conn;
	}
	
	
}
