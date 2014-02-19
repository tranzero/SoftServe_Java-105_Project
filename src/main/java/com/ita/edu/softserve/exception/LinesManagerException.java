/**
 * 
 */
package com.ita.edu.softserve.exception;

/**
 * @author MatyashPetro
 * 
 */
public class LinesManagerException extends RuntimeException {

	private static final long serialVersionUID = -993591282264002555L;
	
	public LinesManagerException() {

	}

	public LinesManagerException(String msg) {
		super(msg);
	}

	public LinesManagerException(String msg, RuntimeException e) {
		super(msg, e);
	}
}