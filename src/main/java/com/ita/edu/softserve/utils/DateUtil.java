package com.ita.edu.softserve.utils;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	
	/**
	 * String for ukrainian language representation in locale format (used in
	 * formatting date)
	 */

	public static final String UKRAINIAN = "ua";

	/**
	 * String for spain language representation in locale format (used in
	 * formatting date)
	 */

	public static final String SPANISH = "es";
	
	
	public static Date parseLocalDate(String dateString, Locale locale) throws ParseException {
		if (locale.getLanguage().trim().equalsIgnoreCase(UKRAINIAN)
				|| locale.getLanguage().trim().equalsIgnoreCase(SPANISH)) {
			return ValidatorUtil.UKRAINIAN_AND_SPANISH_FORMATTER
					.parse(dateString);
		} else {
			return ValidatorUtil.DEFAULT_DATE_FORMATTER
					.parse(dateString);
		}
	}

}
