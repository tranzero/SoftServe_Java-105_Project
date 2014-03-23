package com.ita.edu.softserve.validation;

import static com.ita.edu.softserve.utils.ParseUtil.parseStringToTime;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.sql.Time;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.impl.TransportsManagerImpl;

public class TestTransportsValidation {
	
	private final String mockTransportsCode = "T000000001";

	private Validator transportsValidator;

	private Transports transport;

	private Errors errors;

	@Before
	public void setUp() {
		transportsValidator = new TransportsValidator();
		transport = new Transports();
	}
	
	@Test
	public void supports() {
//		transportsValidator = mock(TransportsValidator.class);
	        boolean supports = transportsValidator.supports(null);
	        
	        assertEquals(false, supports);

	}
	
	@Test
	public void supports2() {
	        boolean supports = transportsValidator.supports(Transports.class);
	        
	        assertEquals(true, supports);
	}

	/* Black box tests */
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateTransportCode(String, Errors)}
	 */
	@Test
	public void needsTransportCode() {
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("transportCode"));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateTransportCode(String, Errors)}
	 */
	@Test
	public void blankTransportCode() {
		transport.setTransportCode("");
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("transportCode"));
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateStartTime(Time, Errors)}
	 */
	@Test
	public void needsStartTime() {
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("startTime"));
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateRoutes(Routes, Errors)}
	 */
	@Test
	public void needsRoutes() {
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("routes"));
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}
	 */
	@Test
	public void needsSeatclass1() {
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("seatclass1"));

	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}
	 */
	@Test
	public void needsSeatclass2() {
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("seatclass2"));
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}
	 */
	@Test
	public void needsSeatclass3() {
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("seatclass3"));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateGeneralPrice(double, Errors)}
	 */
	@Test
	public void needsGeneralPrice() {
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("genPrice"));
	}

	/* Dark box tests */
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateTransportCode(String, Errors)}
	 */
	@Test
	public void wrongTransportCode() {
		transport.setTransportCode("T0000000@1");
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("transportCode"));
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}
	 */
	@Test
	public void negativeSeatclass1() {
		transport.setSeatclass1(-1);
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("seatclass1"));
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}
	 */
	@Test
	public void negativeSeatclass2() {
		transport.setSeatclass2(-1);
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("seatclass2"));
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}
	 */
	@Test
	public void negativeSeatclass3() {
		transport.setSeatclass3(-1);
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("seatclass3"));
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateGeneralPrice(double, Errors)}
	 */
	@Test
	public void negativeGeneralPrice() {
		transport.setGenPrice(-1);
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("genPrice"));
	}

	/* White box tests */
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateTransportCode(String, Errors)}
	 */
	@Test
	public void hasTransportCode() {
		transport.setTransportCode(mockTransportsCode);
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("transportCode"));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateStartTime(Time, Errors)}
	 */
	@Test
	public void hasStartTime() {
		transport.setStartTime(parseStringToTime("10:10:00"));
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("startTime"));
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateRoutes(Routes, Errors)}
	 */
	@Test
	public void hasRoutes() {
		transport.setRoutes(new Routes());
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("routes"));
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}
	 */
	@Test
	public void hasSeatclass1() {
		transport.setSeatclass1(100);
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("seatclass1"));
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}
	 */
	@Test
	public void hasSeatclass2() {
		transport.setSeatclass2(100);
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("seatclass2"));
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}
	 */
	@Test
	public void hasSeatclass3() {
		transport.setSeatclass3(100);
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("seatclass3"));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateGeneralPrice(double, Errors)}
	 */
	@Test
	public void hasGeneralPrice() {
		transport.setGenPrice(25);
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("genPrice"));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateIfTransportExist(Integer,
			String, Errors)}
	 */
	@Test
	public void testValidateIfTransportExist() throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException {

		TransportsManagerImpl mockTransportsManagerImpl = mock(TransportsManagerImpl.class);
		Field fild = transportsValidator.getClass().getDeclaredField(
				"transportsManager");

		fild.setAccessible(true);
		fild.set(transportsValidator, mockTransportsManagerImpl);

		Transports transports = new Transports();
		transports.setTransportId(1);
		when(mockTransportsManagerImpl.findTransportsByCode(mockTransportsCode))
				.thenReturn(transports);

		transport.setTransportId(1);
		transport.setTransportCode(mockTransportsCode);
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError("transportCode"));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateIfTransportExist(Integer,
			String, Errors)}
	 */
	@Test
	public void testBlackValidateIfTransportExist()
			throws IllegalArgumentException, IllegalAccessException,
			NoSuchFieldException, SecurityException {

		TransportsManagerImpl mockTransportsManagerImpl = mock(TransportsManagerImpl.class);
		Field fild = transportsValidator.getClass().getDeclaredField(
				"transportsManager");

		fild.setAccessible(true);
		fild.set(transportsValidator, mockTransportsManagerImpl);

		Transports transports = new Transports();
		transports.setTransportId(2);

		when(mockTransportsManagerImpl.findTransportsByCode(mockTransportsCode))
				.thenReturn(transports);

		transport.setTransportId(1);
		transport.setTransportCode(mockTransportsCode);
		errors = new BeanPropertyBindingResult(transport, "transport");
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("transportCode"));
	}
}