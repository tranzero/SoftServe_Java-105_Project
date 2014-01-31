package com.ita.edu.softserve.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.UsersDAO;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.UserManager;

@Service("userService")
public class UserManagerImpl implements UserManager {

	private static final Logger LOGGER = Logger
			.getLogger(UserManagerImpl.class);
	@Autowired
	private UsersDAO userDao;

	@Transactional
	@Override
	public boolean createUser(String username, String firstname,
			String lastname, String email, String password) {

		try {

			if (userDao.findByName(username) == null) {
				Users tempUser = new Users(username, firstname, lastname,
						email, password);

				userDao.save(tempUser);
				return true;
			}

		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}
		return false;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Users> findAllUsers() {

		return userDao.getAllEntities();
	}

	public static UserManager getInstance() {
		return ManagerFactory.getManager(UserManager.class);
	}

	// no all
	@Override
	public void editUser(String usname) {
		Users user1 = null;
		try {
			user1 = (Users) userDao.findByUsername(usname);

		} catch (Exception e) {
			System.out.println("" + e.getMessage());

		} finally {

		}
		userDao.save(user1);

	}

	// removeUser
	@Override
	@Transactional
	public void removeUser(Integer id) {
		userDao.remove(userDao.findById(id));
	}

	// update user
	
	
	/**
	 * Update database and get list of all users
	 */
	@Override
	public List<Users> updateUsers(Users... userr) {
		List<Users> userUpdateResult = new ArrayList<Users>();
		for (Users user : userr) {
			userUpdateResult.add((Users)userDao.update(user));
			
		}
		return userUpdateResult;
	}


}