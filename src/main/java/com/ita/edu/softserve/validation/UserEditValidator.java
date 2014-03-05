package com.ita.edu.softserve.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.entity.Users;

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

	/**
	 * This Validator validates Users instance
	 */
	public boolean supports(Class<?> clazz) {
		return Users.class.equals(clazz);
	}

	/**
	 * Method that provide validation for Users objects.
	 */
	public void validate(Object obj, Errors error) {
		Users user = (Users) obj;

		validateFirstName(user.getFirstName(), error);
		validateLastName(user.getLastName(), error);
		validateEmail(user.getEmail(), error);
		validatePassword(user.getPassword(), error);

	}

	/**
	 * Method that verify if FirstName is correct inputted.
	 * 
	 * @param firstName
	 * @param error
	 */
	private void validateFirstName(String firstName, Errors error) {
		if (firstName.matches(USER_FIRSTNAME_PATERN) == false) {
			error.rejectValue(FIRSTNAME, USER_FIRSTNAME_MATCHER);
		}

	}

	/**
	 * Method that verify if LastName is correct inputted.
	 * 
	 * @param lastName
	 * @param error
	 */
	private void validateLastName(String lastName, Errors error) {
		if (lastName.matches(USER_LASTNAME_PATERN) == false) {
			error.rejectValue(LASTNAME, USER_LASTNAME_MATCHER);
		}
	}

	/**
	 * Method that verify if Email is correct inputted.
	 * 
	 * @param email
	 * @param error
	 */
	private void validateEmail(String email, Errors error) {
		if (email.matches(USER_EMAIL_PATERN) == false) {
			error.rejectValue(EMAIL, USER_EMAIL_MATCHER);
		}
	}

	/**
	 * Method that verify if the Password is correct inputted.
	 * 
	 * @param password
	 * @param error
	 */
	private void validatePassword(String password, Errors error) {
		if (password.matches(USER_PASSWORD_PATERN) == false) {
			error.rejectValue(PASSWORD, USER_PASSWORD_MATCHER);
		}
	}

}
