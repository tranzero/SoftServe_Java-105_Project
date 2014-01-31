package com.ita.edu.softserve.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ita.edu.softserve.entity.Users;

@Service
public interface UserManager extends BaseManager {

	public boolean createUser(String username, String firstname,
			String lastname, String email, String password);

	public List<Users> findAllUsers();
	
	public void editUser (String usname);

}
