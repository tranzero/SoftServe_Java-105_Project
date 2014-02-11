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

	/**
	 * Constructor with parametr description
	 * 
	 * @param description
	 */
	private Role(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
}
