package com.ita.edu.softserve.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.ita.edu.softserve.manager.UserManager;
import com.ita.edu.softserve.manager.UserNameService;


@Service
public class UserNameServiceImpl implements UserNameService {
	
	private static final String UNREGISTERED_USER = "Unregistered user";
	
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
			return UNREGISTERED_USER;
		}
	}			
	
	/**  
	 * @return userId of logged in user
	 */
	public Integer getLoggedUserId() {	
		if (isUserFromDb()){			
			return usersmanager.findByUsername(getLoggedUsername()).getUserId();
		} 		
			return null;		
	}	
	
	/**  
	 *  Checks if user remains on DB after login to prevent his actions
	 */
	public boolean isUserFromDb(){		
		if (!getLoggedUsername().equals(UNREGISTERED_USER)) {		
			if (usersmanager.findByUsername(getLoggedUsername())!= null){
				return true;
			}				
		}		
		return false;	
	}
}
