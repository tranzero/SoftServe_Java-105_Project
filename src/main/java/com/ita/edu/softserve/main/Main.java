/**
 * 
 */
package com.ita.edu.softserve.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.ita.edu.softserve.service.UserService;

/**
 * @author Mik
 *
 */
public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		ApplicationContext appContext = 
		    	  new ClassPathXmlApplicationContext("/META-INF/context.xml");		 
		UserService user = (UserService) appContext.getBean("transactionManager");		
		user.createUser("TheDarkLord", "Darth", "Vader", "darkforce@sith.com", "force"); 	
	}
}
