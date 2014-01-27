package com.ita.edu.softserve.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class ManagerFactory {
	private ManagerFactory() { }

	private final static ApplicationContext appContext = 
	    	  new ClassPathXmlApplicationContext("/META-INF/spring/root-context.xml");
	
	private static ApplicationContext getApplicationContext()
	{
		return appContext;
	}
	
	public static <S extends BaseManager> S getManager(Class<S> clazz) {
		return (S) getApplicationContext().getBean(clazz);
	}
	
}
