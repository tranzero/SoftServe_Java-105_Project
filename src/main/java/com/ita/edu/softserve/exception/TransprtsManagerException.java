package com.ita.edu.softserve.exception;

/**
 * The exception class for TransprtsManager.
 * @author Roman
 *
 */
public class TransprtsManagerException extends RuntimeException {

	private static final long serialVersionUID = -8260139134189365220L;

	/**
	 * The default constructor.
	 */
	public TransprtsManagerException() {
	}

	/**
	 * @param msg the message to add to exception.
	 */
	public TransprtsManagerException(String msg) {
		super(msg);
	}

	/**
	 * @param msg the message to add to exception.
	 * @param e the exception.
	 */
	public TransprtsManagerException(String msg, Exception e) {
		super(msg, e);
	}
}
