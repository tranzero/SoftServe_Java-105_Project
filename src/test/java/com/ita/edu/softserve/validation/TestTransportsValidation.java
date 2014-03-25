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
	private static final String ILIGAL_TRANSPORTS_CODE = "T00000@@@1";
	private static final String TEST_START_TIME = "10:10:00";

	private Validator transportsValidator = new TransportsValidator();

	private Transports transport;

	private Errors errors;

	@Before
	public void setUp() {
		transport = new Transports();
	}

	/**
	 * Test method for TransportsValidator where Transports is filled with valid arguments.
	 */
	@Test
	public void testTransportsValidator() {
		transport.setTransportCode(MOCK_TRANSPORTS_CODE);
		transport.setRoutes(new Routes());
		transport.setStartTime(parseStringToTime(TEST_START_TIME));
		transport.setSeatclass1(100);
		transport.setSeatclass2(100);
		transport.setSeatclass3(100);
		transport.setGenPrice(25);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertFalse(errors.hasErrors());
	}

	/**
	 * Test method for TransportsValidator where Transports is filled with not valid arguments.
	 */
	@Test
	public void testTransportsValidatorNotValid() {
		transport.setTransportCode(ILIGAL_TRANSPORTS_CODE);
		transport.setRoutes(null);
		transport.setStartTime(null);
		transport.setSeatclass1(0);
		transport.setSeatclass2(0);
		transport.setSeatclass3(0);
		transport.setGenPrice(0.0);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#supports(Class)}
	 * .
	 */
	@Test
	public void testSupports() {
		assertFalse(transportsValidator.supports(null));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#supports(Class)}
	 * .
	 */
	@Test
	public void testSupportsObject() {
		assertFalse(transportsValidator.supports(Object.class));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#supports(Class)}
	 * .
	 */
	@Test
	public void testSupportsTramsports() {
		assertTrue(transportsValidator.supports(transport.getClass()));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateTransportCode(String, Errors)}
	 * .
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
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateTransportCode(String, Errors)}
	 * .
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
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateTransportCode(String, Errors)}
	 * .
	 */
	@Test
	public void testBlankTransportCode() {
		transport.setTransportCode("");

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(TRANSPORT_CODE_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateTransportCode(String, Errors)}
	 * .
	 */
	@Test
	public void testHasTransportCode() {
		transport.setTransportCode(MOCK_TRANSPORTS_CODE);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError(TRANSPORT_CODE_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateStartTime(Time, Errors)}
	 * .
	 */
	@Test
	public void testNeedsStartTime() {
		transport.setStartTime(null);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(START_TIME_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateStartTime(Time, Errors)}
	 * .
	 */
	@Test
	public void testHasStartTime() {
		transport.setStartTime(parseStringToTime(TEST_START_TIME));

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError(START_TIME_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateRoutes(Routes, Errors)}
	 * .
	 */
	@Test
	public void testNeedsRoutes() {
		transport.setRoutes(null);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(ROUTES_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateRoutes(Routes, Errors)}
	 * .
	 */
	@Test
	public void testHasRoutes() {
		transport.setRoutes(new Routes());

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError(ROUTES_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}
	 * .
	 */
	@Test
	public void testNeedsSeatclass1() {
		transport.setSeatclass1(0);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(SEATCLASS1_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}
	 * .
	 */
	@Test
	public void testNegativeSeatclass1() {
		transport.setSeatclass1(-1);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(SEATCLASS1_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}
	 * .
	 */
	@Test
	public void testHasSeatclass1() {
		transport.setSeatclass1(100);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError(SEATCLASS1_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}
	 * .
	 */
	@Test
	public void testNeedsSeatclass2() {
		transport.setSeatclass2(0);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(SEATCLASS2_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}
	 * .
	 */
	@Test
	public void testNegativeSeatclass2() {
		transport.setSeatclass2(-1);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(SEATCLASS2_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}
	 * .
	 */
	@Test
	public void testHasSeatclass2() {
		transport.setSeatclass2(100);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError(SEATCLASS2_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}
	 * .
	 */
	@Test
	public void testNeedsSeatclass3() {
		transport.setSeatclass3(0);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(SEATCLASS3_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}
	 * .
	 */
	@Test
	public void testNegativeSeatclass3() {
		transport.setSeatclass3(-1);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(SEATCLASS3_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateSeatClasses(int, int, int, Errors)}
	 * .
	 */
	@Test
	public void testHasSeatclass3() {
		transport.setSeatclass3(100);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError(SEATCLASS3_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateGeneralPrice(double, Errors)}
	 * .
	 */
	@Test
	public void testNeedsGeneralPrice() {
		transport.setGenPrice(0);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(GEN_PRICE_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateGeneralPrice(double, Errors)}
	 * .
	 */
	@Test
	public void testNegativeGeneralPrice() {
		transport.setGenPrice(-1);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(GEN_PRICE_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateGeneralPrice(double, Errors)}
	 * .
	 */
	@Test
	public void testHasGeneralPrice() {
		transport.setGenPrice(25);

		errors = new BeanPropertyBindingResult(transport, TRANSPORT_OBJECT_NAME);
		transportsValidator.validate(transport, errors);

		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError(GEN_PRICE_FIELD_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateIfTransportExist(Integer, String, Errors)}
	 * .
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
		when(
				mockTransportsManagerImpl
						.findTransportsByCode(MOCK_TRANSPORTS_CODE))
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
	 * {@link com.ita.edu.softserve.validation.TransportsValidator#validateIfTransportExist(Integer, String, Errors)}
	 * .
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

		when(
				mockTransportsManagerImpl
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
