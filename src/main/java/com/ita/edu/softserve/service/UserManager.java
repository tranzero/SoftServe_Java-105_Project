package com.ita.edu.softserve.service;

import java.util.List;

import com.ita.edu.softserve.entity.Users;

public interface UserManager extends BaseManager {
	
	public boolean createUser(String username, String firstname,
		    String lastname, String email, String password);
	public List<Users> findAllUsers();

}
