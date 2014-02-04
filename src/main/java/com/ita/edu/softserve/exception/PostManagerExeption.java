package com.ita.edu.softserve.exception;

public class PostManagerExeption extends DBException {

	private static final long serialVersionUID = -993591282264002666L;

	public PostManagerExeption() {

	}

	public PostManagerExeption(String msg) {
		super(msg);
	}

	public PostManagerExeption(String msg, Exception e) {
		super(msg, e);
	}
}
