/**
 * hkboateng
 */
package com.boateng.abankus.messaging.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author hkboateng
 *
 */
@Configuration
public class MessagingConfig {

	 /**
	 * Default Queue
	 */
	private static final String EXCHANGE_NAME = "platform";

	private static final String QUEUE_NAME = "platform";
	private String queue;
	
	@Bean
	 public ConnectionFactory connectionFactory() {
	   CachingConnectionFactory factory =   new CachingConnectionFactory("localhost");
	   factory.setUsername("guest");
	   factory.setPassword("guest");
	   factory.setPort(5627);
	     
	     return factory;
	 }
	 
	 @Bean
	 public RabbitTemplate rabbitTemplate(){
		 RabbitTemplate template = new RabbitTemplate(connectionFactory());
		 template.setExchange(MessagingConfig.EXCHANGE_NAME);
		 return template;
	 }
	 
	 /**
	  * Must set queue or else the queue will default to platform queue.
	  * @return new Queue
	  */
	 @Bean
	 public Queue getQueueName(){
		 Queue queue = null;
		 if(getQueue() != null){
			queue = new Queue(getQueue());
		 }else{
			 queue = new Queue(MessagingConfig.QUEUE_NAME);
		 }
		 return queue;
	 }

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}
}
