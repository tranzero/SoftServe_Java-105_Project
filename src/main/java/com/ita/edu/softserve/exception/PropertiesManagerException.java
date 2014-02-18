package com.ita.edu.softserve.exception;



public class PropertiesManagerException extends DBException{

	private static final long serialVersionUID = 6864000205907324002L;

	public PropertiesManagerException() {
    }

    public PropertiesManagerException(String msg) {
    	super(msg);
    }

    public PropertiesManagerException(String msg, Exception e) {
    	super(msg, e);
    }
	
}
