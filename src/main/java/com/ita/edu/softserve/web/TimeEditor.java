package com.ita.edu.softserve.web;

import static com.ita.edu.softserve.utils.ParseUtil.timeParse;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

/**
 * Property editor for <code>java.util.Time</code>, supporting a custom
 * <code>java.text.DateFormat</code>.
 * 
 * <p>
 * This is not meant to be used as system PropertyEditor but rather as
 * locale-specific date editor within custom controller code, parsing
 * user-entered number strings into Time properties of beans and rendering them
 * in the UI form.
 * 
 * <p>
 * In web MVC code, this editor will typically be registered with
 * <code>binder.registerCustomEditor</code> calls in a custom
 * <code>initBinder</code> method.
 * 
 * @author Roman
 * @since 22.02.2014
 * @see java.sql.Time
 * @see java.text.DateFormat
 * @see org.springframework.validation.DataBinder#registerCustomEditor
 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder
 */
public class TimeEditor extends PropertyEditorSupport {

	private final DateFormat dateFormat;

	private final boolean allowEmpty;

	private final int exactDateLength;

	/**
	 * Create a new CustomDateEditor instance, using the given DateFormat for
	 * parsing and rendering.
	 * <p>
	 * The "allowEmpty" parameter states if an empty String should be allowed
	 * for parsing, i.e. get interpreted as null value. Otherwise, an
	 * IllegalArgumentException gets thrown in that case.
	 * 
	 * @param dateFormat
	 *            DateFormat to use for parsing and rendering
	 * @param allowEmpty
	 *            if empty strings should be allowed
	 */
	public TimeEditor(DateFormat dateFormat, boolean allowEmpty) {
		this.dateFormat = dateFormat;
		this.allowEmpty = allowEmpty;
		this.exactDateLength = -1;
	}

	/**
	 * Create a new CustomDateEditor instance, using the given DateFormat for
	 * parsing and rendering.
	 * <p>
	 * The "allowEmpty" parameter states if an empty String should be allowed
	 * for parsing, i.e. get interpreted as null value. Otherwise, an
	 * IllegalArgumentException gets thrown in that case.
	 * <p>
	 * The "exactDateLength" parameter states that IllegalArgumentException gets
	 * thrown if the String does not exactly match the length specified. This is
	 * useful because SimpleDateFormat does not enforce strict parsing of the
	 * year part, not even with <code>setLenient(false)</code>. Without an
	 * "exactDateLength" specified, the "01/01/05" would get parsed to
	 * "01/01/0005". However, even with an "exactDateLength" specified,
	 * prepended zeros in the day or month part may still allow for a shorter
	 * year part, so consider this as just one more assertion that gets you
	 * closer to the intended date format.
	 * 
	 * @param dateFormat
	 *            DateFormat to use for parsing and rendering
	 * @param allowEmpty
	 *            if empty strings should be allowed
	 * @param exactDateLength
	 *            the exact expected length of the date String
	 */
	public TimeEditor(DateFormat dateFormat, boolean allowEmpty,
			int exactDateLength) {
		this.dateFormat = dateFormat;
		this.allowEmpty = allowEmpty;
		this.exactDateLength = exactDateLength;
	}

	/**
	 * Parse the Time from the given text, using the specified DateFormat.
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			// Treat empty String as null value.
			setValue(null);
		} else if (text != null && this.exactDateLength >= 0
				&& text.length() != this.exactDateLength) {
			throw new IllegalArgumentException(
					"Could not parse date: it is not exactly"
							+ this.exactDateLength + "characters long");
		} else {
			setValue(timeParse(text));
		}
	}

	/**
	 * Format the Time as String, using the specified DateFormat.
	 */
	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		return (value != null ? this.dateFormat.format(value) : "");
	}

}
