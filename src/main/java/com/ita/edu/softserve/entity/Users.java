package com.ita.edu.softserve.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author admin
 * 
 *         The persistent class for the USERS database table.
 */
@Entity
@Table(name = "users")
@NamedQueries({
		@NamedQuery(name = Users.FIND_BY_NAME, query = Users.FIND_BY_NAME_QUERY),
		@NamedQuery(name = Users.GET_ALL_USERS, query = Users.GET_ALL_USERS_QUERY) })
public class Users extends BaseEntity {

	public static final String FIND_BY_NAME = "Users.findByName";
	// public static final String FIND_BY_NAME_QUERY =
	// "SELECT u FROM Users u WHERE u.eMail = ?1";
	public static final String FIND_BY_NAME_QUERY = "SELECT u FROM Users u WHERE u.lastName = ?1";
	public static final String GET_ALL_USERS = "Users.getAllUsers";
	public static final String GET_ALL_USERS_QUERY = "SELECT user FROM Users user";
	@Id
	@Column(name = "USERID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	@Column(name = "USERNAME", nullable = false, length = 100)
	private String userName;

	@Column(name = "FIRSTNAME", nullable = true, length = 100)
	private String firstName;

	@Column(name = "LASTNAME", nullable = true, length = 100)
	private String lastName;

	@Column(name = "EMAIL", nullable = false, length = 100)
	private String eMail;

	@Column(name = "PASSWD", nullable = false, length = 100)
	private String passwd;

	@Column(name = "REGDATE", nullable = true)
	private Date regDate;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "enum('REGUSER','MANAGER', 'ADMIN')")
	private Role role;

	/**
	 * Default Constructor
	 */
	public Users() {
		this.setRegDate();
	}

	/**
	 * Constructor with parametrs:
	 * 
	 * @param username
	 * @param email
	 * @param password
	 */
	public Users(String userName, String eMail, String password) {
		this();
		if (trueEnterValueOfFirstPart(userName, eMail, password)){
			this.setUserName(userName);
			this.seteMail(eMail);
			this.setPasswd(password);
		}
	}

	/**
	 * Constructor with parametrs:
	 * 
	 * @param username
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param password
	 */
	public Users(String userName, String firstName, String lastName,
			String eMail, String password) {
		this(userName, eMail, password);
		if (trueEnterValueOfSecondPart(firstName, lastName)){
			this.setFirstName(firstName);
			this.setLastName(lastName);
		} else {
			System.exit(1);
		}
		
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}


	/**
	 * @param userId 
	 * 			the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName 
	 * 				the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the eMail
	 */
	public String geteMail() {
		return eMail;
	}

	/**
	 * @param eMail
	 *            the eMail to set
	 */
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd
	 *            the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * @return the regDate
	 */
	public Date getRegDate() {
		return regDate;
	}

	/**
	 * @param regDate
	 *            the regDate to set
	 */
	private void setRegDate() {
		this.regDate = new Date();
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	public boolean trueEnterValueOfFirstPart(String username,
											String eMail, String password){
		if (username != "" && username != null && eMail != "" 
				&& eMail != null && password != "" 
				&& password != null) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean trueEnterValueOfSecondPart( String firstName, 
			String lastName){
		if (firstName != "" && firstName != null && lastName != "" 
				&& lastName != null) {
			return true;
		} else {
			return false;
		}
		
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}


}
