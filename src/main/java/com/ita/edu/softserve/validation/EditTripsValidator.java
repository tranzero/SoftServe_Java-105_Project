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
import com.ita.edu.softserve.utils.DateUtil;
import com.ita.edu.softserve.utils.ValidatorUtil;
import com.ita.edu.softserve.validationcontainers.impl.AddTripsInfoValidationContainer;
import com.ita.edu.softserve.validationcontainers.impl.EditTripsInfoValidationContainer;

@Component
@Qualifier("editTripsValidator")
public class EditTripsValidator implements Validator {
	
	public static final String WRONG_DATE = "startDate";
	public static final String WRONG_DATE_MESSAGE = "wrongDate=true&";
	public static final String WRONG_REM_SEAT_CLASS1 = "remSeatClass1";
	public static final String WRONG_REM_SEAT_CLASS2 = "remSeatClass2";
	public static final String WRONG_REM_SEAT_CLASS3 = "remSeatClass3";
	public static final String WRONG_REM_SEAT_MESSAGE = "wrongSeats=true&";
	
	
	@Autowired
	TransportsManager transportManager;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return EditTripsInfoValidationContainer.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		EditTripsInfoValidationContainer container = (EditTripsInfoValidationContainer) obj;
		validateDate(container.getStartDate(), errors, container.getLocaleParam());
		validateSeatsWithContainer(container, errors);;
		ValidatorUtil.validateTransportIdString(transportManager, container.getTransportId(), errors);
	}
	
	private void validateDate(String dateString, Errors errors, Locale locale){
		if (dateString == null) {
			errors.rejectValue(WRONG_DATE, WRONG_DATE_MESSAGE);
		} else {
			try {
				DateUtil.parseLocalDate(dateString, locale);;
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
