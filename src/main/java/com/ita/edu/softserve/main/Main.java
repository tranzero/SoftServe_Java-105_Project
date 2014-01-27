/**
 * 
 */
package com.ita.edu.softserve.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ita.edu.softserve.dao.UsersDAO;
import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.service.PostForMainPageService;
import com.ita.edu.softserve.service.UserManager;

/**
 * @author Mik
 * 
 */
public class Main implements MainIface {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		UserManager userService = (UserManager) appContext
				.getBean("userService");

		for(Users user : userService.findAllUsers()) {

			System.out.println(user.getFirstName() + " " + user.getLastName());

		}

	}
}
