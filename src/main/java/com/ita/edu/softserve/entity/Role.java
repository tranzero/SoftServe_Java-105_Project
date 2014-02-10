package com.ita.edu.softserve.entity;

/**
 * Role
 * 
 * @author iryna
 * 
 */
public enum Role {

	REGUSER("Regular User"), MANAGER("Manager"), ADMIN("Administrator");

	private String description;

	private Role(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
