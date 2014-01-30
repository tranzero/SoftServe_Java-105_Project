package com.ita.edu.softserve.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.impl.UsersDAOImpl;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.manager.AdminManager;

@Service
public class AdminManagerImpl implements AdminManager {
	
	@Autowired
	private UsersDAOImpl userDao;

	public AdminManagerImpl() {
	}

	/**
	 * Constructor with one argument.
	 * @param stationDao
	 */
	public AdminManagerImpl(UsersDAOImpl userDao) {
		this.userDao = userDao;
	}
	
	@Transactional
	@Override
	public void printAllUsers() {
		List<Users> usersList = userDao.getAllEntities();
		for (Users u : usersList) {
			System.out.println("Users name" + u.getFirstName() + " "
					+ u.getLastName());
		}
	}

	/**
	 * Returns the number of all users 
	 */
	@Transactional(readOnly = true)
	@Override
	public Long countAllUsers() {		
		Long countUsers = userDao.getCountAllUsers();		
		System.out.println("Number of users: " + countUsers);
		return countUsers;
	}
}
