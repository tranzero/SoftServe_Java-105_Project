package com.ita.edu.softserve.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.ita.edu.softserve.dao.impl.LinesDAOImpl;
import com.ita.edu.softserve.dao.impl.RoutesDAOImpl;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.entity.StationsOnLine;
import com.ita.edu.softserve.manager.impl.LinesManagerImpl;
import com.ita.edu.softserve.manager.impl.RoutesManagerImpl;
import com.ita.edu.softserve.manager.impl.StationOnLineManagerImpl;

/**
 * 
 * @author Lyubomyr
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class TestRoutesValidation {

	private static final String ROUTE_OBJECT_NAME = "route";

	private static final String ROUTE_CODE = "routeCode";
	private static final String LINE_NAME = "lineId";
	private static final String STATION_START = "stationStartId";
	private static final String STATION_END = "stationEndId";

	private static final String ROUTE_CODE_MATCHER = "routeCode.matcher";
	private static final String LINE_NAME_MATCHER = "lineName.matcher";
	private static final String STATION_START_MATCHER = "stationStart.matcher";
	private static final String STATION_END_MATCHER = "stationEnd.matcher";
	private static final String LINE_NOT_EXIST = "line.notExist";
	private static final String STATION_START_NOT_EXIST = "stationStart.notExist";
	private static final String STATION_END_NOT_EXIST = "stationEnd.notExist";
	private static final String SAME_STATIONS = "stations.Same";

	private static final String MOCK_ROUTE_CODE = "000000001";
	private static final String WRONG_ROUTE_CODE = "0000000@A";
	private static final String MOCK_LINE_NAME = "L'viv - Stryy";
	private static final String WRONG_LINE_NAME = "0A";
	private static final String MOCK_STATION_START_CODE = "000000000001";
	private static final String MOCK_STATION_START_NAME = "L'viv";
	private static final String ANOTHER_LINE_NAME = "Pisochne1 - Sknyliv3";
	private static final String WRONG_STATION_START_NAME = "0A";
	private static final String MOCK_STATION_END_CODE = "0000000000017";
	private static final String MOCK_STATION_END_NAME = "Stryy";
	private static final String WRONG_STATION_END_NAME = "0A";

	private Errors errors;
	private Errors errorsValidator;
	private RoutesValidator routesValidator;
	private Routes route;

	@InjectMocks
	private RoutesManagerImpl routesManagerImpl = new RoutesManagerImpl();

	@InjectMocks
	private LinesManagerImpl linesManagerImpl = new LinesManagerImpl();

	@InjectMocks
	private StationOnLineManagerImpl stationOnLineManagerImpl = new StationOnLineManagerImpl();

	@Spy
	private Lines line;
	@Spy
	private Stations stationStart;
	@Spy
	private Stations stationEnd;
	@Spy
	private Set<StationsOnLine> stationsOnLines = new HashSet<StationsOnLine>();

	@Mock
	private LinesDAOImpl mockLinesDaoImpl;

	@Mock
	private RoutesDAOImpl mockRoutesDaoImpl;

	@Before
	public void setUp() {
		routesValidator = new RoutesValidator();
		route = new Routes();
		((RoutesValidator) routesValidator).setLinesManager(linesManagerImpl);
		((RoutesValidator) routesValidator).setRoutesManager(routesManagerImpl);
		((RoutesValidator) routesValidator)
				.setStationOnLineManager(stationOnLineManagerImpl);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#supports(Class)}
	 * .
	 */
	@Test
	public void testSupports() {
		assertFalse(routesValidator.supports(null));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#supports(Class)}
	 * .
	 */
	@Test
	public void testSupportsRoutes() {
		assertTrue(routesValidator.supports(route.getClass()));
	}

	/**
	 * Test method for RoutesValidator where Routes is filled with valid
	 * arguments.
	 */
	@Test
	public final void testRoutesValidator() {

		route.setRouteId(1);
		route.setRouteCode(MOCK_ROUTE_CODE);

		line = new Lines(MOCK_LINE_NAME);

		stationStart = new Stations(MOCK_STATION_START_CODE,
				MOCK_STATION_START_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE, MOCK_STATION_END_NAME);

		route.setLineId(line);
		route.setStationStartId(stationStart);
		route.setStationEndId(stationEnd);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertFalse(errors.hasErrors());
	}

	/**
	 * Test method for RoutesValidator where Routes is filled with not valid
	 * arguments.
	 */
	@Test
	public void testRoutesValidatorNotValid() {
		route.setRouteCode(WRONG_ROUTE_CODE);
		route.setLineId(null);
		route.setStationStartId(null);
		route.setStationEndId(null);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);

		assertTrue(errors.hasErrors());
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateRouteCode(String, Errors)}
	 * .
	 */
	@Test
	public void testNeedsRouteCode() {
		errors = new BeanPropertyBindingResult(route, "route");
		routesValidator.validate(route, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("routeCode"));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateLine(String, Errors)}
	 * .
	 */
	@Test
	public void testNeedsLine() {
		errors = new BeanPropertyBindingResult(route, "route");
		routesValidator.validate(route, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("lineId"));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateRouteCode(String, Errors)}
	 * .
	 */
	@Test
	public void testWrongRouteCode() {
		route.setRouteCode(WRONG_ROUTE_CODE);
		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(ROUTE_CODE));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateLineNameAndRouteCode(String, String, Errors)}
	 * .
	 */
	@Test
	public void testWrongLineAndRouteCodeName() {
		line = new Lines(WRONG_LINE_NAME);
		route.setRouteCode(WRONG_ROUTE_CODE);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError(LINE_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateLineName(String, Errors)}
	 * .
	 */
	@Test
	public final void testRoutesCodeWrongLineValidator() {
		route.setRouteId(0);
		route.setRouteCode(MOCK_ROUTE_CODE);
		line = new Lines(WRONG_LINE_NAME);
		route.setLineId(line);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertTrue(errors.hasErrors());
		assertNull(errors.getFieldError(ROUTE_CODE));
		assertNotNull(errors.getFieldError(LINE_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateRouteWithZeroId(String, Errors)}
	 * .
	 */
	@Test
	public final void testRouteCodeValidator() {
		route.setRouteId(0);
		route.setRouteCode(MOCK_ROUTE_CODE);
		line = new Lines(MOCK_LINE_NAME);
		stationStart = new Stations(MOCK_STATION_START_CODE,
				MOCK_STATION_START_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE, MOCK_STATION_END_NAME);
		route.setLineId(line);
		route.setStationStartId(stationStart);
		route.setStationEndId(stationEnd);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertFalse(errors.hasErrors());
		assertFalse(errors.hasFieldErrors(ROUTE_CODE));
		assertFalse(errors.hasFieldErrors(LINE_NAME));
		assertFalse(errors.hasFieldErrors(STATION_START));
		assertFalse(errors.hasFieldErrors(STATION_END));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateRouteId(String, Errors)}
	 * .
	 */
	@Test
	public void testNullRouteWrongValidator() {
		route.setRouteId(null);
		route.setRouteCode(MOCK_ROUTE_CODE);
		line = new Lines(MOCK_LINE_NAME);
		stationStart = new Stations(MOCK_STATION_START_CODE,
				MOCK_STATION_START_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE, MOCK_STATION_END_NAME);
		route.setLineId(line);
		route.setStationStartId(stationStart);
		route.setStationEndId(stationEnd);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertTrue(errors.hasErrors());
		assertTrue(errors.hasFieldErrors(ROUTE_CODE));
		assertTrue(errors.getFieldError(ROUTE_CODE).getCode()
				.equals(ROUTE_CODE_MATCHER));
		assertFalse(errors.hasFieldErrors(LINE_NAME));
		assertFalse(errors.hasFieldErrors(STATION_START));
		assertFalse(errors.hasFieldErrors(STATION_END));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateRouteCode(String, Errors)}
	 * .
	 */
	@Test
	public void testRouteWithZeroIdWrongValidator() {
		route.setRouteId(0);
		route.setRouteCode(WRONG_ROUTE_CODE);
		line = new Lines(MOCK_LINE_NAME);
		stationStart = new Stations(MOCK_STATION_START_CODE,
				MOCK_STATION_START_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE, MOCK_STATION_END_NAME);
		route.setLineId(line);
		route.setStationStartId(stationStart);
		route.setStationEndId(stationEnd);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertTrue(errors.hasErrors());
		assertTrue(errors.hasFieldErrors(ROUTE_CODE));
		assertTrue(errors.getFieldError(ROUTE_CODE).getCode()
				.equals(ROUTE_CODE_MATCHER));
		assertFalse(errors.hasFieldErrors(LINE_NAME));
		assertFalse(errors.hasFieldErrors(STATION_START));
		assertFalse(errors.hasFieldErrors(STATION_END));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateRouteWithPositiveId}
	 * .
	 */
	@Test
	public final void testRouteWithPositiveIdValidator() {
		route.setRouteId(1);
		route.setRouteCode(WRONG_ROUTE_CODE);
		line = new Lines(MOCK_LINE_NAME);
		stationStart = new Stations(MOCK_STATION_START_CODE,
				MOCK_STATION_START_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE, MOCK_STATION_END_NAME);
		route.setLineId(line);
		route.setStationStartId(stationStart);
		route.setStationEndId(stationEnd);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertTrue(errors.hasErrors());
		assertTrue(errors.hasFieldErrors(ROUTE_CODE));
		assertTrue(errors.getFieldError(ROUTE_CODE).getCode()
				.equals(ROUTE_CODE_MATCHER));
		assertFalse(errors.hasFieldErrors(LINE_NAME));
		assertFalse(errors.hasFieldErrors(STATION_START));
		assertFalse(errors.hasFieldErrors(STATION_END));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateRouteCodeAndLineName(String, Errors)}
	 * .
	 */
	@Test
	public final void testWrongRouteCodeAndLineValidator() {
		route.setRouteId(1);
		route.setRouteCode(MOCK_ROUTE_CODE);
		line = new Lines(WRONG_LINE_NAME);
		stationStart = new Stations(MOCK_STATION_START_CODE,
				MOCK_STATION_START_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE, MOCK_STATION_END_NAME);
		route.setLineId(line);
		route.setStationStartId(stationStart);
		route.setStationEndId(stationEnd);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertTrue(errors.hasErrors());
		assertFalse(errors.hasFieldErrors(ROUTE_CODE));
		assertTrue(errors.hasFieldErrors(LINE_NAME));
		assertTrue(errors.getFieldError(LINE_NAME).getCode()
				.equals(LINE_NAME_MATCHER));
		assertFalse(errors.hasFieldErrors(STATION_START));
		assertFalse(errors.hasFieldErrors(STATION_END));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateStartStation(String, Errors)}
	 * .
	 */
	@Test
	public final void testStationStartWrongValidator() {
		route.setRouteId(1);
		route.setRouteCode(MOCK_ROUTE_CODE);
		line = new Lines(MOCK_LINE_NAME);
		stationStart = new Stations(MOCK_STATION_START_CODE,
				WRONG_STATION_START_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE, MOCK_STATION_END_NAME);
		route.setLineId(line);
		route.setStationStartId(stationStart);
		route.setStationEndId(stationEnd);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertTrue(errors.hasErrors());
		assertFalse(errors.hasFieldErrors(ROUTE_CODE));
		assertFalse(errors.hasFieldErrors(LINE_NAME));
		assertTrue(errors.hasFieldErrors(STATION_START));
		assertTrue(errors.getFieldError(STATION_START).getCode()
				.equals(STATION_START_MATCHER));
		assertFalse(errors.hasFieldErrors(STATION_END));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateEndStation(String, Errors)}
	 * .
	 */
	@Test
	public final void testStationEndWrongValidator() {
		route.setRouteId(1);
		route.setRouteCode(MOCK_ROUTE_CODE);
		line = new Lines(MOCK_LINE_NAME);
		stationStart = new Stations(MOCK_STATION_START_CODE,
				MOCK_STATION_START_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE, WRONG_STATION_END_NAME);
		route.setLineId(line);
		route.setStationStartId(stationStart);
		route.setStationEndId(stationEnd);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertTrue(errors.hasErrors());
		assertFalse(errors.hasFieldErrors(ROUTE_CODE));
		assertFalse(errors.hasFieldErrors(LINE_NAME));
		assertFalse(errors.hasFieldErrors(STATION_START));
		assertTrue(errors.hasFieldErrors(STATION_END));
		assertTrue(errors.getFieldError(STATION_END).getCode()
				.equals(STATION_END_MATCHER));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateStartAndEndStation(String, String, Errors)}
	 * .
	 */
	@Test
	public final void testStartAndEndStationWrongValidator() {
		route.setRouteId(1);
		route.setRouteCode(MOCK_ROUTE_CODE);
		line = new Lines(MOCK_LINE_NAME);
		stationStart = new Stations(MOCK_STATION_START_CODE,
				WRONG_STATION_START_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE, WRONG_STATION_END_NAME);
		route.setLineId(line);
		route.setStationStartId(stationStart);
		route.setStationEndId(stationEnd);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertTrue(errors.hasErrors());
		assertFalse(errors.hasFieldErrors(ROUTE_CODE));
		assertFalse(errors.hasFieldErrors(LINE_NAME));
		assertTrue(errors.hasFieldErrors(STATION_START));
		assertTrue(errors.getFieldError(STATION_START).getCode()
				.equals(STATION_START_MATCHER));
		assertTrue(errors.hasFieldErrors(STATION_END));
		assertTrue(errors.getFieldError(STATION_END).getCode()
				.equals(STATION_END_MATCHER));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateSameStations(String, String, Errors)}
	 * .
	 */
	@Test
	public final void testHaveSameStationsValidator() {
		route.setRouteId(1);
		route.setRouteCode(MOCK_ROUTE_CODE);
		line = new Lines(MOCK_LINE_NAME);
		stationStart = new Stations(MOCK_STATION_START_CODE,
				MOCK_STATION_START_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE,
				MOCK_STATION_START_NAME);
		route.setLineId(line);
		route.setStationStartId(stationStart);
		route.setStationEndId(stationEnd);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertTrue(errors.hasErrors());
		assertFalse(errors.hasFieldErrors(ROUTE_CODE));
		assertFalse(errors.hasFieldErrors(LINE_NAME));
		assertTrue(errors.hasFieldErrors(STATION_START));
		assertTrue(errors.getFieldError(STATION_START).getCode()
				.equals(SAME_STATIONS));
		assertTrue(errors.hasFieldErrors(STATION_END));
		assertTrue(errors.getFieldError(STATION_END).getCode()
				.equals(SAME_STATIONS));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateLine(String, Errors)}
	 * .
	 */
	@Test
	public final void testNullLineValidator() {
		route.setRouteId(null);
		route.setRouteCode(MOCK_ROUTE_CODE);
		stationStart = new Stations(MOCK_STATION_START_CODE,
				MOCK_STATION_START_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE, MOCK_STATION_END_NAME);
		route.setLineId(null);
		route.setStationStartId(stationStart);
		route.setStationEndId(stationEnd);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertTrue(errors.hasErrors());
		assertTrue(errors.hasFieldErrors(ROUTE_CODE));
		assertTrue(errors.getFieldError(ROUTE_CODE).getCode()
				.equals(ROUTE_CODE_MATCHER));
		assertTrue(errors.hasFieldErrors(LINE_NAME));
		assertTrue(errors.getFieldError(LINE_NAME).getCode()
				.equals(LINE_NOT_EXIST));
		assertFalse(errors.hasFieldErrors(STATION_START));
		assertFalse(errors.hasFieldErrors(STATION_END));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateRouteCodeAndLine(String, String, Errors)}
	 * .
	 */
	@Test
	public final void testNullRouteCodeAndLineValidator() {
		route.setRouteId(1);
		route.setRouteCode(null);
		stationStart = new Stations(MOCK_STATION_START_CODE,
				MOCK_STATION_START_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE, MOCK_STATION_END_NAME);
		route.setLineId(null);
		route.setStationStartId(stationStart);
		route.setStationEndId(stationEnd);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertTrue(errors.hasErrors());
		assertTrue(errors.hasFieldErrors(ROUTE_CODE));
		assertTrue(errors.getFieldError(ROUTE_CODE).getCode()
				.equals(ROUTE_CODE_MATCHER));
		assertTrue(errors.hasFieldErrors(LINE_NAME));
		assertTrue(errors.getFieldError(LINE_NAME).getCode()
				.equals(LINE_NOT_EXIST));
		assertFalse(errors.hasFieldErrors(STATION_START));
		assertFalse(errors.hasFieldErrors(STATION_END));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateLineNotExist(String, Errors)}
	 * .
	 */
	@Test
	public final void testLineNotExistValidator() {
		route.setRouteId(1);
		route.setRouteCode(MOCK_ROUTE_CODE);
		stationStart = new Stations(MOCK_STATION_START_CODE,
				MOCK_STATION_START_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE, MOCK_STATION_END_NAME);
		route.setLineId(null);
		route.setStationStartId(stationStart);
		route.setStationEndId(stationEnd);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertTrue(errors.hasErrors());
		assertFalse(errors.hasFieldErrors(ROUTE_CODE));
		assertTrue(errors.hasFieldErrors(LINE_NAME));
		assertTrue(errors.getFieldError(LINE_NAME).getCode()
				.equals(LINE_NOT_EXIST));
		assertFalse(errors.hasFieldErrors(STATION_START));
		assertFalse(errors.hasFieldErrors(STATION_END));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateRouteAndLine(String, String, Errors)}
	 * .
	 */
	@Test
	public final void testWrongRouteAndLineValidator() {
		route.setRouteId(1);
		route.setRouteCode(WRONG_ROUTE_CODE);
		line = new Lines(WRONG_LINE_NAME);
		stationStart = new Stations(MOCK_STATION_START_CODE,
				MOCK_STATION_START_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE, MOCK_STATION_END_NAME);
		route.setLineId(line);
		route.setStationStartId(stationStart);
		route.setStationEndId(stationEnd);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertTrue(errors.hasErrors());
		assertTrue(errors.hasFieldErrors(ROUTE_CODE));
		assertTrue(errors.getFieldError(ROUTE_CODE).getCode()
				.equals(ROUTE_CODE_MATCHER));
		assertTrue(errors.hasFieldErrors(LINE_NAME));
		assertTrue(errors.getFieldError(LINE_NAME).getCode()
				.equals(LINE_NAME_MATCHER));
		assertFalse(errors.hasFieldErrors(STATION_START));
		assertFalse(errors.hasFieldErrors(STATION_END));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateByNullRouteAndLine(String, String, Errors)}
	 * .
	 */
	@Test
	public final void testNullRouteAndLineValidator() {
		route.setRouteId(null);
		route.setRouteCode(null);
		stationStart = new Stations(MOCK_STATION_START_CODE,
				MOCK_STATION_START_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE, MOCK_STATION_END_NAME);
		route.setLineId(null);
		route.setStationStartId(stationStart);
		route.setStationEndId(stationEnd);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertTrue(errors.hasErrors());
		assertTrue(errors.hasFieldErrors(ROUTE_CODE));
		assertTrue(errors.getFieldError(ROUTE_CODE).getCode()
				.equals(ROUTE_CODE_MATCHER));
		assertTrue(errors.hasFieldErrors(LINE_NAME));
		assertTrue(errors.getFieldError(LINE_NAME).getCode()
				.equals(LINE_NOT_EXIST));
		assertFalse(errors.hasFieldErrors(STATION_START));
		assertFalse(errors.hasFieldErrors(STATION_END));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateByNullStationStart(String, Errors)}
	 * .
	 */
	@Test
	public final void testNullStationStartValidator() {
		route.setRouteId(1);
		route.setRouteCode(MOCK_ROUTE_CODE);
		line = new Lines(MOCK_LINE_NAME);
		stationStart = new Stations(MOCK_STATION_START_CODE,
				MOCK_STATION_START_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE,
				MOCK_STATION_START_NAME);
		route.setLineId(line);
		route.setStationStartId(null);
		route.setStationEndId(stationEnd);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertTrue(errors.hasErrors());
		assertFalse(errors.hasFieldErrors(ROUTE_CODE));
		assertFalse(errors.hasFieldErrors(LINE_NAME));
		assertTrue(errors.hasFieldErrors(STATION_START));
		assertTrue(errors.getFieldError(STATION_START).getCode()
				.equals(STATION_START_NOT_EXIST));
		assertFalse(errors.hasFieldErrors(STATION_END));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateByNullStationEnd(String, Errors)}
	 * .
	 */
	@Test
	public final void testNullStationEndValidator() {
		route.setRouteId(1);
		route.setRouteCode(MOCK_ROUTE_CODE);
		line = new Lines(MOCK_LINE_NAME);
		stationStart = new Stations(MOCK_STATION_START_CODE,
				MOCK_STATION_START_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE,
				MOCK_STATION_START_NAME);
		route.setLineId(line);
		route.setStationStartId(stationStart);
		route.setStationEndId(null);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertTrue(errors.hasErrors());
		assertFalse(errors.hasFieldErrors(ROUTE_CODE));
		assertFalse(errors.hasFieldErrors(LINE_NAME));
		assertFalse(errors.hasFieldErrors(STATION_START));
		assertTrue(errors.hasFieldErrors(STATION_END));
		assertTrue(errors.getFieldError(STATION_END).getCode()
				.equals(STATION_END_NOT_EXIST));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateStartAndEndStations(String, String, Errors)}
	 * .
	 */
	@Test
	public final void testNeedsStartAndEndStationsValidator() {
		route.setRouteId(1);
		route.setRouteCode(MOCK_ROUTE_CODE);
		line = new Lines(MOCK_LINE_NAME);
		stationStart = new Stations(MOCK_STATION_START_CODE,
				MOCK_STATION_START_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE, MOCK_STATION_END_NAME);
		route.setLineId(line);
		route.setStationStartId(null);
		route.setStationEndId(null);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertTrue(errors.hasErrors());
		assertFalse(errors.hasFieldErrors(ROUTE_CODE));
		assertFalse(errors.hasFieldErrors(LINE_NAME));
		assertTrue(errors.hasFieldErrors(STATION_START));
		assertTrue(errors.getFieldError(STATION_START).getCode()
				.equals(STATION_START_NOT_EXIST));
		assertTrue(errors.hasFieldErrors(STATION_END));
		assertTrue(errors.getFieldError(STATION_END).getCode()
				.equals(STATION_END_NOT_EXIST));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateRouteAndLineWithPositiveIds(String, String, Errors)}
	 * .
	 */
	@Test
	public final void testRouteAndLineWithPositiveIdValidator() {
		route.setRouteId(1);
		route.setRouteCode(MOCK_ROUTE_CODE);
		line = new Lines(MOCK_LINE_NAME);
		line.setLineId(1);
		stationStart = new Stations(MOCK_STATION_START_CODE,
				MOCK_STATION_START_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE, MOCK_STATION_END_NAME);
		route.setLineId(line);
		route.setStationStartId(stationStart);
		route.setStationEndId(stationEnd);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertFalse(errors.hasErrors());
		assertFalse(errors.hasFieldErrors(ROUTE_CODE));
		assertFalse(errors.hasFieldErrors(LINE_NAME));
		assertFalse(errors.hasFieldErrors(STATION_START));
		assertFalse(errors.hasFieldErrors(STATION_END));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateSameStations(String, String, Errors)}
	 * .
	 */
	@Test
	public final void testSameStationsValidator() {
		route.setRouteId(1);
		route.setRouteCode(MOCK_ROUTE_CODE);
		line = new Lines(MOCK_LINE_NAME);
		line.setLineId(1);
		stationStart = new Stations(MOCK_STATION_START_CODE,
				MOCK_STATION_END_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE, MOCK_STATION_END_NAME);
		route.setLineId(line);
		route.setStationStartId(stationStart);
		route.setStationEndId(stationEnd);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertTrue(errors.hasErrors());
		assertFalse(errors.hasFieldErrors(ROUTE_CODE));
		assertFalse(errors.hasFieldErrors(LINE_NAME));
		assertTrue(errors.hasFieldErrors(STATION_START));
		assertTrue(errors.getFieldError(STATION_START).getCode()
				.equals(SAME_STATIONS));
		assertTrue(errors.getFieldError(STATION_END).getCode()
				.equals(SAME_STATIONS));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateSameStationsOnAnotherLine(String, Errors)}
	 * .
	 */
	@Test
	public final void testSameStationsOnAnotherLineValidator() {
		route.setRouteId(1);
		route.setRouteCode(MOCK_ROUTE_CODE);
		line = new Lines(ANOTHER_LINE_NAME);
		line.setLineId(1);
		stationStart = new Stations(MOCK_STATION_START_CODE,
				MOCK_STATION_END_NAME);
		stationEnd = new Stations(MOCK_STATION_END_CODE, MOCK_STATION_END_NAME);
		route.setLineId(line);
		route.setStationStartId(stationStart);
		route.setStationEndId(stationEnd);

		errors = new BeanPropertyBindingResult(route, ROUTE_OBJECT_NAME);
		routesValidator.validate(route, errors);
		assertTrue(errors.hasErrors());
		assertFalse(errors.hasFieldErrors(ROUTE_CODE));
		assertFalse(errors.hasFieldErrors(LINE_NAME));
		assertTrue(errors.hasFieldErrors(STATION_START));
		assertTrue(errors.getFieldError(STATION_START).getCode()
				.equals(SAME_STATIONS));
		assertTrue(errors.getFieldError(STATION_END).getCode()
				.equals(SAME_STATIONS));

	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateRouteCode(String, Errors)}
	 * .
	 */
	@Test
	public final void testWrongRouteCodeValidator() {
		assertFalse(routesValidator.validateRouteCode(WRONG_ROUTE_CODE));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateLineName(String, Errors)}
	 * .
	 */
	@Test
	public final void testWrongLineNameValidator() {
		assertFalse(routesValidator.validateLineName(WRONG_LINE_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateStationStart(String, Errors)}
	 * .
	 */
	@Test
	public final void testWrongStationStartValidator() {
		assertFalse(routesValidator
				.validateStationStartName(WRONG_STATION_START_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateStationEnd(String, Errors)}
	 * .
	 */
	@Test
	public final void testWrongStationEndValidator() {
		assertFalse(routesValidator
				.validateStationEndName(WRONG_STATION_END_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateStartStationByLine(String, Errors)}
	 * .
	 */
	@Test
	public final void testStartStationByLineValidator() {
		errorsValidator = new BeanPropertyBindingResult(route,
				ROUTE_OBJECT_NAME);

		routesValidator.validateStartStationByLine(MOCK_STATION_START_NAME,
				MOCK_LINE_NAME, errorsValidator);
		assertFalse(errorsValidator.hasFieldErrors(LINE_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateStartStationByLine(String, Errors)}
	 * .
	 */
	@Test
	public final void testWrongStartStationByLineValidator() {
		errorsValidator = new BeanPropertyBindingResult(route,
				ROUTE_OBJECT_NAME);

		routesValidator.validateStartStationByLine(WRONG_STATION_START_NAME,
				MOCK_LINE_NAME, errorsValidator);
		assertTrue(errorsValidator.hasFieldErrors(STATION_START));
		assertTrue(errorsValidator.getFieldError(STATION_START).getCode()
				.equals(STATION_START_MATCHER));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateLineOnStationByLine(String, Errors)}
	 * .
	 */
	@Test
	public final void testWrongLineOnStationByLineValidator() {
		errorsValidator = new BeanPropertyBindingResult(route,
				ROUTE_OBJECT_NAME);

		errorsValidator.rejectValue(LINE_NAME, LINE_NAME_MATCHER);
		routesValidator.validateStartStationByLine(MOCK_STATION_START_NAME,
				WRONG_LINE_NAME, errorsValidator);
		assertFalse(errorsValidator.hasFieldErrors(STATION_START));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateStationByLine(String, Errors)}
	 * .
	 */
	@Test
	public final void testWrongStationByLineValidator() {
		errorsValidator = new BeanPropertyBindingResult(route,
				ROUTE_OBJECT_NAME);

		errorsValidator.rejectValue(STATION_START, STATION_START_MATCHER);
		routesValidator.validateStartStationByLine(WRONG_STATION_START_NAME,
				MOCK_LINE_NAME, errorsValidator);
		assertFalse(errorsValidator.hasFieldErrors(LINE_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateEndStationByLine(String, Errors)}
	 * .
	 */
	@Test
	public final void testEndStationByLineValidator() {
		errorsValidator = new BeanPropertyBindingResult(route,
				ROUTE_OBJECT_NAME);

		routesValidator.validateEndStationByLine(MOCK_STATION_END_NAME,
				MOCK_LINE_NAME, errorsValidator);
		assertFalse(errorsValidator.hasFieldErrors(LINE_NAME));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateStationEndByLine(String, Errors)}
	 * .
	 */
	@Test
	public final void testWrongStationEndByLineValidator() {
		errorsValidator = new BeanPropertyBindingResult(route,
				ROUTE_OBJECT_NAME);

		routesValidator.validateEndStationByLine(WRONG_STATION_END_NAME,
				MOCK_LINE_NAME, errorsValidator);
		assertTrue(errorsValidator.hasFieldErrors(STATION_END));
		assertTrue(errorsValidator.getFieldError(STATION_END).getCode()
				.equals(STATION_END_MATCHER));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateEndStationByLine(String, Errors)}
	 * .
	 */
	@Test
	public final void testNeedsEndStationByLineValidator() {
		errorsValidator = new BeanPropertyBindingResult(route,
				ROUTE_OBJECT_NAME);

		errorsValidator.rejectValue(LINE_NAME, LINE_NAME_MATCHER);
		routesValidator.validateEndStationByLine(MOCK_STATION_END_NAME,
				WRONG_LINE_NAME, errorsValidator);
		assertFalse(errorsValidator.hasFieldErrors(STATION_END));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.validation.RoutesValidator#validateEndStationByLine(String, Errors)}
	 * .
	 */
	@Test
	public final void testWrongEndStationByLineValidator() {
		errorsValidator = new BeanPropertyBindingResult(route,
				ROUTE_OBJECT_NAME);

		errorsValidator.rejectValue(STATION_END, STATION_END_MATCHER);
		routesValidator.validateEndStationByLine(WRONG_STATION_END_NAME,
				MOCK_LINE_NAME, errorsValidator);
		assertFalse(errorsValidator.hasFieldErrors(LINE_NAME));
	}
}
