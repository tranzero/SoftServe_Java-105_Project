package com.ita.edu.softserve.components.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ita.edu.softserve.components.CustomPasswordEncoder;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.manager.UserManager;

@Component
public class CustomPasswordEncoderImpl implements CustomPasswordEncoder{
	
	@Autowired
	private UserManager usersmanage;

	/**
	 * Method for password encoding by using BCryptPasswordEncoder
	 * 
	 * @param user
	 */
	public void encodePassword(Users user) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String usrPassword = user.getPassword();
		String hashedPassword = passwordEncoder.encode(usrPassword);
		if (isSamePassword(user)){
			user.setPassword(hashedPassword);
		}
	}
	
	/**
	 * Condition to investigate if entered user password matches password in DB
	 * 
	 * @param user
	 */	
	private boolean isSamePassword(Users user) {
		try {
			if(!user.getPassword().equals(usersmanage.findUser(user.getUserId()).getPassword())){
				return true;
			}			
		} catch (RuntimeException e) {
			return true;
		}
		return false;
	}
}