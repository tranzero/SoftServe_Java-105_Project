package com.ita.edu.softserve.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.impl.StationsDAOImpl;
import com.ita.edu.softserve.dao.impl.UsersDAOImpl;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private UsersDAOImpl userDao;

	public AdminServiceImpl() {
	}

	/**
	 * Constructor with one argument.
	 * @param stationDao
	 */
	public AdminServiceImpl(UsersDAOImpl userDao) {
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
	@Transactional
	@Override
	public Integer countAllUsers() {		
		int countUsers = userDao.getAllEntities().size();		
		System.out.println("Number of users:" + countUsers);
		return countUsers;
	}
}
