package com.ita.edu.softserve.propertyeditors;

import java.beans.PropertyEditorSupport;

import com.ita.edu.softserve.entity.Role;

/**
 * Class UserRoleEditor
 * 
 * @author Iryna
 */

public class UserRoleEditor extends PropertyEditorSupport {

	/**
	 * Format the Role as String.- return valueOfRole.name()
	 * 
	 * @see java.beans.PropertyEditorSupport#getAsText()
	 */
	@Override
	public String getAsText() {
		Role valueOfRole = (Role) getValue();
		return (valueOfRole != null ? valueOfRole.name() : "");
	}

	/**
	 * Parse the Role from the given text.
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null) {
			setValue(Role.valueOf(text));
		} else {
			setValue(null);
		}
	}

}
