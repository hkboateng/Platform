package com.boateng.abankus.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtils {
    private static final SessionFactory sessionFactory;
    
    static {
        try {
        	Configuration config = new Configuration().configure("/Hibernate/hibernate.cfg.xml");
        	StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        	serviceRegistryBuilder.applySettings(config.getProperties());
        	 ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
             sessionFactory = config.buildSessionFactory(serviceRegistry);       	
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
