package com.ita.edu.softserve.exception;

public class DBException extends EntityExceptions{
	private static final long serialVersionUID = -2943018141412512379L;

    public DBException() {
    }

    public DBException(String msg) {
	super(msg);
    }

    public DBException(String msg, Exception e) {
	super(msg, e);
    }
}
