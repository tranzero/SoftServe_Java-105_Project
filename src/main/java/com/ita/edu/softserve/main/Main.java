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
		    	  new ClassPathXmlApplicationContext("/META-INF/spring/root-context.xml");		 

//		UsersDAO usersDao = (UsersDAO) appContext.getBean("usersDao");
//		Users users= usersDao.findByName("Lesniak");
//		
//		System.out.println();
//		System.out.println(users.getFirstName() + " " + users.getLastName());
		
		UserService us = (UserService) appContext.getBean("userService");
		boolean user = us.createUser("sdfgsdfs", "sdfgsdfs", "sdfgsdfs", "sdfgsdfs", "sdfgsdfs");
		System.out.println(user);

	}
}
