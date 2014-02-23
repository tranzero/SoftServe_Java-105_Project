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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.util.Assert;

/**
 * @author admin
 * 
 *         The persistent class for the USERS database table.
 */
@Entity
@Table(name = "users")
@NamedQueries({
		@NamedQuery(name = Users.FIND_BY_NAME, query = Users.FIND_BY_NAME_QUERY),
		@NamedQuery(name = Users.FIND_BY_USERNAME, query = Users.FIND_BY_USERNAME_QUERY),
		
		@NamedQuery(name = Users.GET_ALL_USERS, query = Users.GET_ALL_USERS_QUERY),
		@NamedQuery(name = Users.GET_COUNT_ALL_USERS, query = Users.GET_COUNT_ALL_USERS_QUERY)

})
public class Users extends BaseEntity {

	public static final String FIND_BY_NAME = "Users.findByName";
	public static final String FIND_BY_NAME_QUERY = "SELECT u FROM Users u WHERE u.lastName = ?1";

	public static final String FIND_BY_USERNAME = "Users.findByUsername";
	public static final String FIND_BY_USERNAME_QUERY = "SELECT u FROM Users u WHERE u.userName =?1";

	public static final String GET_ALL_USERS = "Users.getAllUsers";
	public static final String GET_ALL_USERS_QUERY = "SELECT user FROM Users user";

	public static final String GET_COUNT_ALL_USERS = "Users.getCountAllUsers";
	public static final String GET_COUNT_ALL_USERS_QUERY = "SELECT COUNT(user) FROM Users user";

	@Id
	@Column(name = "USERID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;

	@Column(name = "USERNAME", nullable = false, updatable = false, length = 100)
	private String userName;

	@Column(name = "FIRSTNAME", nullable = true, updatable = true, length = 100)
	private String firstName;

	@Column(name = "LASTNAME", nullable = true, updatable = true, length = 100)
	private String lastName;

	@Column(name = "EMAIL", nullable = false, updatable = true, length = 100)
	private String email;

	@Column(name = "PASSWD", nullable = false, updatable = true, length = 100)
	private String password;

	@Column(name = "REGDATE", nullable = true)
	private Date regDate;

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

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
	public Users(String userName, String email, String password) {
		this();

		this.setUserName(userName);
		this.seteMail(email);
		this.setPassword(password);
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
			String email, String password) {
		this(userName, email, password);
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}

	/**
	 * Constructor with parametrs:
	 * 
	 * @param username
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param password
	 * @param role
	 */
	public Users(String userName, String firstName, String lastName,
			String email, String password, Role role) {

		this.setUserName(userName);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.seteMail(email);
		this.setPassword(password);
		this.setRole(role);
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
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
	 *            the userName to set
	 */
	private void setUserName(String userName) {
		Assert.hasText(userName, "Username must not be empty!");
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
		return email;
	}

	/**
	 * @param email
	 *            the eMail to set
	 */
	public void seteMail(String email) {
		Assert.hasText(email, "Email adress must not be empty!");
		this.email = email;
	}

	/**
	 * @return the passwd
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the passwd to set
	 */
	public void setPassword(String password) {
		// Assert.hasText(passwd, "Password must not be empty!");
		this.password = password;
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

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(userName).hashCode();
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

		return new EqualsBuilder().append(userName, other.userName)
				.append(userId, other.userId).isEquals();
	}
}
