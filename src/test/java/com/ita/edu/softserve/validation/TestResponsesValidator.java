package com.ita.edu.softserve.validation;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.entity.Responses;

public class TestResponsesValidator {

	private Validator responsesValidator;

	private Responses response;

	private Errors errors;

	@Before
	public void setUp() {
		responsesValidator = new ResponsesValidator();
		response = new Responses();
	}

	/**
	 * If illegal characters test
	 */
	@Test
	public void responseIllegalCharactersTest() {
		String comment = "<script>alert(1)</script>";
		
		response.setComment(comment);
		errors = new BeanPropertyBindingResult(response, "response");
		responsesValidator.validate(response, errors);

		assertTrue(errors.hasErrors());
	}

	/**
	 * If empty comment text test
	 */
	@Test
	public void responseIfEmptyTest() {
		String comment = "";
		
		response.setComment(comment);
		errors = new BeanPropertyBindingResult(response, "response");
		responsesValidator.validate(response, errors);

		assertTrue(errors.hasErrors());
	}
	
	/**
	 * If too large comment text test
	 */
	@Test
	public void responseIfTooLargeTest() {
		String comment = "test";
		
		for (int i = 0; i < 250; i++) {
			comment += "1";
		}
		
		response.setComment(comment);
		errors = new BeanPropertyBindingResult(response, "response");
		responsesValidator.validate(response, errors);

		assertTrue(errors.hasErrors());
	}
	
	/**
	 * If normal comment test
	 */
	@Test
	public void responseIfNormalTest() {
		String comment = "This was a good trip.";
		
		response.setComment(comment);
		errors = new BeanPropertyBindingResult(response, "response");
		responsesValidator.validate(response, errors);

		assertTrue(!errors.hasErrors());
	}

	
}
