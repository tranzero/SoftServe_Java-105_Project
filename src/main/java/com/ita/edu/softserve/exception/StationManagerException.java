/**
 * 
 */
package com.ita.edu.softserve.exception;

/**
 * @author admin
 * 
 */
public class StationManagerException extends DBException {

	/**
     * 
     */
	private static final long serialVersionUID = -7682510556706339437L;

	public StationManagerException() {

	}

	public StationManagerException(String msg) {
		super(msg);
	}

	public StationManagerException(String msg, Exception e) {
		super(msg, e);
	}
}
