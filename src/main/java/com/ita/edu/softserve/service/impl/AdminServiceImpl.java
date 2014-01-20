package com.ita.edu.softserve.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.UsersDAO;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.service.AdminService;

public class AdminServiceImpl implements AdminService {
	@Autowired
	private UsersDAO userDao;

	@Transactional
	@Override
	public void printAllUsers() {
		List<Users> usersList = new ArrayList<Users>();
		usersList = userDao.getAllUsers();
		for (Users u : usersList) {
			System.out.println("Users name" + u.getFirstName() + " "
					+ u.getLastName());
		}
	}

	/**
	 * Returns the number of all users 
	 */
	@Transactional
	@Override
	public Integer countAllUsers() {		
		int countUsers = userDao.getAllUsers().size();		
		System.out.println("Number of users:" + countUsers);
		return countUsers;
	}
}
