package com.ita.edu.softserve.exception;

/**
 * class UsersManagerExeption
 * 
 * @author iryna
 *
 */
public class UsersManagerExeption extends RuntimeException {

	private static final long serialVersionUID = -5340409645019171436L;

	/**
	 * The default constructor.
	 */
	public UsersManagerExeption() {

	}

	/**
	 * @param msg - the message to add to exception.
	 */
	public UsersManagerExeption(String msg) {
		super(msg);
	}

	/**
	 * @param msg - the message to add to exception.
	 * @param e - the exception.
	 */
	public UsersManagerExeption(String msg, Exception e) {
		super(msg, e);
	}

}
