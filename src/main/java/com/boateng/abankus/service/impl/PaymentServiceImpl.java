/**
 * hkboateng
 */
package com.boateng.abankus.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.boateng.abankus.domain.BankInformation;
import com.boateng.abankus.domain.OrderPayment;
import com.boateng.abankus.domain.PaymentTransaction;
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

	@Transactional
	public void saveBankInformation(BankInformation bank,Session session){
		if(session.isOpen()){
			session.save(bank);
		}
	}
	@Override
	@Transactional
	public String submitPayment(OrderPayment payment,Paymentmethod paymentMethod,BankInformation bank){
		Session session = getSessionFactory().getCurrentSession();
		String confirmationNo = PlatformUtils.generateConfirmationNo();
		session.save(paymentMethod);
		if(bank != null){
			bank.setPaymentmethod(paymentMethod);
			saveBankInformation(bank,session);
		}
		
		
		payment.setPaymentMethod(paymentMethod);
		payment.setConfirmationNumber(confirmationNo);
		Serializable i = session.save(payment);
		OrderPayment p = (OrderPayment) session.get(OrderPayment.class, i);
		submtPaymentTransaction(session,p);
		session.flush();
		return confirmationNo;
	}
	
	private void submtPaymentTransaction(Session session,OrderPayment payment){
		PaymentTransaction transaction = new PaymentTransaction(payment);
		session.save(transaction);
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
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<OrderPayment> findAllPaymentsByFromAndToDate(String from,String to){
		Session session = getSessionFactory().getCurrentSession();
		List<OrderPayment> orderPayment = session.createQuery("from PaymentTransaction t where t.paymentDate >=:from and t.paymentDate <=:to")
									.setParameter("from", from)
									.setParameter("to", to)
									.list();
		
		return orderPayment;		
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<PaymentTransaction> findAllPaymentsByDate(String date){
		Session session = getSessionFactory().getCurrentSession();
		List<PaymentTransaction> orderPayment = session.createQuery("from PaymentTransaction t where t.paymentDate =:date")
									.setParameter("date", date)
									.list();
		
		return orderPayment;		
	}	
	
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<PaymentTransaction> findAllMonthPaymentByYearAndMonth(String year, String month){
		Session session = getSessionFactory().getCurrentSession();
		List<PaymentTransaction> orderPayment = session.createQuery("from PaymentTransaction t where t.paymentMonth =:month and t.paymentYear =:year")
									.setParameter("month", month)
									.setParameter("year", year)
									.list();
		
		return orderPayment;			
	}
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<PaymentTransaction> findAllYearPaymentByYear(String year){
		Session session = getSessionFactory().getCurrentSession();
		List<PaymentTransaction> orderPayment = session.createQuery("from PaymentTransaction t where t.paymentYear =:year")
									.setParameter("year", year)
									.list();
		
		return orderPayment;			
	}

	@Override
	@Transactional
	public PaymentTransaction findPaymentTransactionByTransactionId(String transactionId) {
		Session session = getSessionFactory().getCurrentSession();
		PaymentTransaction payment = (PaymentTransaction) session.createQuery("From PaymentTransaction t where t.transactionId =:transactionId")
									.setParameter("transactionId", transactionId)
									.uniqueResult();
		return payment;
	}

	
	/* (non-Javadoc)
	 * @see com.boateng.abankus.services.PaymentService#findPaymentTransactionByCustomerNumber(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<PaymentTransaction> findPaymentTransactionByCustomerNumber(String customerNumber) {
		Session session = getSessionFactory().getCurrentSession();
		List<PaymentTransaction> payment = session.createQuery("From PaymentTransaction t where t.customerNumber =:customerNumber")
									.setParameter("customerNumber", customerNumber)
									.list();
		return payment;
	}	
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<PaymentTransaction> findPaymentTransactionByOrderNumber(String orderNumber) {
		Session session = getSessionFactory().getCurrentSession();
		List<PaymentTransaction> payment = session.createQuery("From PaymentTransaction t where t.orderNumber =:orderNumber")
									.setParameter("orderNumber", orderNumber)
									.list();
		return payment;
	}	
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<OrderPayment> findPaymentsByOrderNumber(String orderNumber) {
		Session session = getSessionFactory().getCurrentSession();
		List<OrderPayment> orderPayment = session.createQuery("from OrderPayment o where o.clientorder.orderNumber =:orderNumber")
		.setParameter("orderNumber", orderNumber)
		.list();
		
		return orderPayment;
	}
}
