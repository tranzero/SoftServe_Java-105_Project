package com.ita.edu.softserve.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.manager.UserManager;

/**
 * @author iryna
 * 
 *         Validator for UserEdit
 */
@Component("userEditValidator")
public class UserEditValidator implements Validator {

	public static final String USER_FIRSTNAME_PATERN = "^[a-zA-Z]{3,25}$";
	public static final String USER_LASTNAME_PATERN = "^[a-zA-Z]{3,25}$";
	private static final String USER_EMAIL_PATERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String USER_PASSWORD_PATERN = "^[a-zA-Z0-9]{4,}$";

	private static final String FIRSTNAME = "firstName";
	private static final String LASTNAME = "lastName";
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";

	private static final String USER_FIRSTNAME_MATCHER = "msg.user.firstName.matcher";
	private static final String USER_LASTNAME_MATCHER = "msg.user.lastName.matcher";
	private static final String USER_EMAIL_MATCHER = "msg.user.email.matcher";
	private static final String USER_PASSWORD_MATCHER = "msg.user.password.matcher";

	@Autowired
	private UserManager usersmanage;

	/**
	 * This Validator validates Users instance
	 */
	public boolean supports(Class<?> clazz) {
		return Users.class.equals(clazz);
	}

	/**
	 * Method that provide validation for Users objects
	 */
	public void validate(Object obj, Errors error) {
		Users user = (Users) obj;

		validateFirstName(user.getFirstName(), error);
		validateLastName(user.getLastName(), error);
		validateEmail(user.getEmail(), error);
		validateEncodePassword(error, user);
	}

	/**
	 * Method that verify encode of password
	 * 
	 * @param error
	 * @param user
	 */
	private void validateEncodePassword(Errors error, Users user) {
		try {
			if (!user.getPassword().equals(
					usersmanage.findUser(user.getUserId()).getPassword())) {

				validatePassword(user.getPassword(), error);
			}
		} catch (NullPointerException e) {
		}
	}

	/**
	 * Method that verify if FirstName is correct introduced.
	 * 
	 * @param firstName
	 * @param error
	 */
	private void validateFirstName(String firstName, Errors error) {
		if (firstName == null || firstName.isEmpty()) {
			error.rejectValue(FIRSTNAME, USER_FIRSTNAME_MATCHER);

		} else if (!firstName.matches(USER_FIRSTNAME_PATERN)) {
			error.rejectValue(FIRSTNAME, USER_FIRSTNAME_MATCHER);
		}
	}

	/**
	 * Method that verify if LastName is correct introduced.
	 * 
	 * @param lastName
	 * @param error
	 */
	private void validateLastName(String lastName, Errors error) {
		if (lastName == null || lastName.isEmpty()) {
			error.rejectValue(LASTNAME, USER_LASTNAME_MATCHER);

		} else if (!lastName.matches(USER_LASTNAME_PATERN)) {
			error.rejectValue(LASTNAME, USER_LASTNAME_MATCHER);
		}
	}

	/**
	 * Method that verify if Email is correct introduced.
	 * 
	 * @param email
	 * @param error
	 */
	private void validateEmail(String email, Errors error) {
		if (email == null || email.isEmpty()) {
			error.rejectValue(EMAIL, USER_EMAIL_MATCHER);

		} else if (!email.matches(USER_EMAIL_PATERN)) {
			error.rejectValue(EMAIL, USER_EMAIL_MATCHER);
		}
	}

	/**
	 * Method that verify if the Password is correct introduced.
	 * 
	 * @param password
	 * @param error
	 */
	private void validatePassword(String password, Errors error) {
		if (password == null || password.isEmpty()) {
			error.rejectValue(PASSWORD, USER_PASSWORD_MATCHER);

		} else if (!password.matches(USER_PASSWORD_PATERN)) {
			error.rejectValue(PASSWORD, USER_PASSWORD_MATCHER);
		}
	}

}
