package com.ita.edu.softserve.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.manager.UserManager;
import com.ita.edu.softserve.manager.impl.UserManagerImpl;

/**
 * Test class for UserEditValidator
 * 
 * @author iryna
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class TestUserEditValidation {

	private Users user;

	private Errors errors;

	private static final String userFirstName = "Anna";
	private static final String userLastName = "Krachkovska";
	private static final String userEmail = "user45@mail.com";
	private static final String userPassword = "12345";

	private static final String USER_OBJECT_NAME = "user";
	@Mock
	private UserManagerImpl usersmanage;

	@InjectMocks
	private Validator userEditValidator = new UserEditValidator();;

	@Before
	public void setUp() {
		user = new Users();
	}

	/* Black box tests */
	/**
	 * Test method that validate firstName
	 */
	@Test
	public void testHasFirstNameBlack() {
		errors = new BeanPropertyBindingResult(user, "user");
		userEditValidator.validate(user, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("firstName"));
	}

	/**
	 * Test method that validate lastName
	 */
	@Test
	public void testHasLastNameBlack() {
		errors = new BeanPropertyBindingResult(user, "user");
		userEditValidator.validate(user, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("lastName"));
	}

	/**
	 * Test method that validate email
	 */
	@Test
	public void testHasEmailBlack() {
		errors = new BeanPropertyBindingResult(user, "user");
		userEditValidator.validate(user, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("email"));
	}

	/**
	 * Test verify if user with valid arguments not has erros
	 */
	@Test
	public void testUserEditValidateWithValidArguments() {
		user.setFirstName(userFirstName);
		user.setLastName(userLastName);
		user.setEmail(userEmail);
		user.setPassword(userPassword);

		errors = new BeanPropertyBindingResult(user, USER_OBJECT_NAME);
		userEditValidator.validate(user, errors);

		assertFalse(errors.hasErrors());
	}

	/**
	 * Test verify if user with not valid arguments has erros
	 */
	@Test
	public void testUserEditValidateWithNotValidArguments() {
		user.setFirstName("Roxana7");
		user.setLastName(userLastName);
		user.setEmail(userEmail);
		user.setPassword("18");

		errors = new BeanPropertyBindingResult(user, USER_OBJECT_NAME);
		userEditValidator.validate(user, errors);

		assertTrue(errors.hasErrors());
	}

	/* White box tests */
	/**
	 * Test for validate firstName
	 */
	@Test
	public void testHasFirstName() {
		user.setFirstName("Roxana");
		errors = new BeanPropertyBindingResult(user, "user");
		userEditValidator.validate(user, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("firstName"));
	}

	/**
	 * Test for validate lastName
	 */
	@Test
	public void testHasLastName() {
		user.setLastName("Gud");
		errors = new BeanPropertyBindingResult(user, "user");
		userEditValidator.validate(user, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("lastName"));
	}

	/**
	 * Test for validate email
	 */
	@Test
	public void testHasEmail() {
		user.setEmail("user7@yahoo.com");
		errors = new BeanPropertyBindingResult(user, "user");
		userEditValidator.validate(user, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("email"));
	}

	/**
	 * Test for validate passwod
	 */
	@Test
	public void testHasPassword() {
		user.setPassword("41hjkR");
		errors = new BeanPropertyBindingResult(user, "user");
		userEditValidator.validate(user, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("password"));
	}

}
