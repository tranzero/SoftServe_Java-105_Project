package com.ita.edu.softserve.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.entity.Transports;

@Component("transportsValidator")
public class TransportsValidator implements Validator {

	private static final String TRANSPORT_CODE = "transportCode";
	private static final String START_TIME = "startTime";
	private static final String SEATCLASS1 = "seatclass1";
	private static final String SEATCLASS2 = "seatclass2";
	private static final String SEATCLASS3 = "seatclass3";
	private static final String GENERAL_PRICE = "genPrice";
	
	private static final String TRANSPORT_CODE_EMPTY = "transportCode.empty";
	private static final String START_TIME_EMPTY = "startTime.empty";
	private static final String SEATCLASS1_EMPTY = "seatclass1.empty";
	private static final String SEATCLASS2_EMPTY = "seatclass2.empty";
	private static final String SEATCLASS3_EMPTY = "seatclass3.empty";
	private static final String GEN_PRICE_EMPTY = "genPrice.empty";
	private static final String SEATCLASS2_REQUIRED = "seatclass2.required";
	private static final String SEATCLASS3_REQUIRED = "seatclass3.required";
	private static final String GEN_PRICE_REQUIRED = "genPrice.required";
	private static final String SEATCLASS1_REQUIRED = "seatclass1.required";

	private static final String EMPTY_TRANSPORT_CODE_MESSAGE = "Empty transport Code";
	private static final String EMPTY_START_TIME_MESSAGE = "Empty start Time";
	private static final String EMPTY_SEAT_CLASS_1_MESSAGE = "Empty seat class 1";
	private static final String EMPTY_SEAT_CLASS_2_MESSAGE = "Empty seat class 2";
	private static final String EMPTY_SEAT_CLASS_3_MESSAGE = "Empty seat class 3";
	private static final String EMPTY_GENERAL_PRICE_MESSAGE = "Empty general Price";
	
	/**
	 * This Validator validates *just* Transports instance
	 */
	public boolean supports(Class<?> clazz) {
		return Transports.class.equals(clazz);
	}

	/**
	 * This method provide validation for Transports objects.
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 *      org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors error) {
		Transports transport = (Transports) obj;

		ValidationUtils.rejectIfEmpty(error, TRANSPORT_CODE,
				TRANSPORT_CODE_EMPTY, EMPTY_TRANSPORT_CODE_MESSAGE);
		ValidationUtils.rejectIfEmpty(error, START_TIME, START_TIME_EMPTY,
				EMPTY_START_TIME_MESSAGE);

		ValidationUtils.rejectIfEmpty(error, SEATCLASS1, SEATCLASS1_EMPTY,
				EMPTY_SEAT_CLASS_1_MESSAGE);
		ValidationUtils.rejectIfEmpty(error, SEATCLASS2, SEATCLASS2_EMPTY,
				EMPTY_SEAT_CLASS_2_MESSAGE);
		ValidationUtils.rejectIfEmpty(error, SEATCLASS3, SEATCLASS3_EMPTY,
				EMPTY_SEAT_CLASS_3_MESSAGE);
		ValidationUtils.rejectIfEmpty(error, GENERAL_PRICE, GEN_PRICE_EMPTY,
				EMPTY_GENERAL_PRICE_MESSAGE);

		if (transport.getSeatclass1() == 0) {
			error.rejectValue(SEATCLASS1, SEATCLASS1_REQUIRED);
		}

		if (transport.getSeatclass2() == 0) {
			error.rejectValue(SEATCLASS2, SEATCLASS2_REQUIRED);
		}

		if (transport.getSeatclass3() == 0) {
			error.rejectValue(SEATCLASS3, SEATCLASS3_REQUIRED);
		}

		if (transport.getGenPrice() == 0.0) {
			error.rejectValue(GENERAL_PRICE, GEN_PRICE_REQUIRED);
		}

		try {
			transport.getSeatclass1();
		} catch (NumberFormatException e) {
			error.rejectValue(SEATCLASS1, SEATCLASS1_REQUIRED);
		}
	}
}
