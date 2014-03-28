package com.ita.edu.softserve.manager.impl;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static com.ita.edu.softserve.utils.ParseUtil.parseStringToTime;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Iterables;
import com.ita.edu.softserve.dao.LinesDAO;
import com.ita.edu.softserve.dao.StationsOnLineDAO;
import com.ita.edu.softserve.dao.impl.LinesDAOImpl;
import com.ita.edu.softserve.dao.impl.RoutesDAOImpl;
import com.ita.edu.softserve.manager.RoutesManager;
import com.ita.edu.softserve.manager.UserNameService;
import com.ita.edu.softserve.manager.impl.RoutesManagerImpl;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.StationsOnLine;

/**
 * Class under test {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl}
 * 
 * @author Lyubomyr
 */
@RunWith(MockitoJUnitRunner.class)
public class RoutesManagerImplTests {

	@Mock
	private RoutesDAOImpl mockRoutesDaoImpl;
	@Mock
	private StationsOnLineDAO mockStationOnLineDao;
	@Mock
	private RoutesManager routesManager;
	@Mock
	private UserNameService userName;

	@InjectMocks
	private LinesDAO linesDao = new LinesDAOImpl();
	@InjectMocks
	private RoutesManagerImpl routesManagerImpl = new RoutesManagerImpl();

	private Routes routes;
	private Lines lines;
	private StationsOnLine stationOnLine;
	private List<String> stationNameList;
	private List<String> lineNameList;
	private String stations;
	private Integer routesIdMock = 20;
	private Integer illegalRouteId = -1;
	private Integer lineIdMock = 20;
	private Integer stationIdMock = 30;

	private static final String mockLineName = "L'viv - Stryy";
	private static final String illegalLineName = "@#";
	private static final String illegalRouteCode = "@#";
	private static final String mockStationName = "Stryy";
	private static final String illegalStationName = "@#";

	@Before
	public final void setUp() throws NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {

		when(userName.getLoggedUsername()).thenReturn("lyubomyr");
		routes = mock(Routes.class);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#findRoutesById(String)}
	 * .
	 */
	@Test()
	public void testFindRoutesById() {
		when(mockRoutesDaoImpl.findById(routesIdMock)).thenReturn(routes);
		Routes actualRoute = routesManagerImpl.findRoutesById(routesIdMock);

		assertEquals(routes, actualRoute);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getInstance() }
	 * .
	 */
	@Test
	public final void testGetInstance() {
		RoutesManager actualRoutesManager = RoutesManagerImpl.getInstance();

		assertThat(routesManagerImpl, not(actualRoutesManager));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#findRoutesByIdForNull(Integer)}
	 * .
	 */
	@Test
	public final void testFindRoutesByIdForNull() {
		when(mockRoutesDaoImpl.findById(Mockito.anyInt())).thenReturn(null);
		Routes expectedRoute = routesManagerImpl.findRoutesById(routesIdMock);

		assertNull(expectedRoute);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#findRoutesById(Integer)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testFindRoutesByIdException() {
		when(mockRoutesDaoImpl.findById(illegalRouteId)).thenThrow(
				new IllegalArgumentException());

		routesManagerImpl.findRoutesById(illegalRouteId);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#findByStationIdAndLineId(Integer, Integer)}
	 * .
	 */
	@Test()
	public void testFindByStationIdAndLineId() {
		when(
				mockStationOnLineDao.findByStationIdAndLineId(lineIdMock,
						stationIdMock)).thenReturn(stationOnLine);

		StationsOnLine actualStationsOnLine = routesManagerImpl
				.findByStationIdAndLineId(lineIdMock, stationIdMock);
		assertEquals(stationOnLine, actualStationsOnLine);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#findByStationIdAndLineId(Integer, Integer)}
	 * .
	 */
	@Test
	public final void testFindByStationIdAndLineIdForNull() {
		when(
				mockStationOnLineDao.findByStationIdAndLineId(Mockito.anyInt(),
						Mockito.anyInt())).thenReturn(null);

		StationsOnLine expectedStationsOnLine = routesManagerImpl
				.findByStationIdAndLineId(lineIdMock, stationIdMock);

		assertNull(expectedStationsOnLine);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#findByCode(String)}
	 * .
	 */
	@Test()
	public void testFindRoutesByCode() {
		when(mockRoutesDaoImpl.findByCode("0000000001")).thenReturn(routes);
		Routes actual = routesManagerImpl.findByCode("0000000001");

		assertEquals(routes, actual);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#findByCode(String)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testFindRoutesByCodeException() {
		when(mockRoutesDaoImpl.findByCode("#00000001")).thenThrow(
				new IllegalArgumentException());

		routesManagerImpl.findByCode("#00000001");
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#createRoute(Route)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSaveRoutesException() {
		doThrow(new IllegalArgumentException()).when(mockRoutesDaoImpl).save(
				routes);

		routesManagerImpl.createRoute(routes);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#createRoute(Route)}
	 * .
	 */
	@Test()
	public void testSaveRoutes() {
		doNothing().when(mockRoutesDaoImpl).save(routes);

		routesManagerImpl.createRoute(routes);

		verify(mockRoutesDaoImpl, times(1)).save(routes);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#removeRouteById(Integer)}
	 * .
	 */
	@Test()
	public void testRemoveRouteById() {
		when(mockRoutesDaoImpl.findById(routesIdMock)).thenReturn(routes);

		routesManagerImpl.removeRouteById(routesIdMock);

		verify(mockRoutesDaoImpl).findById(routesIdMock);
		verify(mockRoutesDaoImpl).remove(routes);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#removeRouteById(Integer)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveRouteByIdException() {
		when(mockRoutesDaoImpl.findById(routesIdMock)).thenReturn(routes);

		doThrow(new IllegalArgumentException()).when(mockRoutesDaoImpl).remove(
				eq(routes));

		routesManagerImpl.removeRouteById(routesIdMock);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#updateRoutes(Routes)}
	 * .
	 */
	@Test()
	public void testUpdateRoutes() {
		routesManagerImpl.updateRoute(routes);

		verify(mockRoutesDaoImpl).update(routes);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#updateRoute(Routes)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateRoutesException() {
		doThrow(new IllegalArgumentException()).when(mockRoutesDaoImpl).update(
				routes);

		routesManagerImpl.updateRoute(routes);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#update(Routes)}
	 * .
	 */
	@Test
	public final void testUpdateRoutesForNull() {
		when(mockRoutesDaoImpl.update(routes)).thenReturn(null);

		Routes expectedRoute = routesManagerImpl.findByCode(illegalRouteCode);

		verify(mockRoutesDaoImpl, times(1)).findByCode(illegalRouteCode);
		assertNull(expectedRoute);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getAllRoutes()}
	 * .
	 */
	@Test
	public final void testFindAllRoutesIsEmpty() {
		List<Routes> listOfRoutes = new ArrayList<Routes>();
		Routes route = mock(Routes.class);
		listOfRoutes.add(route);

		when(mockRoutesDaoImpl.getAllEntities()).thenReturn(listOfRoutes);
		List<Routes> routesList = routesManagerImpl.getAllRoutes();

		assertFalse(routesList.isEmpty());
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getAllRoutes()}
	 * .
	 */
	@Test
	public final void testFindAllRoutesEmptyList() {
		List<Routes> listOfRoutes = new ArrayList<Routes>();

		when(mockRoutesDaoImpl.getAllEntities()).thenReturn(listOfRoutes);
		List<Routes> routesList = routesManagerImpl.getAllRoutes();

		assertTrue((listOfRoutes.size() == routesList.size())
				&& (listOfRoutes.containsAll(routesList)));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getAllRoutes()}
	 * .
	 */
	@Test
	public final void testGetAllRoutesEmptyList() {
		List<Routes> expected = new ArrayList<Routes>();

		when(mockRoutesDaoImpl.getAllEntities()).thenReturn(expected);
		List<Routes> actual = routesManagerImpl.getAllRoutes();

		verify(mockRoutesDaoImpl, times(1)).getAllEntities();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getAllRoutes()}
	 * .
	 */
	@Test
	public final void testGetAllRoutesForNull() {
		when(mockRoutesDaoImpl.getAllEntities()).thenReturn(null);

		List<Routes> actual = routesManagerImpl.getAllRoutes();

		verify(mockRoutesDaoImpl, times(1)).getAllEntities();

		assertNull(actual);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getAllRoutes()}
	 * .
	 */
	@Test(expected = RuntimeException.class)
	public final void testGetAllRoutesShouldThrowException() {

		when(mockRoutesDaoImpl.getAllEntities()).thenThrow(
				new RuntimeException());

		routesManagerImpl.getAllRoutes();
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#findByName(String)}
	 * .
	 */
	@Test()
	public void testFindByLineName() {
		when(mockRoutesDaoImpl.findByName(mockLineName)).thenReturn(lines);
		Lines actual = routesManagerImpl.findByLineName(mockLineName);

		verify(mockRoutesDaoImpl, times(1)).findByName(mockLineName);
		assertEquals(lines, actual);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#findByName(String)
	 * )} .
	 */
	@Test
	public final void testFindByLineNameForNull() {
		when(mockRoutesDaoImpl.findByName(illegalLineName)).thenReturn(null);

		Lines expectedLine = routesManagerImpl.findByLineName(illegalLineName);

		verify(mockRoutesDaoImpl, times(1)).findByName(illegalLineName);
		assertNull(expectedLine);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#findByName(String)
	 * )} .
	 */
	@Test(expected = RuntimeException.class)
	public final void tesFindByLineNameException() {
		when(mockRoutesDaoImpl.findByName(illegalLineName)).thenThrow(
				new RuntimeException());

		routesManagerImpl.findByLineName(illegalLineName);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getStationNameByLineNameCriteria()}
	 * .
	 */
	@Test()
	public void testGetStationNameByLineName() {
		when(
				mockRoutesDaoImpl.getStationNameByLineNameCriteria(
						mockLineName, mockStationName)).thenReturn(stations);
		String actual = routesManagerImpl.getStationNameByLineNameCriteria(
				mockLineName, mockStationName);

		verify(mockRoutesDaoImpl, times(1)).getStationNameByLineNameCriteria(
				mockLineName, mockStationName);
		assertEquals(lines, actual);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getStationNameByLineNameCriteria()}
	 * .
	 */
	@Test
	public final void testGetStationNameByLineNameForNull() {
		when(
				mockRoutesDaoImpl.getStationNameByLineNameCriteria(
						illegalLineName, illegalStationName)).thenReturn(null);

		String expectedStation = routesManagerImpl
				.getStationNameByLineNameCriteria(illegalLineName,
						illegalStationName);

		verify(mockRoutesDaoImpl, times(1)).getStationNameByLineNameCriteria(
				illegalLineName, illegalStationName);
		assertNull(expectedStation);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getStationNameByLineNameCriteria()}
	 * .
	 */
	@Test(expected = RuntimeException.class)
	public final void testGetStationNameByLineNameException() {
		when(
				mockRoutesDaoImpl.getStationNameByLineNameCriteria(
						illegalLineName, illegalStationName)).thenThrow(
				new RuntimeException());

		routesManagerImpl.getStationNameByLineNameCriteria(illegalLineName,
				illegalStationName);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getStationNameListCriteria()}
	 * .
	 */
	@Test()
	public void testGetStationNameList() {
		when(
				mockRoutesDaoImpl.getStationNameListCriteria(mockStationName
						+ "%")).thenReturn(stationNameList);

		List<String> actual = routesManagerImpl
				.getStationNameListCriteria(mockStationName);

		verify(mockRoutesDaoImpl, times(1)).getStationNameListCriteria(
				mockStationName + "%");
		assertEquals(stationNameList, actual);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getStationNameListCriteria()}
	 * .
	 */
	@Test(expected = RuntimeException.class)
	public final void testGetStationNameListException() {
		when(
				mockRoutesDaoImpl.getStationNameListCriteria(illegalStationName
						+ "%")).thenThrow(new RuntimeException());

		routesManagerImpl.getStationNameListCriteria(illegalStationName);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getLineNameListCriteria()}
	 * .
	 */
	@Test()
	public void testGetLineNameList() {
		when(mockRoutesDaoImpl.getLineNameListCriteria(mockLineName + "%"))
				.thenReturn(lineNameList);

		List<String> actual = routesManagerImpl
				.getLineNameListCriteria(mockLineName);

		verify(mockRoutesDaoImpl, times(1)).getLineNameListCriteria(
				mockLineName + "%");
		assertEquals(lineNameList, actual);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getLineNameListCriteria()}
	 * .
	 */
	@Test(expected = RuntimeException.class)
	public final void testGetLineNameListException() {
		when(mockRoutesDaoImpl.getLineNameListCriteria(illegalLineName + "%"))
				.thenThrow(new RuntimeException());

		routesManagerImpl.getLineNameListCriteria(illegalLineName);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#findRoutersListByStationNameArriving()}
	 * .
	 */
	@Test
	public final void getRoutersListByStationNameArrivingTest() {
		String stationNameArrival = "Sknyliv";
		Time timeArrivalMin = parseStringToTime("00:05:00");
		Time timeArrivalMax = parseStringToTime("23:55:00");

		List<RouteTrip> listOfRoutes = new ArrayList<RouteTrip>();
		RouteTrip routeTrip = mock(RouteTrip.class);
		listOfRoutes.add(routeTrip);

		List<RouteTrip> expectedRouteTrips = Collections
				.singletonList(routeTrip);

		when(
				mockRoutesDaoImpl.findRoutersListByStationNameArriving(
						stationNameArrival, timeArrivalMin, timeArrivalMax))
				.thenReturn(listOfRoutes);

		List<RouteTrip> actualRouteTrips = routesManagerImpl
				.findRoutersListByStationNameArriving(stationNameArrival,
						timeArrivalMin, timeArrivalMax);

		assertTrue(Iterables
				.elementsEqual(expectedRouteTrips, actualRouteTrips));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#findRoutersListByStationNameArriving()}
	 * .
	 */
	@Test
	public final void getRoutersListByStationNameArrivingIfEmptyListTest() {
		String stationNameArrival = "Pisochne";
		Time timeArrivalMin = parseStringToTime("00:05:00");
		Time timeArrivalMax = parseStringToTime("23:55:00");

		List<RouteTrip> listOfRouteTrip = new ArrayList<RouteTrip>();
		List<RouteTrip> expectedRouteTrips = new ArrayList<RouteTrip>();

		when(
				mockRoutesDaoImpl.findRoutersListByStationNameArriving(
						stationNameArrival, timeArrivalMin, timeArrivalMax))
				.thenReturn(listOfRouteTrip);
		List<RouteTrip> actualRouteTrips = routesManagerImpl
				.findRoutersListByStationNameArriving(stationNameArrival,
						timeArrivalMin, timeArrivalMax);

		assertTrue(Iterables
				.elementsEqual(expectedRouteTrips, actualRouteTrips));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#findRoutersListByStationNameArriving()}
	 * .
	 */
	@Test(expected = RuntimeException.class)
	public final void getRoutersListByStationNameArrivingIfExceptionTest() {
		String stationNameArrival = "Pisochne";
		Time timeArrivalMin = parseStringToTime("00:05:00");
		Time timeArrivalMax = parseStringToTime("23:55:00");

		List<RouteTrip> listOfRoutes = new ArrayList<RouteTrip>();
		RouteTrip routeTrip = mock(RouteTrip.class);
		listOfRoutes.add(routeTrip);

		when(
				mockRoutesDaoImpl.findRoutersListByStationNameArriving(
						stationNameArrival, timeArrivalMin, timeArrivalMax))
				.thenThrow(new RuntimeException());

		routesManagerImpl.findRoutersListByStationNameArriving(
				stationNameArrival, timeArrivalMin, timeArrivalMax);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getRoutersListByStNameArrivingForPage()}
	 * .
	 */
	@Test
	public final void getRoutersListByStationNameArrivingForLimitTest() {
		String stationNameArrival = "Sknyliv";
		Time timeArrivalMin = parseStringToTime("00:05:00");
		Time timeArrivalMax = parseStringToTime("23:55:00");

		int firstElement = 0;
		int count = 10;

		RouteTrip routeTrip = mock(RouteTrip.class);
		List<RouteTrip> expectedRouteTrips = Collections
				.singletonList(routeTrip);

		when(
				mockRoutesDaoImpl.getRoutersListByStNameArrivingForLimits(
						stationNameArrival, timeArrivalMin, timeArrivalMax,
						firstElement, count)).thenReturn(expectedRouteTrips);

		List<RouteTrip> actualRouteTrips = routesManagerImpl
				.getRoutersListByStNameArrivingForPage(stationNameArrival,
						timeArrivalMin, timeArrivalMax, firstElement, count);

		assertFalse(Iterables.elementsEqual(expectedRouteTrips,
				actualRouteTrips));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getRoutersListByStNameArrivingForPage()}
	 * .
	 */
	@Test
	public final void getRoutersListByStationNameArrivingForLimitIfEmptyListTest() {
		String stationNameArrival = "Sknyliv";
		Time timeArrivalMin = parseStringToTime("00:05:00");
		Time timeArrivalMax = parseStringToTime("23:55:00");
		int firstElement = 0;
		int count = 10;

		List<RouteTrip> expectedRouteTrips = new ArrayList<RouteTrip>();

		when(
				mockRoutesDaoImpl.getRoutersListByStNameArrivingForLimits(
						stationNameArrival, timeArrivalMin, timeArrivalMax,
						firstElement, count)).thenReturn(expectedRouteTrips);

		List<RouteTrip> actualRouteTrips = routesManagerImpl
				.getRoutersListByStNameArrivingForPage(stationNameArrival,
						timeArrivalMin, timeArrivalMax, firstElement, count);
		;

		assertTrue(Iterables
				.elementsEqual(expectedRouteTrips, actualRouteTrips));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getRoutersListByStationNameArrivingCount()}
	 * .
	 */
	@Test
	public final void getRoutersListByStationNameArrivingCountTest() {
		String stationNameArrival = "Sknyliv";
		Time timeArrivalMin = parseStringToTime("00:05:00");
		Time timeArrivalMax = parseStringToTime("23:55:00");

		long expectedRoutesTripCount = 12;

		when(
				mockRoutesDaoImpl.getRoutersListByStationNameArrivingCount(
						stationNameArrival, timeArrivalMin, timeArrivalMax))
				.thenReturn(expectedRoutesTripCount);

		long actualRoutesTripCount = routesManagerImpl
				.getRoutersListByStationNameArrivingCount(stationNameArrival,
						timeArrivalMin, timeArrivalMax);

		assertEquals(expectedRoutesTripCount, actualRoutesTripCount);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#findRoutersListByStationNameDeparting()}
	 * .
	 */
	@Test
	public final void getRoutersListByStationNameDepartingTest() {
		String stationNameDeparting = "Sknyliv";
		Time timeDepartingMin = parseStringToTime("00:05:00");
		Time timeDepartingMax = parseStringToTime("23:55:00");

		List<RouteTrip> listOfRoutes = new ArrayList<RouteTrip>();
		RouteTrip routeTrip = mock(RouteTrip.class);
		listOfRoutes.add(routeTrip);

		List<RouteTrip> expectedRouteTrips = Collections
				.singletonList(routeTrip);

		when(
				mockRoutesDaoImpl.findRoutersListByStationNameDeparting(
						stationNameDeparting, timeDepartingMin,
						timeDepartingMax)).thenReturn(listOfRoutes);

		List<RouteTrip> actualRouteTrips = routesManagerImpl
				.findRoutersListByStationNameDeparting(stationNameDeparting,
						timeDepartingMin, timeDepartingMax);

		assertTrue(Iterables
				.elementsEqual(expectedRouteTrips, actualRouteTrips));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#findRoutersListByStationNameDeparting()}
	 * .
	 */
	@Test
	public final void getRoutersListByStationNameDepartingIfEmptyListTest() {
		String stationNameDeparting = "Pisochne";
		Time timeDepartingMin = parseStringToTime("00:05:00");
		Time timeDepartingMax = parseStringToTime("23:55:00");

		List<RouteTrip> listOfRouteTrip = new ArrayList<RouteTrip>();
		List<RouteTrip> expectedRouteTrips = new ArrayList<RouteTrip>();

		when(
				mockRoutesDaoImpl.findRoutersListByStationNameDeparting(
						stationNameDeparting, timeDepartingMin,
						timeDepartingMax)).thenReturn(listOfRouteTrip);
		List<RouteTrip> actualRouteTrips = routesManagerImpl
				.findRoutersListByStationNameDeparting(stationNameDeparting,
						timeDepartingMin, timeDepartingMax);

		assertTrue(Iterables
				.elementsEqual(expectedRouteTrips, actualRouteTrips));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#findRoutersListByStationNameDeparting()}
	 * .
	 */
	@Test(expected = RuntimeException.class)
	public final void getRoutersListByStationNameDepartingIfExceptionTest() {
		String stationNameDeparting = "Pisochne";
		Time timeDepartingMin = parseStringToTime("00:05:00");
		Time timeDepartingMax = parseStringToTime("23:55:00");

		List<RouteTrip> listOfRoutes = new ArrayList<RouteTrip>();
		RouteTrip routeTrip = mock(RouteTrip.class);
		listOfRoutes.add(routeTrip);

		when(
				mockRoutesDaoImpl.findRoutersListByStationNameDeparting(
						stationNameDeparting, timeDepartingMin,
						timeDepartingMax)).thenThrow(new RuntimeException());

		routesManagerImpl.findRoutersListByStationNameDeparting(
				stationNameDeparting, timeDepartingMin, timeDepartingMax);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getRoutersListByStNameDepartingForPage()}
	 * .
	 */
	@Test
	public final void getRoutersListByStationNameDepartingForLimitTest() {
		String stationNameDeparting = "Sknyliv";
		Time timeDepartingMin = parseStringToTime("00:05:00");
		Time timeDepartingMax = parseStringToTime("23:55:00");

		int firstElement = 0;
		int count = 10;

		RouteTrip routeTrip = mock(RouteTrip.class);
		List<RouteTrip> expectedRouteTrips = Collections
				.singletonList(routeTrip);

		when(
				mockRoutesDaoImpl.getRoutersListByStNameDepartingForLimits(
						stationNameDeparting, timeDepartingMin,
						timeDepartingMax, firstElement, count)).thenReturn(
				expectedRouteTrips);

		List<RouteTrip> actualRouteTrips = routesManagerImpl
				.getRoutersListByStNameDepartingForPage(stationNameDeparting,
						timeDepartingMin, timeDepartingMax, firstElement, count);

		assertFalse(Iterables.elementsEqual(expectedRouteTrips,
				actualRouteTrips));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getRoutersListByStNameDepartingForPage()}
	 * .
	 */
	@Test
	public final void getRoutersListByStationNameDepartingForLimitIfEmptyListTest() {
		String stationNameDeparting = "Sknyliv";
		Time timeDepartingMin = parseStringToTime("00:05:00");
		Time timeDepartingMax = parseStringToTime("23:55:00");
		int firstElement = 0;
		int count = 10;

		List<RouteTrip> expectedRouteTrips = new ArrayList<RouteTrip>();

		when(
				mockRoutesDaoImpl.getRoutersListByStNameDepartingForLimits(
						stationNameDeparting, timeDepartingMin,
						timeDepartingMax, firstElement, count)).thenReturn(
				expectedRouteTrips);

		List<RouteTrip> actualRouteTrips = routesManagerImpl
				.getRoutersListByStNameDepartingForPage(stationNameDeparting,
						timeDepartingMin, timeDepartingMax, firstElement, count);
		;

		assertTrue(Iterables
				.elementsEqual(expectedRouteTrips, actualRouteTrips));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getRoutersListByStationNameDepartingCount()}
	 * .
	 */
	@Test
	public final void getRoutersListByStationNameDepartingCountTest() {
		String stationNameDeparting = "Sknyliv";
		Time timeDepartingMin = parseStringToTime("00:05:00");
		Time timeDepartingMax = parseStringToTime("23:55:00");

		long expectedRoutesTripCount = 12;

		when(
				mockRoutesDaoImpl.getRoutersListByStationNameDepartingCount(
						stationNameDeparting, timeDepartingMin,
						timeDepartingMax)).thenReturn(expectedRoutesTripCount);

		long actualRoutesTripCount = routesManagerImpl
				.getRoutersListByStationNameDepartingCount(
						stationNameDeparting, timeDepartingMin,
						timeDepartingMax);

		assertEquals(expectedRoutesTripCount, actualRoutesTripCount);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getRoutesForPage()}
	 * .
	 */
	@Test
	public void testGetRoutesWithPaging() {
		List<Routes> expectedListOfRoutes = new ArrayList<Routes>();

		when(mockRoutesDaoImpl.getRoutesForLimits(1, 10, "routeCode", "ASC"))
				.thenReturn(expectedListOfRoutes);

		List<Routes> actualListOfRoutes = routesManagerImpl.getRoutesForPage(1,
				10, "routeCode", "ASC");

		assertEquals(expectedListOfRoutes, actualListOfRoutes);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl#getRoutesListCount()}
	 * .
	 */
	@Test
	public final void getRoutesListCountTest() {

		long expectedRoutesTripCount = 1;

		when(mockRoutesDaoImpl.getRoutesListCount()).thenReturn(
				expectedRoutesTripCount);

		long actualRoutesTripCount = routesManagerImpl.getRoutesListCount();

		assertEquals(expectedRoutesTripCount, actualRoutesTripCount);
	}
}