package com.ita.edu.softserve.exception;

public class TransprtsManagerException extends RuntimeException {

	private static final long serialVersionUID = -8260139134189365220L;

	public TransprtsManagerException() {
	}

	public TransprtsManagerException(String msg) {
		super(msg);
	}

	public TransprtsManagerException(String msg, Exception e) {
		super(msg, e);
	}
}
