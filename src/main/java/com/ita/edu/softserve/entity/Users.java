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
import javax.persistence.Transient;

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
		@NamedQuery(name = Users.GET_COUNT_ALL_USERS, query = Users.GET_COUNT_ALL_USERS_QUERY),

		@NamedQuery(name = Users.GET_COUNT_USERS_WITH_CRITERIA, query = Users.GET_COUNT_USERS_WITH_CRITERIA_QUERY) })
public class Users extends BaseEntity {

	public static final String SEARCH_STRING_NAME = "searchstring";
	public static final String ROLE_ARRAY_NAME = "rolearray";
	public static final String MIN_DATE_NAME = "minDate";
	public static final String MAX_DATE_NAME = "maxDate";

	// Queries
	public static final String FIND_BY_NAME = "Users.findByName";
	public static final String FIND_BY_NAME_QUERY = "SELECT u FROM Users u WHERE u.lastName = ?1";

	public static final String FIND_BY_USERNAME = "Users.findByUsername";
	public static final String FIND_BY_USERNAME_QUERY = "SELECT u FROM Users u WHERE u.userName =?1";

	public static final String GET_ALL_USERS = "Users.getAllUsers";
	public static final String GET_ALL_USERS_QUERY = "SELECT user FROM Users user";

	public static final String GET_COUNT_ALL_USERS = "Users.getCountAllUsers";
	public static final String GET_COUNT_ALL_USERS_QUERY = "SELECT COUNT(user) FROM Users user";

	public static final String GET_COUNT_USERS_WITH_CRITERIA = "Users.getCountAllUsersWithCriteria";
	public static final String GET_COUNT_USERS_WITH_CRITERIA_QUERY = "SELECT COUNT(u.userId) FROM Users u WHERE (u.userName LIKE :"
			+ SEARCH_STRING_NAME
			+ " OR u.firstName LIKE :"
			+ SEARCH_STRING_NAME
			+ " OR u.lastName LIKE :"
			+ SEARCH_STRING_NAME
			+ " OR u.email LIKE :"
			+ SEARCH_STRING_NAME
			+ ") AND u.role IN (:"
			+ ROLE_ARRAY_NAME
			+ ") AND u.regDate BETWEEN :"
			+ MIN_DATE_NAME
			+ " AND :" + MAX_DATE_NAME;

	public static final String GET_USERS_WITH_CRITERIA_QUERY = "SELECT u FROM Users u WHERE (u.userName LIKE :"
			+ SEARCH_STRING_NAME
			+ " OR u.firstName LIKE :"
			+ SEARCH_STRING_NAME
			+ " OR u.lastName LIKE :"
			+ SEARCH_STRING_NAME
			+ " OR u.email LIKE :"
			+ SEARCH_STRING_NAME
			+ ") AND u.role IN (:"
			+ ROLE_ARRAY_NAME
			+ ") AND u.regDate BETWEEN :"
			+ MIN_DATE_NAME
			+ " AND :" + MAX_DATE_NAME + " ORDER BY ";

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

	@Transient
	public String confirmPassword;

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
		this.setEmail(email);
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
		this.setEmail(email);
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
	 * The userId to set
	 * 
	 * @param userId
	 * 
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
	 * The userName to set
	 * 
	 * @param userName
	 * 
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
	 * The firstName to set
	 * 
	 * @param firstName
	 * 
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
	 * The lastName to set
	 * 
	 * @param lastName
	 * 
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * The email to set
	 * 
	 * @param email
	 * 
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the Password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * The Password to set
	 * 
	 * @param Password
	 * 
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the Registration Date
	 */
	public Date getRegDate() {
		return regDate;
	}

	/**
	 * The Registration Date to set
	 * 
	 * @param regDate
	 * 
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
	 * The role to set
	 * 
	 * @param role
	 * 
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * 
	 * @return confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * ConfirmPassword to set
	 * 
	 * @param confirmPassword
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
