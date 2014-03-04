package com.ita.edu.softserve.web;

import org.springframework.stereotype.Component;

@Component("TicketValidator")
public class TicketValidator {
	
	public static final String TRANSPORT_CODE_PATERN = "^[a-zA]{3,15}$";

}
