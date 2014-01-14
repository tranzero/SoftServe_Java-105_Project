package com.ita.edu.SoftServeUniversity.entity;

/**
 * @author admin
 *
 */

public class Permission {
	/**
	 * field name - {ADMIN, MANAGER, REGUSER}
	 */
	private String name;
	
	/**
	 * Default constructor
	 */
	public Permission() {
	}

	/**
	 * @param name
	 */
	public Permission(String name) {
		this.name = name;
	}

	 /**
     * @return the name
     */
	public String getName() {
		return name;
	}

	 /**
     * @param name
     */
	public void setName(final String name) {
		this.name = name;
	}

}
