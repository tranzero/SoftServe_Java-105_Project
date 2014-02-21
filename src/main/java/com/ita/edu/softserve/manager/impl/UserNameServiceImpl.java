package com.ita.edu.softserve.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.ita.edu.softserve.manager.UserManager;
import com.ita.edu.softserve.manager.UserNameService;

@Service
public class UserNameServiceImpl implements UserNameService {
	
	@Autowired
	private UserManager usersmanager;
	
	/**  
	 * @return username of logged in user
	 */
	public String getLoggedUsername() {	
		try {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getUsername();
		} catch (RuntimeException e){
		
			return "Unregister user";
		}
	}	
		
	
	/**  
	 *  Checks if user remains on DB after login
	 */
	public boolean isUserFromDb(){		
		return !usersmanager.findByUsername(getLoggedUsername()).equals(null);
	}
}
