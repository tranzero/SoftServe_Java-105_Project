package com.ita.edu.softserve.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.entity.Users;

@Service
public interface UserManager extends BaseManager {

	public boolean createUser(String username, String firstname,
			String lastname, String email, String password, Role role);

	/**
	 * Find All users
	 * 
	 * @return List of Users
	 */
	public List<Users> findAllUsers();

	/**
	 * Find a user by id
	 * 
	 * @param id
	 * @return user
	 */
	public Users findUser(Integer id);

	/**
	 * Edit user
	 * 
	 * @param usname
	 */
	public void editUser(String usname);

	/**
	 * Update user
	 */
	public void updateUser(Integer userId, String firstName, String lastName,
			String eMail, String passwd, Role role);

	/**
	 * Delete user
	 * 
	 * @param id
	 */
	public void removeUser(Integer id);

}
