/**
 * hkboateng
 */
package com.boateng.abankus.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boateng.abankus.domain.OrderPayment;
import com.boateng.abankus.domain.Paymentmethod;
import com.boateng.abankus.services.PaymentService;
import com.boateng.abankus.utils.PlatformUtils;

/**
 * @author hkboateng
 *
 */

public class PaymentServiceImpl implements PaymentService {
	private static final Logger logger = Logger.getLogger(PaymentServiceImpl.class.getName());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 
	 * @param sessionFactory
	 */

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public String submitPayment(OrderPayment payment,Paymentmethod paymentMethod){
		Session session = getSessionFactory().getCurrentSession();
		
		session.save(paymentMethod);
		payment.setPaymentMethod(paymentMethod);
		String confirmationNo = PlatformUtils.generateConfirmationNo();
		
		payment.setConfirmationNumber(confirmationNo);
		session.save(payment);
		session.flush();
		
		
		return confirmationNo;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<OrderPayment> findPaymentsByOrderId(long customerOrderId){
		Session session = getSessionFactory().getCurrentSession();
		List<OrderPayment> orderPayment = session.createQuery("from OrderPayment o where o.clientorder.clientOrderId =:orderId")
		.setParameter("orderId", customerOrderId)
		.list();
		
		return orderPayment;
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.services.PaymentService#findPaymentsByOrderId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<OrderPayment> findPaymentsByOrderId(int customerId) {
		Session session = getSessionFactory().getCurrentSession();
		List<OrderPayment> orderPayment = session.createQuery("from OrderPayment o where o.clientorder.customer.customerId =:customerId")
		.setParameter("customerId", customerId)
		.list();
		
		return orderPayment;
	}

}
