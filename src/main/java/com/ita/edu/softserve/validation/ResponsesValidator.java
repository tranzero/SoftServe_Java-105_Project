package com.ita.edu.softserve.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.entity.Responses;
import com.ita.edu.softserve.manager.ResponsesManager;

@Component("responsesValidator")
public class ResponsesValidator implements Validator {

	private static final String COMMENT_PATERN = "[\\w\\s!\\?%()*+,./:;=\\_{|}-]{2,200}";
	
	private static final String COMMENT = "comment";

	private static final String COMMENT_REQUIRED = "comment.required";
	
	private static final String COMMENT_MATCHER = "comment.matcher";

	/**
	 * ResponsesManager Field 
	 */
	@Autowired
	private ResponsesManager responsesManager;

	/**
	 * Validates Responses instance
	 */
	public boolean supports(Class<?> clazz) {
		return Responses.class.equals(clazz);
	}

	/**
	 * Validate comment text
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 *      org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors error) {
		Responses responses = (Responses) obj;

		validateResponsesText(responses.getComment(), error);

	}

	/**
	 * Validate comment text, using regexp pattern
	 * 
	 * @param commentText
	 * @param error
	 *            the Errors object that stores and exposes information about
	 *            data-binding and validation errors for a specific object.
	 */
	private void validateResponsesText(String commentText, Errors error) {
		if (commentText.isEmpty()) {
			error.rejectValue(COMMENT, COMMENT_REQUIRED);
			return;
		} else if (!commentText.matches(COMMENT_PATERN)) {
			error.rejectValue(COMMENT, COMMENT_MATCHER);
		}
	}

}
