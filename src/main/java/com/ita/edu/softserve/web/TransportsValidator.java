package com.ita.edu.softserve.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.entity.Transports;

@Component("transportsValidator")
public class TransportsValidator implements Validator {

	/**
	 * This Validator validates *just* Transports instance
	 */
	public boolean supports(Class<?> clazz) {
		return Transports.class.equals(clazz);
	}

	public void validate(Object obj, Errors error) {
		Transports transport = (Transports) obj;
		if (transport.getGenPrice() <= 0) {
			error.reject("transport.null", "Transports is null");
		}

		ValidationUtils.rejectIfEmpty(error, "transportCode",
				"transportCode.empty", "Empty transport Code");
		ValidationUtils.rejectIfEmpty(error, "startTime", "startTime.empty",
				"Empty start Time");
		
//		ValidationUtils.rejectIfEmpty(error, "seatclass1", "seatclass1.empty",
//				"Empty seat class 1");
		ValidationUtils.rejectIfEmpty(error, "seatclass2", "seatclass2.empty",
				"Empty seat class 2");
		ValidationUtils.rejectIfEmpty(error, "seatclass3", "seatclass3.empty",
				"Empty seat class 3");
		ValidationUtils.rejectIfEmpty(error, "genPrice", "genPrice.empty",
				"Empty general Price");

			
		if (transport.getSeatclass1() == 0) {
			error.rejectValue("seatclass1", "seatclass1.required");
		}
		
		if (transport.getSeatclass2() == 0) {
			error.rejectValue("seatclass2", "seatclass2.required");
		}
		
		if (transport.getSeatclass3() == 0) {
			error.rejectValue("seatclass3", "seatclass3.required");
		}
		
		if (transport.getGenPrice() == 0.0) {
			error.rejectValue("genPrice", "genPrice.required");
		}
		
		try{
			transport.getSeatclass1();
		}catch(NumberFormatException e){
			error.rejectValue("seatclass1", "seatclass1.required");
		}
		
	}

}
