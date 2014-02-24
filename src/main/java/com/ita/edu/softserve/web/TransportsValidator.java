package com.ita.edu.softserve.web;

import com.ita.edu.softserve.utils.ParseUtil;

import java.sql.Time;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.entity.Transports;

@Component("transportsValidator")
public class TransportsValidator implements Validator {

	private static final String TRANSPORT_CODE_PATERN = "^[a-zA-Z0-9]{5,15}$";
	private static final String START_TIME_PATERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";

	private static final String TRANSPORT_CODE = "transportCode";
	private static final String START_TIME = "startTime";
	private static final String SEATCLASS1 = "seatclass1";
	private static final String SEATCLASS2 = "seatclass2";
	private static final String SEATCLASS3 = "seatclass3";
	private static final String GENERAL_PRICE = "genPrice";

	private static final String SEATCLASS_REQUIRED = "seatclass.required";
	private static final String GEN_PRICE_REQUIRED = "genPrice.required";
	private static final String TRANSPORT_CODE_MATCHER = "transportCode.matcher";
	private static final String START_TIME_MATCHER = "startTime.matcher";
	private static final String START_TIME_NULL = "startTime.null";

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

		validateTransportCode(transport.getTransportCode(), error);

		validateStartTime(transport.getStartTime(), error);

		validateSeatClasses(transport.getSeatclass1(),
				transport.getSeatclass2(), transport.getSeatclass3(), error);

		validateGeneralPrice(transport.getGenPrice(), error);

	}

	/**
	 * String must contain only letter and digits more then 5 and less then 15.
	 * 
	 * @param transportCode
	 *            The transport code to match.
	 * @param error
	 *            the Errors object that stores and exposes information about
	 *            data-binding and validation errors for a specific object.
	 */
	private void validateTransportCode(String transportCode, Errors error) {
		if (!transportCode.matches(TRANSPORT_CODE_PATERN)) {
			error.rejectValue(TRANSPORT_CODE, TRANSPORT_CODE_MATCHER);
		}
	}

	/**
	 * @param startTime
	 *            The start time to match.
	 * @param error
	 *            the Errors object that stores and exposes information about
	 *            data-binding and validation errors for a specific object.
	 */
	private void validateStartTime(Time startTime, Errors error) {
		if (startTime == null) {
			error.rejectValue(START_TIME, START_TIME_NULL);
		} else {
			String time = ParseUtil.parseTimeToString(startTime);
			if (!time.matches(START_TIME_PATERN)) {
				error.rejectValue(START_TIME, START_TIME_MATCHER);
			}
		}
	}

	/**
	 * Verifies if seat classes are not less then 0 or equals to 0. Must be
	 * entered number of seats at least one class.
	 * 
	 * @param seatClass1
	 *            the seat class 1 to check.
	 * @param seatClass2
	 *            the seat class 2 to check.
	 * @param seatClass3
	 *            the seat class 3 to check.
	 * @param error
	 *            the Errors object that stores and exposes information about
	 *            data-binding and validation errors for a specific object.
	 */
	private void validateSeatClasses(int seatClass1, int seatClass2,
			int seatClass3, Errors error) {

		boolean bool = ((seatClass1 > 0) || (seatClass2 > 0) || (seatClass3 > 0)) ? false
				: true;

		if ((seatClass1 <= 0) && bool) {
			error.rejectValue(SEATCLASS1, SEATCLASS_REQUIRED);
		}

		if ((seatClass2 <= 0) && bool) {
			error.rejectValue(SEATCLASS2, SEATCLASS_REQUIRED);
		}

		if ((seatClass3 <= 0) && bool) {
			error.rejectValue(SEATCLASS3, SEATCLASS_REQUIRED);
		}
	}

	/**
	 * Verifies if price is not less then 0 or equals to 0.
	 * @param genPrice
	 *            price to check.
	 * @param error
	 *            the Errors object that stores and exposes information about
	 *            data-binding and validation errors for a specific object.
	 */
	private void validateGeneralPrice(double genPrice, Errors error) {

		if (genPrice <= 0.0) {
			error.rejectValue(GENERAL_PRICE, GEN_PRICE_REQUIRED);
		}
	}
}
