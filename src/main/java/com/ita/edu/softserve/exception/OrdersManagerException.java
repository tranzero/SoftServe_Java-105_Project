package com.ita.edu.softserve.exception;

public class OrdersManagerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5106980682116269991L;


	/**
	 * The default constructor.
	 */
	public OrdersManagerException() {
	}

	/**
	 * @param msg the message to add to exception.
	 */
	public OrdersManagerException(String msg) {
		super(msg);
	}

	/**
	 * @param msg the message to add to exception.
	 * @param e the exception.
	 */
	public OrdersManagerException(String msg, Exception e) {
		super(msg, e);
	}

}
