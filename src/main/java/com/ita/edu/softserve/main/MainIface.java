package com.ita.edu.softserve.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public interface MainIface {
	public static ApplicationContext appContext = 
	    	  new ClassPathXmlApplicationContext("/META-INF/spring/root-context.xml");	

}
