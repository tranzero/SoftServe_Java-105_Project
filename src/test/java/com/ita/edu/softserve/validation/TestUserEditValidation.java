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
import com.ita.edu.softserve.entity.Routes;

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

	@Test
	public void hasFirstNameBlack() {
		errors = new BeanPropertyBindingResult(user, "user");
		userEditValidator.validate(user, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("firstName"));

	}

	/* White box tests */
	@Test
	public void hasFirstName() {
		user.setFirstName("Roxana");
//		user=new Users("rtyu8", "roxana", "yuuuu", "dff@mail.com", "123455", Role.MANAGER);
		errors = new BeanPropertyBindingResult(user, "user");
		userEditValidator.validate(user, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("firstName"));

	}
}
