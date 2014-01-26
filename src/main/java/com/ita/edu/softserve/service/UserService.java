package com.ita.edu.softserve.service;

import java.util.List;

import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.entity.Users;

public interface UserService {
	
	public boolean createUser(String username, String firstname,
		    String lastname, String email, String password);
	public List<Users> findAllUsers();

}
