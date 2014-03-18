package com.ita.edu.softserve.exception;

/**
 * Thrown by the TransprtsManager when a problem occurs.
 *
 * @author Roman
 */
public class ResponsesManagerException extends RuntimeException {


	private static final long serialVersionUID = -6455714021556680199L;

	/**
	 * Constructs a new <code>ResponsesManagerException</code> exception
	 * with <code>null</code> as its detail message.
	 */
	public ResponsesManagerException() {
		super();
	}

	/**
	 * Constructs a new <code>ResponsesManagerException</code> exception
	 * with the specified detail message.
	 *
	 * @param message the detail message.
	 */
	public ResponsesManagerException(String message) {
		super(message);
	}

	/**
	 * Constructs a new <code>ResponsesManagerException</code> exception
	 * with the specified detail message and cause.
	 *
	 * @param message the detail message.
	 * @param cause the cause.
	 */
	public ResponsesManagerException(String message, Exception cause) {
		super(message, cause);
	}
}
