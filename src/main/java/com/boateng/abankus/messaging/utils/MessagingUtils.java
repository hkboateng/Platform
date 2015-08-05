package com.boateng.abankus.messaging.utils;

import java.io.IOException;

import com.boateng.abankus.database.configuration.MessagingConfig;
import com.rabbitmq.client.Channel;
/**
 * hkboateng
 */
import com.rabbitmq.client.Connection;

/**
 * @author hkboateng
 *
 */
public class MessagingUtils {

	private static final String username = "guest";
	
	private static final String password = "guest";
	
	private static final String host = "localhost";
	
	private static final int port = 5627;
	private Connection connection = null;
	public MessagingUtils(){
		connection = MessagingConfig.getInstance().getConnection(username, password, host, port);
		try {
			Channel channel = connection.createChannel();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void createChannel(String channelname) throws IOException{

		Channel channel = connection.createChannel();
		channel.exchangeDeclare("platform.app", "direct", true);
		
		
	}
	
	/**
	 * Connects to the key using the Queue name and the RoutingKey.
	 * @param queue
	 * @param routingKey
	 */
	public void connectQueue(String queue,String routingKey){
		
	}

	
}
