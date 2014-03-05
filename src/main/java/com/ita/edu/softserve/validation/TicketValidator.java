package com.ita.edu.softserve.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.entity.Tickets;
import com.ita.edu.softserve.entity.Transports;

@Component("ticketValidator")
public class TicketValidator implements Validator {
	
	private static final String CUSTOMER_LAST_NAME = "customerLastName";
	private static final String CUSTOMER_FIRST_NAME = "customerFirstName";
	public static final String PATERN = "^[a-zA]{3,15}$";
	
	private static final String TICKETS_MATCHER = "label.tickets.matcher";

	@Override
	public boolean supports(Class<?> clazz) {

		return Tickets.class.equals(clazz);
		
	}

	@Override
	public void validate(Object target, Errors errors) {

		Tickets ticket = (Tickets) target;
		
		validateCustomerFirstName(ticket.getCustomerFirstName(),errors);
		
		validateCustomerLastName(ticket.getCustomerLastName(),errors);
		
	}
	
	private void validateCustomerFirstName(String customerFirstName, Errors error) {

		if (customerFirstName.matches(PATERN) == false) {
			error.rejectValue(CUSTOMER_FIRST_NAME, TICKETS_MATCHER);
		}
	}
	
	private void validateCustomerLastName(String customerLastName, Errors error) {

		if (customerLastName.matches(PATERN) == false) {
			error.rejectValue(CUSTOMER_LAST_NAME, TICKETS_MATCHER);
		}
	}

}
