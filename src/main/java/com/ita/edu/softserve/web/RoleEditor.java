package com.ita.edu.softserve.web;

import java.beans.PropertyEditorSupport;

import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.manager.RoutesManager;

/**
 * @author Roman
 */
//@Component("roleEditor")
public class RoleEditor extends PropertyEditorSupport {

	/**
	 * Parse the Role from the given text.
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null) {
			setValue(null);
		} else {
	        setValue(Role.valueOf(text));
		}
	}

	/**
	 * Format the Role as String.
	 * 
	 * @see java.beans.PropertyEditorSupport#getAsText()
	 */
	@Override
	public String getAsText() {
		Role value = (Role) getValue();
		return (value != null ? value.getDescription() : "");
	}

}
