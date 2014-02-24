package com.ita.edu.softserve.utils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * Class for parser.
 * @author Roman
 */
public final class ParseUtil {
	
	private static final String HH_MM_SS = "hh:mm:ss";
	
	private static final Logger LOGGER = Logger.getLogger(ParseUtil.class);

	/**
	 * The private constructor without arguments.
	 */
	private ParseUtil() {
	}

	/**
	 * Parses time representing in string into sql time.
	 * @param time
	 *            the string object to parse.
	 * @return the sql time object.
	 */
	public static Time parseStringToTime(String time) {
		if (time == null) {
			LOGGER.error("The String object is null");
			return null;
		}

		DateFormat sdf = new SimpleDateFormat(HH_MM_SS);
		Date date = null;

		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			LOGGER.error(e);
		}

		return new Time(date.getTime());
	}
	
	/**
	 * Formats a time in JDBC time escape format.
	 * @param time the Time object to parse.
	 * @return a String in hh:mm:ss format.
	 */
	public static String parseTimeToString(Time time) {
		if (time == null) {
			LOGGER.error("The Time object is null");
			return null;
		}

		return time.toString();
	}
}
