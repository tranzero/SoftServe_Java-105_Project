package com.ita.edu.softserve.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.exception.UsersManagerExeption;

/**
 * interface UserManager
 * 
 * @author iryna
 * 
 */
@Service
public interface UserManager extends BaseManager {

	/**
	 * Create new user
	 * 
	 * @param username
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param password
	 * @param role
	 * @return
	 */
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
	 * Update user
	 */
	public void updateUser(Integer userId, String firstName, String lastName,
			String eMail, String passwd, Role role);

	/**
	 * Update user - without role
	 */
	public void updateUser2(Integer userId, String firstName, String lastName,
			String eMail, String passwd);

	/**
	 * Delete user
	 * 
	 * @param id
	 */
	public void removeUser(Integer id);

	/**
	 * Find user by username
	 * 
	 * @param id
	 * @return user
	 */
	public Users findByUsername(String username);

	/**
	 * For paging- get userlist Count
	 * 
	 * @return
	 * @throws UsersManagerExeption
	 */
	public long getUsersListCount() throws UsersManagerExeption;

	/**
	 * For paging- get all users
	 * 
	 * @param from
	 * @param count
	 * @return
	 * @throws UsersManagerExeption
	 */
	public List<Users> getUsersForPage(int from, int count)
			throws UsersManagerExeption;

}
