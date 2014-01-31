/**
 * 
 */
package com.ita.edu.softserve.main;

import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.manager.UserManager;
import com.ita.edu.softserve.manager.impl.UserManagerImpl;

/**
 * @author Mik
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		UserManager userService = (UserManager) UserManagerImpl.getInstance();
		
		for(Users user : userService.findAllUsers()) {

			System.out.println(user.getFirstName() + " " + user.getLastName());

		}

		
		
		
	}
}
