package com.ita.edu.softserve.validation;

//import static org.mockito.Mockito.mock;
import static com.ita.edu.softserve.utils.ParseUtil.parseStringToTime;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Transports;

public class TestTransportsValidation {

	@Mock
	private Validator transportsValidator = new TransportsValidator();

	Transports transport;

	Errors errors;

	@Before
	public void setUp() {
		transportsValidator = new TransportsValidator();
		transport = new Transports();
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);
	}

	/* Black box tests */
	@Test
	public void needsTransportCode() {
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("transportCode"));

	}

	@Test
	public void needsStartTime() {
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("startTime"));

	}

	@Test
	public void needsRoutes() {
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("routes"));

	}

	@Test
	public void needsSeatclass1() {
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("seatclass1"));

	}

	@Test
	public void needsSeatclass2() {
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("seatclass2"));

	}

	@Test
	public void needsSeatclass3() {
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("seatclass3"));

	}

	@Test
	public void needsGeneralPrice() {
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("genPrice"));
	}

	/* Dark box tests */
	@Test
	public void wrongTransportCode() {
		transport.setTransportCode("T0000000@");
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("transportCode"));

	}
	
	@Test
	public void negativeSeatclass1() {
		transport.setSeatclass1(-1);
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("seatclass1"));

	}
	
	@Test
	public void negativeSeatclass2() {
		transport.setSeatclass2(-1);
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);
		
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("seatclass2"));

	}

	@Test
	public void negativeSeatclass3() {
		transport.setSeatclass3(-1);
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);
		
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("seatclass3"));

	}

	@Test
	public void negativeGeneralPrice() {
		transport.setGenPrice(-1);
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);
		
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("genPrice"));
	}

	/* White box tests */
	@Test
	public void hasTransportCode() {
		transport.setTransportCode("T0000000");
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("transportCode"));

	}

	@Test
	public void hasStartTime() {
		transport.setStartTime(parseStringToTime("10:10:00"));
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("startTime"));

	}

	@Test
	public void hasRoutes() {
		transport.setRoutes(new Routes());
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("routes"));

	}

	@Test
	public void hasSeatclass1() {
		transport.setSeatclass1(100);
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("seatclass1"));

	}

	@Test
	public void hasSeatclass2() {
		transport.setSeatclass2(100);
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("seatclass2"));

	}

	@Test
	public void hasSeatclass3() {
		transport.setSeatclass3(100);
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("seatclass3"));

	}

	@Test
	public void hasGeneralPrice() {
		transport.setGenPrice(25);
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("genPrice"));
	}
}
