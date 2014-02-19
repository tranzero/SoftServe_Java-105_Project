package com.ita.edu.softserve.exception;

public class PostManagerException extends DBException {

	private static final long serialVersionUID = -993591282264002666L;

	public PostManagerException() {

	}

	public PostManagerException(String msg) {
		super(msg);
	}

	public PostManagerException(String msg, Exception e) {
		super(msg, e);
	}
}
