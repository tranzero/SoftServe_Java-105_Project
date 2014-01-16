package com.ita.edu.softserve.dao;

import com.ita.edu.softserve.entity.Users;



public interface UsersDAO {
	
	//
	Users findByName(String name);

    void save(Users user);

    void remove(Users user);

    Users update(Users user);
    
    //

}
