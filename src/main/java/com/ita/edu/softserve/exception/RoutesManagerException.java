package com.ita.edu.softserve.exception;

/**
 * @author Lyubomyr
 * 
 */
public class RoutesManagerException extends DBException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6672761590257032954L;

	public RoutesManagerException() {
	}

	public RoutesManagerException(String msg) {
		super(msg);
	}

	public RoutesManagerException(String msg, Exception e) {
		super(msg, e);
	}
}