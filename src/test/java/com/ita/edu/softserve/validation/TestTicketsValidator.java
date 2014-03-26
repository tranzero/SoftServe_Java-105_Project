/**
 * 
 */
package com.ita.edu.softserve.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.ita.edu.softserve.entity.Tickets;

/**
 * @author admin
 *
 */
public class TestTicketsValidator {

	private static final String TICKET_OBJECT_NAME = "ticket";

	private static final String CUSTOMER_LAST_NAME_VALUE = "customerLastName";

	private static final String WRONG_CUSTOMER_LAST_NAME_VALUE = "!!!last@Name***";

	private static final String CUSTOMER_FIRST_NAME_VALUE = "customerFirstName";

	private static final String WRONG_CUSTOMER_FIRST_NAME_VALUE = "!!!first@Name***";

	private TicketValidator ticketValidator;

	private Tickets ticket;

	private Errors errors;

	@Before
	public void setUp() {
		ticketValidator = new TicketValidator();
		ticket = new Tickets();
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TicketValidator#supports(Class)}
	 */
	@Test
	public void testForSupportMethod1() {
		boolean supports = ticketValidator.supports(null);
		assertEquals(false, supports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TicketValidator#supports(Class)}
	 */
	@Test
	public void testForSupportMethod2() {
		boolean supports = ticketValidator.supports(ticket.getClass());
		assertEquals(true, supports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TicketValidator#supports(Class)}
	 */
	@Test
	public void testValidateCustomerFirstNameMethod() {
		errors = new BeanPropertyBindingResult(ticket, TICKET_OBJECT_NAME);
		ticketValidator.validate(ticket, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(CUSTOMER_FIRST_NAME_VALUE));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.StationsValidator#validateStationCode(String, Errors)}
	 */
	@Test
	public void testWrongCustomerFirstNameValue() {
		ticket.setCustomerFirstName(WRONG_CUSTOMER_FIRST_NAME_VALUE);
		errors = new BeanPropertyBindingResult(ticket, TICKET_OBJECT_NAME);
		ticketValidator.validate(ticket, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(CUSTOMER_FIRST_NAME_VALUE));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.StationsValidator#validateStationCode(String, Errors)}
	 */
	@Test
	public void testCustomerFirstNameNullValue() {
		ticket.setCustomerFirstName(null);

		errors = new BeanPropertyBindingResult(ticket, TICKET_OBJECT_NAME);
		ticketValidator.validate(ticket, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(CUSTOMER_FIRST_NAME_VALUE));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.StationsValidator#validateStationName(String, Errors)}
	 */
	@Test
	public void testValidateCustomerLastNameMethod() {
		errors = new BeanPropertyBindingResult(ticket, TICKET_OBJECT_NAME);
		ticketValidator.validate(ticket, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(CUSTOMER_LAST_NAME_VALUE));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.StationsValidator#validateStationName(String, Errors)}
	 */
	@Test
	public void testWrongCustomerLastNameValue() {
		ticket.setCustomerLastName(WRONG_CUSTOMER_LAST_NAME_VALUE);
		errors = new BeanPropertyBindingResult(ticket, TICKET_OBJECT_NAME);
		ticketValidator.validate(ticket, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(CUSTOMER_LAST_NAME_VALUE));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.StationsValidator#validateStationName(String, Errors)}
	 */
	@Test
	public void testCustomerLastNameNullValue() {
		ticket.setCustomerLastName(null);
		errors = new BeanPropertyBindingResult(ticket, TICKET_OBJECT_NAME);
		ticketValidator.validate(ticket, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(CUSTOMER_LAST_NAME_VALUE));
	}

}
