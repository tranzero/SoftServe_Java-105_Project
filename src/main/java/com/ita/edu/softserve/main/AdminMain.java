package com.ita.edu.softserve.main;

import com.ita.edu.softserve.manager.TicketsManager;
import com.ita.edu.softserve.manager.impl.TicketsManagerImpl;

public class AdminMain {
	/**
	 * @param args
	 */
	public static void main(String[] args) {		
//		AdminManager adminManager = (AdminManager) AdminManagerImpl.getInstance();
//		adminManager.printAllUsers();
//		adminManager.countAllUsers();
		
		TicketsManager ticketsManager = (TicketsManager) TicketsManagerImpl.getInstance();
		ticketsManager.ticketsForPage(1);
	}	
}





