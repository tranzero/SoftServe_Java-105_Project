package com.ita.edu.softserve.exception;

/**
 * Thrown by the TransprtsManager when a problem occurs.
 *
 * @author Roman
 */
public class TransprtsManagerException extends RuntimeException {

	private static final long serialVersionUID = -8260139134189365220L;

	/**
	 * Constructs a new <code>TransprtsManagerException</code> exception
	 * with <code>null</code> as its detail message.
	 */
	public TransprtsManagerException() {
		super();
	}

	/**
	 * Constructs a new <code>TransprtsManagerException</code> exception
	 * with the specified detail message.
	 *
	 * @param message the detail message.
	 */
	public TransprtsManagerException(String message) {
		super(message);
	}

	/**
	 * Constructs a new <code>TransprtsManagerException</code> exception
	 * with the specified detail message and cause.
	 *
	 * @param message the detail message.
	 * @param cause the cause.
	 */
	public TransprtsManagerException(String message, Exception cause) {
		super(message, cause);
	}
}
