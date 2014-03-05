package com.ita.edu.softserve.exception;

public class TripsManagerException extends RuntimeException {
	private static final long serialVersionUID = -1365913360418982220L;

	/**
	 * The default constructor.
	 */
	public TripsManagerException() {

	}

	/**
	 * @param msg - the message to add to exception.
	 */
	public TripsManagerException(String msg) {
		super(msg);
	}

	/**
	 * @param msg - the message to add to exception.
	 * @param e - the exception.
	 */
	public TripsManagerException(String msg, Exception e) {
		super(msg, e);
	}
}
