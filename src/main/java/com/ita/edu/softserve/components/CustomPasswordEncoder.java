package com.ita.edu.softserve.components;

import com.ita.edu.softserve.entity.Users;

public interface CustomPasswordEncoder {
	
	/**
	 * Method for password encoding by using BCryptPasswordEncoder
	 */
	public void encodePassword(Users user);
	
}
