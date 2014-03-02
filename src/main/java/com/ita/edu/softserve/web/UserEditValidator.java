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

	public static final String USER_FIRSTNAME_PATERN = "^[a-zA-Z]{5,35}$";
	public static final String USER_LASTNAME_PATERN = "^[a-zA-Z]{5,35}$";
	private static final String USER_EMAIL_PATERN = "^[a-zA-Z0-9]$";
	private static final String USER_PASSWORD_PATERN = "^[a-zA-Z0-9]{5,15}$";
	
	private static final String FIRSTNAME = "firstName";
	private static final String LASTNAME = "lastName";
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	
	private static final String USER_FIRSTNAME_MATCHER = "firstName.matcher";
	private static final String USER_LASTNAME_MATCHER = "lastName.matcher";
	private static final String USER_EMAIL_MATCHER = "email.matcher";
	private static final String USER_PASSWORD_MATCHER = "password.matcher";

	public boolean supports(Class<?> clazz) {
		return Users.class.equals(clazz);
	}

	public void validate(Object obj, Errors error) {
		Users user = (Users) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "firstName", null,
				"Empty firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "lastName", null,
				"Empty lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "email", null,
				"Empty email");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "password", null,
				"Empty password");
		
		

	}

	private void validateFirstName(String firstName, Errors error) {
		if (firstName.matches(USER_FIRSTNAME_PATERN) == false) {
			error.rejectValue(firstName, USER_FIRSTNAME_MATCHER);
		}

	}

	/**
	 * @param lastName
	 * @param error
	 */
	private void validateLastName(String lastName, Errors error) {
		if (lastName.matches(USER_LASTNAME_PATERN) == false) {
			error.rejectValue(lastName, USER_LASTNAME_MATCHER);
		}
	}

	private void validateEmail(String email, Errors error) {
		if (email.matches(USER_EMAIL_PATERN) == false) {
			error.rejectValue(email, USER_EMAIL_MATCHER);
		}
	}

	private void validatePassword(String password, Errors error) {
		if (password.matches(USER_PASSWORD_PATERN) == false) {
			error.rejectValue(password, USER_PASSWORD_MATCHER);
		}
	}
	
	//-------------------------------------------------------
	
	
}
