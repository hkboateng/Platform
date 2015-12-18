/**
 * hkboateng
 */
package com.boateng.abankus.messaging.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

import com.boateng.abankus.fields.MessagingFields;

/**
 * @author hkboateng
 *
 */
//@Configuration
public class RabbitConfiguration {

	@Autowired
	private ConnectionFactory factory;
	
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setReplyTimeout(60000);
		RetryTemplate retryTemplate = new RetryTemplate();
		ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
		backOffPolicy.setInitialInterval(500);
		backOffPolicy.setMultiplier(10.0);
		backOffPolicy.setMaxInterval(10000);
		retryTemplate.setBackOffPolicy(backOffPolicy);
		rabbitTemplate.setRetryTemplate(retryTemplate);        
        return rabbitTemplate;
    }

	
	/**
	 * Queue to submit customer bill payment
	 * 
	 * @return new Queue instance
	 */
	@Bean
	public Queue addBillPaymentQueue(){
		return new Queue(MessagingFields.PAYMENT_CENTER_MAKE_PAYMENT_QUEUE);
		
	}
	
	/**
	 * Queue to update customer bill payment
	 * 
	 * @return new Queue instance
	 */
	@Bean
	public Queue updateBillPaymentQueue(){
		return new Queue(MessagingFields.PAYMENT_CENTER_UPDATE_PAYMENT_QUEUE);
		
	}
}
