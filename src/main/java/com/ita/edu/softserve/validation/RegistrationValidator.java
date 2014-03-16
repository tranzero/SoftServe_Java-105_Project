package com.ita.edu.softserve.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.entity.Users;

/**
 * 
 * Validator for Registration of users
 * 
 */
@Component
public class RegistrationValidator implements Validator {

	Pattern pattern;
	Matcher matcher;

	private static final String USERNAME_PATTERN = "[a-zA-Z]{5,15}$";
	private static final String STRING_PATTERN = "[a-zA-Z]+";
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * This Validator validates Users instance
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Users.class.equals(clazz);
	}

	/**
	 * Method that provide validation for Users objects.
	 */
	@Override
	public void validate(Object obj, Errors error) {
		Users user = (Users) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "userName",
				"required.userName", "User Name is required.");
		validateUserName(user.getUserName(), error);
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "firstName",
				"required.firstName", "First Name is required");
		validateFirstName(user.getFirstName(), error);
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "lastName",
				"required.lastName", "Last Name is required");
		validateLastName(user.getLastName(), error);
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "email",
				"required.email", "Email is required");
		validateEmail(user.getEmail(), error);
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "password",
				"required.password", "Password is required");
		validatePassword(user.getPassword(), user.getConfirmPassword(), error);
	}

	/**
	 * Method that verify if userName is correct introduced.
	 * 
	 * @param userName
	 * @param error
	 */
	private void validateUserName(String userName, Errors error) {
		if (!(userName != null && userName.isEmpty())) {
			pattern = Pattern.compile(USERNAME_PATTERN);
			matcher = pattern.matcher(userName);
			if (!matcher.matches()) {
				error.rejectValue("userName", "userName.containNonChar",
						"At least 5 characters required in User Name");
			}
		}
	}

	/**
	 * Method that verify if firstName is correct introduced.
	 * 
	 * @param firstName
	 * @param error
	 */
	private void validateFirstName(String firstName, Errors error) {
		if (!(firstName != null && firstName.isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher(firstName);
			if (!matcher.matches()) {
				error.rejectValue("firstName", "firstName.containNonChar",
						"Enter a correct First Name");
			}
		}
	}

	/**
	 * Method that verify if lastName is correct introduced.
	 * 
	 * @param lastName
	 * @param error
	 */
	private void validateLastName(String lastName, Errors error) {
		if (!(lastName != null && lastName.isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher(lastName);
			if (!matcher.matches()) {
				error.rejectValue("lastName", "lastName.containNonChar",
						"Enter a correct Last Name");
			}
		}
	}

	/**
	 * Method that verify if E-mail is correct introduced.
	 * 
	 * @param email
	 * @param error
	 */
	private void validateEmail(String email, Errors error) {
		if (!(email != null && email.isEmpty())) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(email);
			if (!matcher.matches()) {
				error.rejectValue("email", "email.incorrect",
						"Enter a correct email");
			}
		}
	}

	/**
	 * Method that verify if the password is correct introduced.
	 * 
	 * @param password
	 * @param confirmPassword
	 * @param error
	 */
	private void validatePassword(String password, String confirmPassword,
			Errors error) {
		if (!password.equals(confirmPassword)) {
			error.rejectValue("confirmPassword", "password.mismatch",
					"Password does not match");
		}
	}
}
