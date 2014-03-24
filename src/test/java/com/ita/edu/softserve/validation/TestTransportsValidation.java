package com.ita.edu.softserve.validation;

import static com.ita.edu.softserve.utils.ParseUtil.parseStringToTime;
import static org.junit.Assert.assertFalse;
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

	private static final String TRANSPORT_CODE_FIELD_NAME = "transportCode";
	private static final String START_TIME_FIELD_NAME = "startTime";
	private static final String ROUTES_FIELD_NAME = "routes";
	private static final String SEATCLASS1_FIELD_NAME = "seatclass1";
	private static final String SEATCLASS2_FIELD_NAME = "seatclass2";
	private static final String SEATCLASS3_FIELD_NAME = "seatclass3";
	private static final String GEN_PRICE_FIELD_NAME = "genPrice";

	private static final String TRANSPORT_OBJECT_NAME = "transport";

	private static final String MOCK_TRANSPORTS_CODE = "T000000001";
	private static final String ILIGAL_TRANSPORTS_CODE = "T0000000@1";

	private Validator transportsValidator = new TransportsValidator();

	private Transports transport;

	private Errors errors;

	@Before
	public void setUp() {
		transport = new Transports();
	}

	/**
	 * Test method for TransportsValidator.
	 */
	@Test
	public void testTransportsValidator() {
		transport.setTransportCode(MOCK_TRANSPORTS_CODE);
		transport.setRoutes(new Routes());
		transport.setStartTime(parseStringToTime("10:10:00"));
		transport.setSeatclass1(100);
		transport.setSeatclass2(100);
		transport.setSeatclass3(100);
		transport.setGenPrice(25);
		
		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertFalse(errors.hasErrors());
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#supports(Class)}.
	 */
	@Test
	public void testSupports() {
		assertFalse(transportsValidator.supports(null));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#supports(Class)}.
	 */
	@Test
	public void testSupportsObject() {
		assertFalse(transportsValidator.supports(Object.class));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#supports(Class)}.
	 */
	@Test
	public void testSupportsTramsports() {
		assertTrue(transportsValidator.supports(transport.getClass()));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateTransportCode(String, Errors)}.
	 */
	@Test
	public void testTransportCodeNull() {
		transport.setTransportCode(null);
		
		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(TRANSPORT_CODE_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateTransportCode(String, Errors)}.
	 */
	@Test
	public void testIligalTransportCode() {
		transport.setTransportCode(ILIGAL_TRANSPORTS_CODE);
		
		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(TRANSPORT_CODE_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateTransportCode(String, Errors)}.
	 */
	@Test
	public void blankTransportCode() {
		transport.setTransportCode("");
		
		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(TRANSPORT_CODE_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateTransportCode(String, Errors)}.
	 */
	@Test
	public void hasTransportCode() {
		transport.setTransportCode(MOCK_TRANSPORTS_CODE);
		
		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError(TRANSPORT_CODE_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateStartTime(Time, Errors)}.
	 */
	@Test
	public void needsStartTime() {
		transport.setStartTime(null);
		
		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(START_TIME_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateStartTime(Time, Errors)}.
	 */
	@Test
	public void hasStartTime() {
		transport.setStartTime(parseStringToTime("10:10:00"));

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError(START_TIME_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateRoutes(Routes, Errors)}.
	 */
	@Test
	public void needsRoutes() {
		transport.setRoutes(null);
		
		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(ROUTES_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateRoutes(Routes, Errors)}.
	 */
	@Test
	public void hasRoutes() {
		transport.setRoutes(new Routes());

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError(ROUTES_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}.
	 */
	@Test
	public void needsSeatclass1() {
		transport.setSeatclass1(0);
		
		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(SEATCLASS1_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}.
	 */
	@Test
	public void negativeSeatclass1() {
		transport.setSeatclass1(-1);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(SEATCLASS1_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}.
	 */
	@Test
	public void hasSeatclass1() {
		transport.setSeatclass1(100);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError(SEATCLASS1_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}.
	 */
	@Test
	public void needsSeatclass2() {
		transport.setSeatclass2(0);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(SEATCLASS2_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}.
	 */
	@Test
	public void negativeSeatclass2() {
		transport.setSeatclass2(-1);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(SEATCLASS2_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}.
	 */
	@Test
	public void hasSeatclass2() {
		transport.setSeatclass2(100);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError(SEATCLASS2_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}.
	 */
	@Test
	public void needsSeatclass3() {
		transport.setSeatclass3(0);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(SEATCLASS3_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}.
	 */
	@Test
	public void negativeSeatclass3() {
		transport.setSeatclass3(-1);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(SEATCLASS3_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}.
	 */
	@Test
	public void hasSeatclass3() {
		transport.setSeatclass3(100);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError(SEATCLASS3_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateGeneralPrice(double, Errors)}.
	 */
	@Test
	public void needsGeneralPrice() {
		transport.setGenPrice(0);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(GEN_PRICE_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateGeneralPrice(double, Errors)}.
	 */
	@Test
	public void negativeGeneralPrice() {
		transport.setGenPrice(-1);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(GEN_PRICE_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateGeneralPrice(double, Errors)}.
	 */
	@Test
	public void hasGeneralPrice() {
		transport.setGenPrice(25);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError(GEN_PRICE_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateIfTransportExist(Integer, String, Errors)}.
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
		when(mockTransportsManagerImpl.findTransportsByCode(MOCK_TRANSPORTS_CODE))
			.thenReturn(transports);

		transport.setTransportId(1);
		transport.setTransportCode(MOCK_TRANSPORTS_CODE);
		
		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError(TRANSPORT_CODE_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateIfTransportExist(Integer, String, Errors)}.
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

		when(mockTransportsManagerImpl
					.findTransportsByCode(MOCK_TRANSPORTS_CODE))
			.thenReturn(transports);

		transport.setTransportId(1);
		transport.setTransportCode(MOCK_TRANSPORTS_CODE);
		
		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(TRANSPORT_CODE_FIELD_NAME));
	}
}
