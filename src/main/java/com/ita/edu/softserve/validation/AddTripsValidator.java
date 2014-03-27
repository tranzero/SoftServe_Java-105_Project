package com.ita.edu.softserve.validation;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.dao.TransportsDao;
import com.ita.edu.softserve.manager.TransportsManager;
import com.ita.edu.softserve.manager.impl.TripsManagerImpl;
import com.ita.edu.softserve.utils.ValidatorUtil;
import com.ita.edu.softserve.validationcontainers.impl.AddTripsInfoValidationContainer;

@Component
@Qualifier("addTripsValidator")
public class AddTripsValidator implements Validator {

	public static final String WRONG_DATE_MIN = "from";
	public static final String WRONG_DATE_MAX = "to";
	public static final String WRONG_DATE_MESSAGE = "wrongDate=true&";
	
	@Autowired
	TransportsManager transportManager;

	@Override
	public boolean supports(Class<?> clazz) {
		return AddTripsInfoValidationContainer.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		AddTripsInfoValidationContainer container = (AddTripsInfoValidationContainer) obj;
		validateDatesFromContainer(container, errors);
		ValidatorUtil.validateTransportIdString(transportManager, container.getTransportId(), errors);
	}

	private void validateDatesFromContainer(
			AddTripsInfoValidationContainer container, Errors errors) {
		String minDate = container.getFrom();
		String maxDate = container.getTo();
		Locale locale = container.getLocaleParam();
		
		if (minDate == null) {
			errors.rejectValue(WRONG_DATE_MIN, WRONG_DATE_MESSAGE);
		} else {
			try {
				if (locale.getLanguage().trim()
						.equalsIgnoreCase(TripsManagerImpl.UKRAINIAN)
						|| locale.getLanguage().trim()
								.equalsIgnoreCase(TripsManagerImpl.SPANISH)) {
					ValidatorUtil.UKRAINIAN_AND_SPANISH_FORMATTER
							.parse(minDate);
				} else {
					ValidatorUtil.DEFAULT_DATE_FORMATTER.parse(minDate);
				}
			} catch (Exception e) {
				errors.rejectValue(WRONG_DATE_MIN, WRONG_DATE_MESSAGE);
			}
		}

		if (maxDate == null) {
			errors.rejectValue(WRONG_DATE_MAX, WRONG_DATE_MESSAGE);
		} else {
			try {
				if (locale.getLanguage().trim()
						.equalsIgnoreCase(TripsManagerImpl.UKRAINIAN)
						|| locale.getLanguage().trim()
								.equalsIgnoreCase(TripsManagerImpl.SPANISH)) {
					ValidatorUtil.UKRAINIAN_AND_SPANISH_FORMATTER
							.parse(maxDate);
				} else {
					ValidatorUtil.DEFAULT_DATE_FORMATTER.parse(maxDate);
				}
			} catch (Exception e) {
				errors.rejectValue(WRONG_DATE_MAX, WRONG_DATE_MESSAGE);
			}
		}

	}
}
