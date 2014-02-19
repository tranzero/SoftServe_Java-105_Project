/**
 * 
 */
package com.ita.edu.softserve.exception;

/**
 * @author MatyashPetro
 *
 */
public class StationOnLineManagerException extends RuntimeException {

private static final long serialVersionUID = -993591282264002439L;
	
	public StationOnLineManagerException() {

	}

	public StationOnLineManagerException(String msg) {
		super(msg);
	}

	public StationOnLineManagerException(String msg, RuntimeException e) {
		super(msg, e);
	}
	
}
