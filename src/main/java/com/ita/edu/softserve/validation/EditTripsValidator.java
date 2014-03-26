package com.ita.edu.softserve.validation;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.dao.TransportsDao;
import com.ita.edu.softserve.manager.impl.TripsManagerImpl;
import com.ita.edu.softserve.utils.ValidatorUtil;
import com.ita.edu.softserve.validationcontainers.impl.AddTripsInfoValidationContainer;
import com.ita.edu.softserve.validationcontainers.impl.EditTripsInfoValidationContainer;

public class EditTripsValidator implements Validator {
	
	public static final String WRONG_DATE = "startDate";
	public static final String WRONG_DATE_MESSAGE = "wrongDate=true&";
	public static final String WRONG_REM_SEAT_CLASS1 = "remSeatClass1";
	public static final String WRONG_REM_SEAT_CLASS2 = "remSeatClass2";
	public static final String WRONG_REM_SEAT_CLASS3 = "remSeatClass3";
	public static final String WRONG_REM_SEAT_MESSAGE = "wrongSeats=true&";
	
	
	@Autowired
	TransportsDao transportDao;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return EditTripsInfoValidationContainer.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		EditTripsInfoValidationContainer container = (EditTripsInfoValidationContainer) obj;
		validateDate(container.getStartDate(), errors, container.getLocaleParam());
		validateSeatsWithContainer(container, errors);;
		ValidatorUtil.validateTransportIdString(transportDao, container.getTransportId(), errors);
	}
	
	private void validateDate(String dateString, Errors errors, Locale locale){
		if (dateString == null) {
			errors.rejectValue(WRONG_DATE, WRONG_DATE_MESSAGE);
		} else {
			try {
				if (locale.getLanguage().trim()
						.equalsIgnoreCase(TripsManagerImpl.UKRAINIAN)
						|| locale.getLanguage().trim()
								.equalsIgnoreCase(TripsManagerImpl.SPANISH)) {
					ValidatorUtil.UKRAINIAN_AND_SPANISH_FORMATTER
							.parse(dateString);
				} else {
					ValidatorUtil.DEFAULT_DATE_FORMATTER.parse(dateString);
				}
			} catch (Exception e) {
				errors.rejectValue(WRONG_DATE, WRONG_DATE_MESSAGE);
			}
		}

	}
	
	private void validateSeatsWithContainer(EditTripsInfoValidationContainer container, Errors errors){
		try{
			if(Integer.parseInt(container.getRemSeatClass1())<0){
				errors.rejectValue(WRONG_REM_SEAT_CLASS1, WRONG_REM_SEAT_MESSAGE);
			}
		} catch (Exception e){
			errors.rejectValue(WRONG_REM_SEAT_CLASS1, WRONG_REM_SEAT_MESSAGE);
		}
		try{
			if(Integer.parseInt(container.getRemSeatClass2())<0){
				errors.rejectValue(WRONG_REM_SEAT_CLASS2, WRONG_REM_SEAT_MESSAGE);
			}
		} catch (Exception e){
			errors.rejectValue(WRONG_REM_SEAT_CLASS2, WRONG_REM_SEAT_MESSAGE);
		}
		try{
			if(Integer.parseInt(container.getRemSeatClass3())<0){
				errors.rejectValue(WRONG_REM_SEAT_CLASS3, WRONG_REM_SEAT_MESSAGE);
			}
		} catch (Exception e){
			errors.rejectValue(WRONG_REM_SEAT_CLASS3, WRONG_REM_SEAT_MESSAGE);
		}
	}

}
