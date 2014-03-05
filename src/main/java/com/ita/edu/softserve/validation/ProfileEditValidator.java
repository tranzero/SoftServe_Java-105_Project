package com.ita.edu.softserve.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.entity.Users;

@Component
public class ProfileEditValidator implements Validator {
	
	Pattern pattern;
	Matcher matcher;

	private static final String STRING_PATTERN = "[a-zA-Z]+";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
    @Override
	public boolean supports(Class<?> clazz) {
		return Users.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors error) {
		Users user = (Users) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "firstName", "required.firstName", "First Name is required");
        validateFirstName(user.getFirstName(), error);
        ValidationUtils.rejectIfEmptyOrWhitespace(error, "lastName", "required.lastName", "Last Name is required");
	    validateLastName(user.getLastName(), error);
	    ValidationUtils.rejectIfEmptyOrWhitespace(error, "email", "required.email","Email is required");
	    validateEmail(user.getEmail(), error);
	    ValidationUtils.rejectIfEmptyOrWhitespace(error, "password", "required.password","Password is required");
	    validatePassword(user.getPassword(), user.getConfirmPassword(), error);
	}

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

	private void validatePassword(String password, String confirmPassword,
			Errors error) {
		if (!password.equals(confirmPassword)) {
			error.rejectValue("confirmPassword", "password.mismatch",
					"Password does not match");
		}
	}
}
