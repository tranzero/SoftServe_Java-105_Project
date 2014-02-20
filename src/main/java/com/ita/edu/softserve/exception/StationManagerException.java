/**
 * 
 */
package com.ita.edu.softserve.exception;

/**
 * @author admin
 * 
 */
public class StationManagerException extends RuntimeException {

	/**
     * 
     */
	private static final long serialVersionUID = -7682510556706339437L;

	public StationManagerException() {

	}

	public StationManagerException(String msg) {
		super(msg);
	}

	public StationManagerException(String msg, RuntimeException e) {
		super(msg, e);
	}
}
