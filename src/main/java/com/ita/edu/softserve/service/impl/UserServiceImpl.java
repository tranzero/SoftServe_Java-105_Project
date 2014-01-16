package com.ita.edu.softserve.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;





import com.ita.edu.softserve.daoiface.UsersDAO;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.service.UserService;





public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = Logger
			.getLogger(UserServiceImpl.class);
	@Autowired
	private UsersDAO userDao;
	
	
	
	@Transactional
	@Override
	public boolean createUser(String username, String firstname,
			String lastname, String email, String password)
			{

		try {

				Users tempUser = new Users(username, firstname, lastname, email,
					password);

				if (userDao.findByName(username) == null) {
				
					userDao.save(tempUser);
					return true;

				} 

			} catch (Exception e) {
			LOGGER.error(e);
			
				}
		return false;
			}
	
}