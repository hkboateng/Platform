/**
 * hkboateng
 */
package com.boateng.abankus.service.impl;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		payment.setPaymentMethodId(paymentMethod.getPaymentMethodId());
		session.save(payment);
		session.flush();
		
		String confirmationNo = PlatformUtils.generateConfirmationNo();
		return confirmationNo;
	}

}
