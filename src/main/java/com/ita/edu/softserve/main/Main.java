/**
 * 
 */
package com.ita.edu.softserve.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ita.edu.softserve.dao.UsersDAO;
import com.ita.edu.softserve.entity.Users;
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

		UsersDAO usersDao = (UsersDAO) appContext.getBean("usersDao");
		Users users= usersDao.findByName("Lesniak");
		System.out.println(users.getFirstName() + " " + users.getLastName());

	}
}
