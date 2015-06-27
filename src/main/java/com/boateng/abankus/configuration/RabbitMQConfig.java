package com.boateng.abankus.configuration;

public class RabbitMQConfig {

	private RabbitMQConfig(){}
	
	private static final RabbitMQConfig rabbit  = new RabbitMQConfig();
	
	public static RabbitMQConfig getInstance(){
		return rabbit;
	}
}
