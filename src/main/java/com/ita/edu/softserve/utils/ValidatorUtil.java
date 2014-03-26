package com.ita.edu.softserve.utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.validation.Errors;

import com.ita.edu.softserve.dao.TransportsDao;
import com.ita.edu.softserve.web.TripsController;

/**
 * Class contains different utility methods used in validation
 * 
 * @author dnycktc
 * 
 */

public final class ValidatorUtil {
	
	public static final String WRONG_TRANSPORT_ID = "transportId";
	public static final String WRONG_TRANSPORT_ID_MESSAGE = "wrongTransportId=true&";

	/**
	 * Formatter of date with Ukrainian or Spanish date format
	 */
	public static final SimpleDateFormat UKRAINIAN_AND_SPANISH_FORMATTER = new SimpleDateFormat(
			TripsController.UKRAINIAN_OR_SPANISH_DATE_FORMAT);

	/**
	 * Formatter of date with default(English) date format
	 */
	public static final SimpleDateFormat DEFAULT_DATE_FORMATTER = new SimpleDateFormat(
			TripsController.DEFAULT_DATE_FORMAT);

	/**
	 * Method checks if the given object is null (and so should be replaced with
	 * default value)
	 * 
	 * @param checkObject
	 *            Object to check
	 * @param defaultValue
	 *            Default value (returned if object is null)
	 * @return given object or default value (if given object is null)
	 */
	public static Object defaultForNull(Object checkObject, Object defaultValue) {
		return (checkObject != null) ? checkObject : defaultValue;
	}

	/**
	 * Method gets date from given string according to given date format
	 * 
	 * @param dateString
	 *            string representation of date to check
	 * @param locale
	 *            locale of given date
	 * @param defualtString
	 *            externally checked correct string for case when the given
	 *            string is not in correct format
	 * @return result
	 */
	public static Date getDateWithFormat(String dateString, Locale locale,
			String defualtString) {
		try {
			if (locale.getLanguage().equalsIgnoreCase(TripsController.SPANISH)
					|| locale.getLanguage().equalsIgnoreCase(
							TripsController.UKRAINIAN)) {
				return UKRAINIAN_AND_SPANISH_FORMATTER.parse(dateString);
			} else {
				return DEFAULT_DATE_FORMATTER.parse(dateString);
			}
		} catch (Exception e) {
			try {
				return DEFAULT_DATE_FORMATTER.parse(defualtString);
			} catch (ParseException e1) {// if correct arguments, should never
											// be activated
				e1.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * Method gets time from given stringt
	 * 
	 * @param timeString
	 *            string representation of time to check
	 * @param defualtString
	 *            externally checked correct string for case when the given
	 *            string is not in correct format
	 * @return result
	 */
	public static Time getTime(String timeString, String defualtString) {
		try {
			return ParseUtil.parseStringToTime(timeString);
		} catch (Exception e) {
			return ParseUtil.parseStringToTime(defualtString);
		}
	}
	
	/**
	 * Checks if given string is empty or null
	 * 
	 * @param checkedString
	 *            string to check
	 * @return result of check
	 */
	public static boolean isEmptyString(String checkedString) {
		return checkedString == null || checkedString.equals("");
	}
	
	public static void validateTransportIdString(TransportsDao transportDao, String transportIdString, Errors errors){
		try{
			Integer transportId = Integer.parseInt(transportIdString);
			if (transportDao.findById(transportId)== null){
				errors.rejectValue(WRONG_TRANSPORT_ID, WRONG_TRANSPORT_ID_MESSAGE);
			}
		}
		catch(Exception e){
			errors.rejectValue(WRONG_TRANSPORT_ID, WRONG_TRANSPORT_ID_MESSAGE);
		}
	}

}
