package com.ita.edu.softserve.utils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * Class for parser.
 * 
 * @author Roman
 */
public final class ParseUtil {
	private static final Logger LOGGER = Logger.getLogger(ParseUtil.class);

	/**
	 * private constructor.
	 */
	private ParseUtil() {
	}

	/**
	 * Parses time representing in string into sql time.
	 * 
	 * @return time sql time object.
	 */
	public static Time timeParse(final String time) {

		DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		Date date = null;

		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			LOGGER.error(e.toString());
		}

		return new Time(date.getTime());
	}
}
