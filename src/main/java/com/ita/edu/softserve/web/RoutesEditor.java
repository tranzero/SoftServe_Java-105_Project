package com.ita.edu.softserve.web;

import java.beans.PropertyEditorSupport;

import org.springframework.util.Assert;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.manager.RoutesManager;

/**
 * @author Roman
 */
public class RoutesEditor extends PropertyEditorSupport {

	RoutesManager routesManager;

	/**
	 * Create a new RoutesEditor instance, using the given RoutesManager for
	 * parsing and rendering.
	 * @param routesManager
	 *            the RoutesManager to get the Routes as String and set the
	 *            String as Routes.
	 */
	public RoutesEditor(RoutesManager routesManager) {
		this.routesManager = routesManager;
	}

	/**
	 * Parse the Routes from the given text.
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Assert.notNull(text, "Text must not be null");

		String trimmed = StringUtils.trimAllWhitespace(text);
		// Use default valueOf methods for parsing text.
		Integer numb = NumberUtils.parseNumber(trimmed, Integer.class);

		setValue(this.routesManager.findRoutesById(numb));
	}

	/**
	 * Format the Routes as String.
	 * @see java.beans.PropertyEditorSupport#getAsText()
	 */
	@Override
	public String getAsText() {
		Routes value = (Routes) getValue();
		return (value != null ? String.valueOf(value.getRouteId()) : "");
	}

}
