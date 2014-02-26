package com.ita.edu.softserve.manager.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.UsersDAO;
import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.exception.TransprtsManagerException;
import com.ita.edu.softserve.exception.UsersManagerExeption;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.UserManager;

/**
 * UserManagerImpl
 * 
 * @author iryna
 * 
 */
@Service("userService")
public class UserManagerImpl implements UserManager {

	private static final Logger LOGGER = Logger
			.getLogger(UserManagerImpl.class);

	@Autowired
	private UsersDAO userDao;

	public static UserManager getInstance() {
		return ManagerFactory.getManager(UserManager.class);
	}

	/**
	 * Create new user
	 */
	@Transactional
	@Override
	public boolean createUser(String username, String firstname,
			String lastname, String email, String password, Role role) {
		Users tempUser = null;
		try {
			tempUser = (Users) userDao.findByUsername(username);
		} catch (NoResultException nr) {
		}
		if (tempUser == null) {
			tempUser = new Users(username, firstname, lastname, email,
					password, role);
			userDao.save(tempUser);
			System.out.println("Successfully registered!");
			return true;

		}
		return false;
	}

	/**
	 * Find all users
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Users> findAllUsers() {
		return userDao.getAllEntities();
	}

	/**
	 * Find user by id
	 */
	@Override
	@Transactional
	public Users findUser(Integer id) {
		return userDao.findById(id);
	}

	/**
	 * Update user (for userEdit.jsp)
	 */
	@Override
	@Transactional
	public void updateUser(Integer userId, String firstName, String lastName,
			String email, String password, Role role) {

		Users user = userDao.findById(userId);

		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(role);

		userDao.update(user);
	}
	
	///
	
	@Transactional(readOnly = false)
	@Override
	public void saveOrUpdateUser(Users user) {
		userDao.saveOrUpdate(user);
		
		/*try {
			userDao.saveOrUpdate(transport);
		} catch (RuntimeException e) {
			RuntimeException ex = new TransprtsManagerException(
					saveOrUpdateTransportMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}*/
	}

	/**
	 * Update user - without role
	 */
	@Transactional
	public void updateUser2(Integer userId, String firstName, String lastName,
			String email, String password) {

		Users user = userDao.findById(userId);

		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);

		userDao.update(user);
	}

	/**
	 * Delete user of DB
	 */
	@Override
	@Transactional
	public void removeUser(Integer id) {
		userDao.remove(userDao.findById(id));
	}

	/**
	 * Find user by username
	 */
	@Override
	@Transactional
	public Users findByUsername(String username){		
		try {
			return (Users) userDao.findByUsername(username);
		} catch (NoResultException e) {
			System.out.println("User does not exist!");
		}
		return null;
	}

	/**
	 * For paging- get userlist Count
	 */
	@Override
	public long getUsersListCount() throws UsersManagerExeption {
		try {
			return userDao.getUsersListCount();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new UsersManagerExeption("Could not get Users list count", e);
		}
	}

	/**
	 * For paging- get all users
	 */
	@Override
	public List<Users> getUsersForPage(int from, int count)
			throws UsersManagerExeption {
		try {
			return userDao.getUsersForOnePage(from - 1, count);
		} catch (Exception e) {
			LOGGER.error(e);
			throw new UsersManagerExeption("Could not get Users for one page",
					e);
		}

	}
}
