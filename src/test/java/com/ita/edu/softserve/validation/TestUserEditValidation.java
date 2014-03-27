package com.ita.edu.softserve.validation;

import com.ita.edu.softserve.entity.Users;

import static com.ita.edu.softserve.utils.ParseUtil.parseStringToTime;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.utils.ParseUtil;

/**
 * Class under test {}
 * 
 * @author iryna
 * 
 */
public class TestUserEditValidation {

	private Validator userEditValidator;

	private Users user;

	private Errors errors;

	@Before
	public void setUp() {
		userEditValidator = new UserEditValidator();
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
	 * Test method that validate password
	 */
	/*@Test
	public void testHasPasswordBlack() {
		errors = new BeanPropertyBindingResult(user, "user");
		userEditValidator.validate(user, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("password"));

	}*/
	/**
	 * Test method that validate role------NO
	 *//*
	@Test
	public void testHasRoleBlack() {
		errors = new BeanPropertyBindingResult(user, "user");
		userEditValidator.validate(user, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("role"));

	}
*/
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

	/**
	 * Test for validate role
	 */
	@Test
	public void testHasRole() {
		user.setRole(Role.ADMIN);
		errors = new BeanPropertyBindingResult(user, "user");
		userEditValidator.validate(user, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("role"));

	}
}
