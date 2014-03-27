package com.ita.edu.softserve.components.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ita.edu.softserve.components.CustomPasswordEncoder;
import com.ita.edu.softserve.entity.Users;

@Component
public class CustomPasswordEncoderImpl implements CustomPasswordEncoder{
	
	/**
	 * Method for password encoding by using BCryptPasswordEncoder
	 * 
	 * @param user
	 */
	public void encodePassword(Users user) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String usrPassword = user.getPassword();
		String hashedPassword = passwordEncoder.encode(usrPassword);		
		user.setPassword(hashedPassword);			
	}
}