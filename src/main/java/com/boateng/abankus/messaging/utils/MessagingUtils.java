package com.boateng.abankus.messaging.utils;

import java.io.IOException;



import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.MessagingFields;
import com.boateng.abankus.messaging.config.MessagingConfig;
import com.rabbitmq.client.Channel;
/**
 * hkboateng
 */
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

/**
 * @author hkboateng
 *
 */
public class MessagingUtils {

	private static final Logger logger = Logger.getLogger(MessagingUtils.class.getCanonicalName());
	private Connection connection = null;
	Channel channel = null;
	public MessagingUtils(){

	}

	
	/**
	 * Connects to the key using the Queue name and the RoutingKey.
	 * @param queue
	 * @param routingKey
	 */
	public void connectToQueue(String message){
		logger.info("Logging for "+message);
	
	try {
		connection = MessagingConfig.getInstance().getConnection();
		channel = connection.createChannel();
		channel.exchangeDeclare(MessagingFields.PAYMENT_CENTER_EXCHANGE_NAME, "direct", true);
		channel.queueDeclare(MessagingFields.PAYMENT_CENTER_MAKE_PAYMENT_QUEUE, true, false, false, null);
		channel.queueBind(MessagingFields.PAYMENT_CENTER_MAKE_PAYMENT_QUEUE, MessagingFields.PAYMENT_CENTER_EXCHANGE_NAME, MessagingFields.PAYMENT_CENTER_MAKE_PAYMENT_KEY);
		channel.basicPublish(MessagingFields.PAYMENT_CENTER_EXCHANGE_NAME, MessagingFields.PAYMENT_CENTER_MAKE_PAYMENT_KEY,true, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
	
		logger.info("Completed sending message to RabbitMq Queue: "+channel.confirmSelect());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			channel.close();
			connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	}

	
}
