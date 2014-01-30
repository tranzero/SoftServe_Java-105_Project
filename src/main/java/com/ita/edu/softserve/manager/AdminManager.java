package com.ita.edu.softserve.manager;

/**
 * 
 * @author nvrubl
 * @author Mik 
 * 
 */
public interface AdminManager extends BaseManager {
	
	/**
	 * Print all users
	 */
	public void printAllUsers();

	/** Prints the number of all users */	 
	public Long countAllUsers();

}
