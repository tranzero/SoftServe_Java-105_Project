package com.ita.edu.softserve.validation;

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

import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.manager.UserManager;
import com.ita.edu.softserve.manager.impl.UserManagerImpl;

/**
 * Class under test {}
 * 
 * @author iryna
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class TestUserEditValidation {

	private Users user;

	private Errors errors;
	
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
	 * Test method that validate password
	 */
//	@Test
	public void testHasPasswordBlack() {
		user.setPassword(null);
		errors = new BeanPropertyBindingResult(user, "user");
		userEditValidator.validate(user, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("password"));
	}

	/**
	 * Test method that validate role------NO
	 */
//	@Test
	public void testHasRoleBlack() { 
		user.setRole(null);
		errors = new BeanPropertyBindingResult(user, "user");
		userEditValidator.validate(user, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("role"));

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
