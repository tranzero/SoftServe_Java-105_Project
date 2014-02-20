package com.ita.edu.softserve.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.ita.edu.softserve.manager.UserManager;

public class UserServiceImpl {
	
	@Autowired
	private UserManager usersmanager;
	
	/**  
	 * @return username of logged in user
	 */
	public String getLoggedUsername() {	
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		return user.getUsername();
	}	
	
	/**  
	 *  Checks if user remains on DB after login
	 */
	public boolean isUserFromDb(){		
		return !usersmanager.findByUsername(getLoggedUsername()).equals(null);
	}
}
