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
	public static Time timeParse(final String time) {
		if (time == null) {
			LOGGER.error("The param time is null");
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
}
