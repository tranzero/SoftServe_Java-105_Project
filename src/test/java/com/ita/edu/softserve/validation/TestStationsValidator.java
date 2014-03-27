/**
 * 
 */
package com.ita.edu.softserve.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.entity.Stations;

/**
 * @author admin
 * 
 */
public class TestStationsValidator {

	private static final String STATION_OBJECT_NAME = "station";

	private static final String STATION_CODE_VALUE = "stationCode";
	private static final String STATION_NAME_VALUE = "stationName";

	private static final String WRONG_STATION_CODE_VALUE = "000000@A";
	private static final String WRONG_STATION_NAME_VALUE = "L'viv + @@@";

	private static final String MOCK_STATION_CODE = "00000000001";
	private static final String MOCK_STATION_NAME = "Stryy";

	private Validator stationsValidator = new StationsValidator();

	private Stations station;

	private Errors errors;

	@Before
	public void setUp() {
		station = new Stations();
	}

	/**
	 * Test method for StationsValidator where entity class Stations is filled
	 * with valid arguments.
	 */
	@Test
	public void testStationsValidator() {
		station.setStationCode(MOCK_STATION_CODE);
		station.setStationName(MOCK_STATION_NAME);

		errors = new BeanPropertyBindingResult(station, STATION_OBJECT_NAME);
		stationsValidator.validate(station, errors);

		assertFalse(errors.hasErrors());
	}

	/**
	 * Test method for StationsValidator where entity class Stations is filled
	 * with invalid arguments.
	 */
	@Test
	public void testStationsValidatorNotValid() {
		station.setStationCode(WRONG_STATION_CODE_VALUE);
		station.setStationName(WRONG_STATION_NAME_VALUE);

		errors = new BeanPropertyBindingResult(station, STATION_OBJECT_NAME);
		stationsValidator.validate(station, errors);

		assertTrue(errors.hasErrors());
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.StationsValidator#supports(Class)}
	 */
	@Test
	public void testForSupportMethod1() {
		boolean supports = stationsValidator.supports(null);
		assertEquals(false, supports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.StationsValidator#supports(Class)}
	 */
	@Test
	public void testForSupportMethod2() {
		boolean supports = stationsValidator.supports(station.getClass());
		assertEquals(true, supports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.StationsValidator#validateStationCode(String, Errors)}
	 */
	@Test
	public void testValidateStationCodeMethod() {
		errors = new BeanPropertyBindingResult(station, STATION_OBJECT_NAME);
		stationsValidator.validate(station, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(STATION_CODE_VALUE));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.StationsValidator#validateStationCode(String, Errors)}
	 */
	@Test
	public void testWrongStationCodeValue() {
		station.setStationCode(WRONG_STATION_CODE_VALUE);
		errors = new BeanPropertyBindingResult(station, STATION_OBJECT_NAME);
		stationsValidator.validate(station, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(STATION_CODE_VALUE));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.StationsValidator#validateStationCode(String, Errors)}
	 */
	@Test
	public void testStationCodeNullValue() {
		station.setStationCode(null);
		errors = new BeanPropertyBindingResult(station, STATION_OBJECT_NAME);
		stationsValidator.validate(station, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(STATION_CODE_VALUE));
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.StationsValidator#validateStationCode(String, Errors)}
	 */
	@Test
	public void testStationCodeEmptyValue() {
		station.setStationCode("");
		errors = new BeanPropertyBindingResult(station, STATION_OBJECT_NAME);
		stationsValidator.validate(station, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(STATION_CODE_VALUE));
	}


	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.StationsValidator#validateStationName(String, Errors)}
	 */
	@Test
	public void testValidateStationNameMethod() {
		errors = new BeanPropertyBindingResult(station, STATION_OBJECT_NAME);
		stationsValidator.validate(station, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(STATION_NAME_VALUE));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.StationsValidator#validateStationName(String, Errors)}
	 */
	@Test
	public void testWrongStationNameValue() {
		station.setStationName(WRONG_STATION_NAME_VALUE);
		errors = new BeanPropertyBindingResult(station, STATION_OBJECT_NAME);
		stationsValidator.validate(station, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(STATION_NAME_VALUE));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.StationsValidator#validateStationName(String, Errors)}
	 */
	@Test
	public void testStationNameNullValue() {
		station.setStationName(null);
		errors = new BeanPropertyBindingResult(station, STATION_OBJECT_NAME);
		stationsValidator.validate(station, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(STATION_NAME_VALUE));
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.StationsValidator#validateStationName(String, Errors)}
	 */
	@Test
	public void testStationNameEmptyValue() {
		station.setStationName("");
		errors = new BeanPropertyBindingResult(station, STATION_OBJECT_NAME);
		stationsValidator.validate(station, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(STATION_NAME_VALUE));
	}

}
