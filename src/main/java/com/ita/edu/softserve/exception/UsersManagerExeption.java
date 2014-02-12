package com.ita.edu.softserve.exception;

public class UsersManagerExeption extends DBException {

	private static final long serialVersionUID = -993591282264002666L;

	public UsersManagerExeption() {

	}

	public UsersManagerExeption(String msg) {
		super(msg);
	}

	public UsersManagerExeption(String msg, Exception e) {
		super(msg, e);
	}

}
