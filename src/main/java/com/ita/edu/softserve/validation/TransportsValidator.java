package com.ita.edu.softserve.validation;

import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.TransportsManager;

@Component("transportsValidator")
public class TransportsValidator implements Validator {

	public static final String TRANSPORT_CODE_PATERN = "^[a-zA-Z0-9]{5,15}$";

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
		Transports transports = (Transports) obj;

		validateTransportCode(transports.getTransportCode(), error);

		validateIfTransportExist(transports.getTransportId(),
				transports.getTransportCode(), error);

		validateStartTime(transports.getStartTime(), error);

		validateRoutes(transports.getRoutes(), error);
		
		boolean isSeatClassValid = isAtLeastOneFilled(transports.getSeatclass1(),
				transports.getSeatclass2(), transports.getSeatclass3());
		
		validateSeatClasse1(transports.getSeatclass1(), error, isSeatClassValid);

		validateSeatClasse2(transports.getSeatclass2(), error, isSeatClassValid);

		validateSeatClasse3(transports.getSeatclass3(), error, isSeatClassValid);

		validateGeneralPrice(transports.getGenPrice(), error);

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
		if (transportCode == null || transportCode.isEmpty()) {
			error.rejectValue(TRANSPORT_CODE, TRANSPORT_CODE_MATCHER);

		} else if (!transportCode.matches(TRANSPORT_CODE_PATERN)) {
			error.rejectValue(TRANSPORT_CODE, TRANSPORT_CODE_MATCHER);
		}
	}

	/**
	 * Finds out if Transports object exist in database with such transport
	 * code.
	 * 
	 * @param transportId
	 *            the transport ID to check.
	 * @param transportCode
	 *            the transport code to check.
	 * @param error
	 *            the error to register message.
	 */
	private void validateIfTransportExist(Integer transportId,
			String transportCode, Errors error) {

		if ((transportCode != null) && (!transportCode.isEmpty())) {
			Transports transport = null;

			try {
				transport = transportsManager
						.findTransportsByCode(transportCode);

				if ((transport.getTransportId()).equals(transportId)) {
					return;

				} else {
					error.rejectValue(TRANSPORT_CODE, TRANSPORT_CODE_EXIST);
				}

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
	 * Verifies if seat class 1 are not less then 0 or equals to 0. Must be
	 * entered number of seats at least one class.
	 * 
	 * @param seatClass1
	 *            the seat class 1 to check.
	 * @param error
	 *            the Errors object that stores and exposes information about
	 *            data-binding and validation errors for a specific object.
	 */
	private void validateSeatClasse1(int seatClass1, Errors error, boolean bool) {
		if ((seatClass1 <= 0) && bool) {
			error.rejectValue(SEATCLASS1, SEATCLASS_REQUIRED);
		}
	}
	
	/**
	 * Verifies if seat class 2 are not less then 0 or equals to 0. Must be
	 * entered number of seats at least one class.
	 * 
	 * @param seatClass2
	 *            the seat class 2 to check.
	 * @param error
	 *            the Errors object that stores and exposes information about
	 *            data-binding and validation errors for a specific object.
	 */
	private void validateSeatClasse2(int seatClass2, Errors error, boolean bool) {
		if ((seatClass2 <= 0) && bool) {
			error.rejectValue(SEATCLASS2, SEATCLASS_REQUIRED);
		}
	}
	/**
	 * Verifies if seat class 3 are not less then 0 or equals to 0. Must be
	 * entered number of seats at least one class.
	 * 
	 * @param seatClass3
	 *            the seat class 3 to check.
	 * @param error
	 *            the Errors object that stores and exposes information about
	 *            data-binding and validation errors for a specific object.
	 */
	private void validateSeatClasse3(int seatClass3, Errors error, boolean bool) {
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

	/**
	 * 
	 * @param seatClass1 the seat class 1 to check.
	 * @param seatClass2 the seat class 2 to check.
	 * @param seatClass3 the seat class 3 to check.
	 * @return true if on of three seat classes are filled otherwise false
	 */
	private boolean isAtLeastOneFilled(int seatClass1, int seatClass2, int seatClass3) {
		return ((seatClass1 > 0) || (seatClass2 > 0) || (seatClass3 > 0)) ? false
				: true;
	}
}
