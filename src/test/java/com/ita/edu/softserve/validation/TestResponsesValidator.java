package com.ita.edu.softserve.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.entity.Responses;

/**
 * 
 * @author yuraloga
 *
 */
public class TestResponsesValidator { 

	private Validator responsesValidator;

	private Responses response;

	private Errors errors;
	
	private static final String COMMENT_FIELD = "response";

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
		String xssComment = "<script>alert(1)</script>";
		
		response.setComment(xssComment);
		errors = new BeanPropertyBindingResult(response, COMMENT_FIELD);
		responsesValidator.validate(response, errors);

		assertTrue(errors.hasErrors());
	}

	/**
	 * If empty comment text test
	 */
	@Test
	public void responseIfEmptyCommentTest() {
		String emptyComment = "";
		
		response.setComment(emptyComment);
		errors = new BeanPropertyBindingResult(response, COMMENT_FIELD);
		responsesValidator.validate(response, errors);

		assertTrue(errors.hasErrors());
	}
	
	/**
	 * If too large comment text test
	 */
	@Test
	public void responseIfTooLargeCommentTest() {
		String tooLongComment = "test";
		
		for (int i = 0; i < 250; i++) {
			tooLongComment += "1";
		}
		
		response.setComment(tooLongComment);
		errors = new BeanPropertyBindingResult(response, COMMENT_FIELD);
		responsesValidator.validate(response, errors);

		assertTrue(errors.hasErrors());
	}
	
	/**
	 * If normal comment test
	 */
	@Test
	public void responseIfNormalCommentTest() {
		String comment = "This was a good trip.";
		
		response.setComment(comment);
		errors = new BeanPropertyBindingResult(response, COMMENT_FIELD);
		responsesValidator.validate(response, errors);

		assertFalse(errors.hasErrors());
	}

	/**
	 * Supports classes test
	 */
	@Test
	public void supportsTest() {
		assertTrue(responsesValidator.supports(Responses.class));
	}
	
	/**
	 * Supports classes test
	 * If not supported class
	 */
	@Test
	public void supportsIfNotSupportedClassTest() {
		assertFalse(responsesValidator.supports(Date.class));
	}
	
	/**
	 * Supports classes test
	 * If null
	 */
	@Test
	public void supportsIfNullTest() {
		assertFalse(responsesValidator.supports(null));
	}
	
}
