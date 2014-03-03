package com.ita.edu.softserve.web;

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

	public static final String USER_FIRSTNAME_PATERN = "^[a-zA-Z]{3,35}$";
	public static final String USER_LASTNAME_PATERN = "^[a-zA-Z]{3,}$";
	// private static final String USER_EMAIL_PATERN = "^[a-zA-Z0-9]{2,35}$";
	private static final String USER_EMAIL_PATERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final String USER_PASSWORD_PATERN = "^[a-zA-Z0-9]{4,15}$";

	/*
	 * private static final String FIRSTNAME = "firstName"; private static final
	 * String LASTNAME = "lastName"; private static final String EMAIL =
	 * "email"; private static final String PASSWORD = "password";
	 */
	private static final String USER_FIRSTNAME_MATCHER = "user.firstName.matcher";
	private static final String USER_LASTNAME_MATCHER = "user.lastName.matcher";
	private static final String USER_EMAIL_MATCHER = "user.email.matcher";
	private static final String USER_PASSWORD_MATCHER = "user.password.matcher";

	public boolean supports(Class<?> clazz) {
		return Users.class.equals(clazz);
	}

	/**
	 * validate
	 */
	public void validate(Object obj, Errors error) {
		Users user = (Users) obj;

		validateFirstName(user.getFirstName(), error);
		validateLastName(user.getLastName(), error);
		validateEmail(user.getEmail(), error);
		validatePassword(user.getPassword(), error);

		/*
		 * ValidationUtils.rejectIfEmptyOrWhitespace(error, "firstName", null,
		 * "Empty firstName"); ValidationUtils.rejectIfEmptyOrWhitespace(error,
		 * "lastName", null, "Empty lastName");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(error, "email", null,
		 * "Empty email"); ValidationUtils.rejectIfEmptyOrWhitespace(error,
		 * "password", null, "Empty password");
		 */

	}

	/**
	 * Validate FirstName
	 * 
	 * @param firstName
	 * @param error
	 */
	private void validateFirstName(String firstName, Errors error) {
		if (firstName.matches(USER_FIRSTNAME_PATERN) == false) {
			error.rejectValue("firstName", "transportCode.matcher");
		}

	}

	/**
	 * Validate LastName
	 * 
	 * @param lastName
	 * @param error
	 */
	private void validateLastName(String lastName, Errors error) {
		//System.out.println(lastName);
		if (lastName.matches(USER_LASTNAME_PATERN) == false) {
			// System.out.println(lastName+ "2");
			error.rejectValue("lastName", "transportCode.matcher");
			// System.out.println(lastName+ "3");

		}

	}

	private void validateEmail(String email, Errors error) {
		if (email.matches(USER_EMAIL_PATERN) == false) {
			error.rejectValue("email", "transportCode.matcher");
		}
	}

	private void validatePassword(String password, Errors error) {
		if (password.matches(USER_PASSWORD_PATERN) == false) {
			error.rejectValue("password", "transportCode.matcher");
		}
	}

	// -------------------------------------------------------

}
