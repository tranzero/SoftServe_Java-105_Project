package com.ita.edu.softserve.validation;

import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.TransportsManager;
import com.ita.edu.softserve.utils.ParseUtil;

@Component("transportsValidator")
public class TransportsValidator implements Validator {

	public static final String TRANSPORT_CODE_PATERN = "^[a-zA-Z0-9]{5,15}$";
	public static final String START_TIME_PATERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";

	private static final String TRANSPORT_CODE = "transportCode";
	private static final String START_TIME = "startTime";
	private static final String ROUTES = "routes";
	private static final String SEATCLASS1 = "seatclass1";
	private static final String SEATCLASS2 = "seatclass2";
	private static final String SEATCLASS3 = "seatclass3";
	private static final String GENERAL_PRICE = "genPrice";

	private static final String SEATCLASS_REQUIRED = "seatclass.required";
	private static final String GEN_PRICE_REQUIRED = "genPrice.required";
	private static final String TRANSPORT_CODE_MATCHER = "transportCode.matcher";
	private static final String START_TIME_MATCHER = "startTime.matcher";
	private static final String START_TIME_NULL = "startTime.null";
	private static final String TRANSPORT_CODE_EXIST = "transportCode.exist";
	private static final String ROUTES_NOT_EXIST = "routes.exist";

	/**
	 * Field for using transports-related methods.
	 */
	@Autowired
	private TransportsManager transportsManager;

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

		validateIfTransportExist(transport.getTransportId(), transport.getTransportCode(), error);

		validateStartTime(transport.getStartTime(), error);

		validateRoutes(transport.getRoutes(), error);

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

		if (transportCode.matches(TRANSPORT_CODE_PATERN) == false) {
			error.rejectValue(TRANSPORT_CODE, TRANSPORT_CODE_MATCHER);
		}
	}

	/**
	 * Finds out if Transports object exist in database with such transport
	 * code. 
	 * 
	 * @param transportCode
	 *            the transport code to check.
	 * @param error
	 *            the error to register message.
	 */
	private void validateIfTransportExist(Integer transportId, String transportCode, Errors error) {

		if ((transportCode != null) && (transportCode != "")) {
			Transports transport = null;

			try {
				transport = transportsManager.findTransportsByCode(transportCode);

				if ((transport.getTransportId()).equals(transportId)) {
					return;
				}
				error.rejectValue(TRANSPORT_CODE, TRANSPORT_CODE_EXIST);
			} catch (RuntimeException e) {
			}
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

			if (time.matches(START_TIME_PATERN) == false) {
				error.rejectValue(START_TIME, START_TIME_MATCHER);
			}
		}
	}

	/**
	 * If routes is <code>null</code> that mean it is not in database.
	 * 
	 * @param routes
	 *            the Routes to verify.
	 * @param error
	 *            the Errors object that stores and exposes information about
	 *            data-binding and validation errors for a specific object.
	 */
	private void validateRoutes(Routes routes, Errors error) {
		if (routes == null) {
			error.rejectValue(ROUTES, ROUTES_NOT_EXIST);
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
	 * 
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
