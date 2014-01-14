/**
 * 
 */
package com.ita.edu.SoftServeUniversity.db_util;

import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * @author tranzero
 *
 */
public class HibernateUtil {
	 public static final Log logger = LogFactory.getLog(HibernateUtil.class
		      .getName());
	 private static SessionFactory sessionFactory;
	 private static ServiceRegistry serviceRegistry;

	     static
	    {
	        try
	        {
	            Configuration configuration = new Configuration().configure();
	            serviceRegistry = new ServiceRegistryBuilder()
	            .applySettings(configuration.getProperties())
	            	.buildServiceRegistry();
	            sessionFactory = configuration.buildSessionFactory
	            											(serviceRegistry);
	            Logger.global.info("Creating SessionFactory was successful!");
	        }
	        catch (HibernateException e)
	        {
	        	logger.error("Initial SessionFactory creation failed." + e);
	            System.err.println("Error creating Session: " + e);
	            throw new ExceptionInInitializerError(e);
	        }
	    }

	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

	    public static void shutdown() {
	     // Close caches and connection pools
	     getSessionFactory().close();
	    }
	    
	       
}
