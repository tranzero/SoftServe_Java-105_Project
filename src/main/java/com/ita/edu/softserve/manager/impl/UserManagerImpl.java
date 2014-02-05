package com.ita.edu.softserve.manager.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.UsersDAO;
import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.UserManager;

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
			String eMail, String passwd, Role role) {

		Users userr = userDao.findById(userId);

		userr.setFirstName(firstName);
		userr.setLastName(lastName);
		userr.seteMail(eMail);

		userr.setPasswd(passwd);
		userr.setRole(role);

		userDao.update(userr);

	}
	
	//updateUser2 bez role
	@Transactional
	public void updateUser2(Integer userId, String firstName, String lastName,
			String eMail, String passwd) {

		Users userr = userDao.findById(userId);

		userr.setFirstName(firstName);
		userr.setLastName(lastName);
		userr.seteMail(eMail);

		userr.setPasswd(passwd);		

		userDao.update(userr);

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
	public Users findByUsername(String username) {
		return (Users) userDao.findByUsername(username);
	}

}
