package com.ita.edu.softserve.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * @author admin
 * 
 *         The persistent class for the USERS database table.
 */
@Entity
@Table(name = "USERS")
@NamedQueries({
		@NamedQuery(name = Users.FIND_BY_NAME, query = Users.FIND_BY_NAME_QUERY),
		@NamedQuery(name = Users.GET_ALL_USERS, query = Users.GET_ALL_USERS_QUERY) })
public class Users {

	public static final String FIND_BY_NAME = "Users.findByName";
	// public static final String FIND_BY_NAME_QUERY =
	// "SELECT u FROM Users u WHERE u.eMail = ?1";
	public static final String FIND_BY_NAME_QUERY = "SELECT u FROM Users u WHERE u.username = ?1";
	public static final String GET_ALL_USERS = "Users.getAllUsers";
	public static final String GET_ALL_USERS_QUERY = "SELECT user FROM Users user";
	@Id
	@Column(name = "USERID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	@Column(name = "FIRSTNAME", nullable = true, length = 100)
	private String firstName;

	@Column(name = "LASTNAME", nullable = true, length = 100)
	private String lastName;

	@Column(name = "EMAIL", nullable = false, length = 100)
	private String eMail;

	@Column(name = "USERNAME", nullable = false, unique = true, length = 100)
	private String username;

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

	}

	/**
	 * Constructor with parametrs:
	 * 
	 * @param username
	 * @param email
	 * @param password
	 */
	public Users(String username, String email, String password) {
		this.username = username;
		this.eMail = email;
		this.passwd = password;

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
	public Users(String username, String firstname, String lastname,
			String email, String password) {
		this.username = username;
		this.firstName = firstname;
		this.lastName = lastname;
		this.eMail = email;
		this.passwd = password;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (passwd == null) {
			if (other.passwd != null)
				return false;
		} else if (!passwd.equals(other.passwd))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((passwd == null) ? 0 : passwd.hashCode());
		return result;
	}

}
