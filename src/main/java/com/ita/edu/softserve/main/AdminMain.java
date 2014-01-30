package com.ita.edu.softserve.main;

import com.ita.edu.softserve.manager.AdminManager;
import com.ita.edu.softserve.manager.impl.AdminManagerImpl;

public class AdminMain {
	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		AdminManager adminManager = (AdminManager) AdminManagerImpl.getInstance();
		adminManager.printAllUsers();
		adminManager.countAllUsers();
	}	
}





