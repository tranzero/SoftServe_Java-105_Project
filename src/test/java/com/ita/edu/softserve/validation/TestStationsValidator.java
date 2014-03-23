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

import com.ita.edu.softserve.entity.Stations;

/**
 * @author admin
 * 
 */
public class TestStationsValidator {

	private static final String STATION_OBJECT_NAME = "station";

	private static final String STATION_CODE_VALUE = "stationCode";

	private static final String WRONG_STATION_CODE_VALUE = "000000@A";

	private static final String STATION_NAME_VALUE = "stationName";

	private static final String WRONG_STATION_NAME_VALUE = "L'viv + @@@";

	private StationsValidator stationsValidator;

	private Stations station;

	private Errors errors;

	@Before
	public void setUp() {
		stationsValidator = new StationsValidator();
		station = new Stations();
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

}
