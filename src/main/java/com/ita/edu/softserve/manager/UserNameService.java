package com.ita.edu.softserve.manager;

import org.springframework.stereotype.Service;

@Service
public interface UserNameService {

	public String getLoggedUsername();
	
	public boolean isUserFromDb();
	
	public Integer getLoggedUserId();
}
