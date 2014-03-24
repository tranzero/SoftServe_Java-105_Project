package com.ita.edu.softserve.manager.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static com.ita.edu.softserve.utils.ParseUtil.parseStringToTime;

import java.lang.reflect.Field;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Iterables;
import com.ita.edu.softserve.dao.impl.ResponsesDAOImpl;
import com.ita.edu.softserve.dao.impl.RoutesDAOImpl;
import com.ita.edu.softserve.exception.RoutesManagerException;
import com.ita.edu.softserve.manager.impl.RoutesManagerImpl;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.entity.Transports;

/**
 * Class under test {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl}
 * 
 * @author Lyubomyr
 */
@RunWith(MockitoJUnitRunner.class)
public class RoutesManagerImplTests {

	/**
	 * Mock object.
	 */
	@Mock
	private RoutesDAOImpl mockRoutesDaoImpl;
	/**
	 * RoutesManagerImpl
	 */
	@InjectMocks
	private RoutesManagerImpl routesManagerImpl = new RoutesManagerImpl();

	private Integer routesIdMock = 20;
	
	private Integer illegalRouteId = -1;

	private Integer lineId = 20;

	private Integer stationStartId = 30;

	private Integer endStartId = 40;

	private Routes routes;

	/**
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@Before
	public final void setUp() throws NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {

		routes = mock(Routes.class);

	/*	mockRoutesDaoImpl = mock(RoutesDAOImpl.class);

		routesManagerImpl = new RoutesManagerImpl();*/
	/*	Field field = routesManagerImpl.getClass()
				.getDeclaredField("routesDao");

		field.setAccessible(true);
		field.set(routesManagerImpl, mockRoutesDaoImpl);*/
	}
	
	/**
	 * Test the methods for Equals.
	 */
	@Test()
	public void testFindRoutesById() {
		when(mockRoutesDaoImpl.findById(routesIdMock)).thenReturn(
				routes);
		Routes actualRoute = routesManagerImpl
				.findRoutesById(routesIdMock);

		assertEquals(routes, actualRoute);
	}
	
	@Test
	public final void testFindRoutesByIdForNull() {
		when(mockRoutesDaoImpl.findById(Mockito.anyInt())).thenReturn(null);

		Routes expectedRoute = routesManagerImpl
				.findRoutesById(routesIdMock);

		assertNull(expectedRoute);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testFindRoutesByIdException() {
		when(mockRoutesDaoImpl.findById(illegalRouteId)).thenThrow(
				new IllegalArgumentException());

		routesManagerImpl.findRoutesById(illegalRouteId);
	}
	
	@Test()
	public void testFindRoutesByCode() {
		when(mockRoutesDaoImpl.findByCode("0000000001")).thenReturn(
				routes);
		Routes actual = routesManagerImpl
				.findByCode("0000000001");

		assertEquals(routes, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testFindRoutesByCodeException() {
		when(mockRoutesDaoImpl.findByCode("#00000001")).thenThrow(
				new IllegalArgumentException());

		routesManagerImpl.findByCode("#00000001");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSaveRoutesException() {
		doThrow(new IllegalArgumentException()).when(mockRoutesDaoImpl)
				.save(routes);

		routesManagerImpl.createRoute(routes);
	}
	
	@Test()
	public void testRemoveRouteById() {
		when(mockRoutesDaoImpl.findById(routesIdMock)).thenReturn(
				routes);

		routesManagerImpl.removeRouteById(routesIdMock);

		verify(mockRoutesDaoImpl).findById(routesIdMock);
		verify(mockRoutesDaoImpl).remove(routes);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveRouteByIdException() {
		when(mockRoutesDaoImpl.findById(routesIdMock)).thenReturn(
				routes);

		doThrow(new IllegalArgumentException()).when(mockRoutesDaoImpl)
				.remove(eq(routes));

		routesManagerImpl.removeRouteById(routesIdMock);
	}
	
	@Test()
	public void testUpdateRoutes() {
		routesManagerImpl.updateRoute(routes);

		verify(mockRoutesDaoImpl).update(routes);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateTransportsException() {
		doThrow(new IllegalArgumentException()).when(mockRoutesDaoImpl)
				.update(routes);

		routesManagerImpl.updateRoute(routes);
	}
	
	@Test
	public final void testFindAllRoutesIsEmpty() {
		List<Routes> listOfRoutes = new ArrayList<Routes>();
		Routes route = mock(Routes.class);
		listOfRoutes.add(route);

		when(mockRoutesDaoImpl.getAllEntities()).thenReturn(
				listOfRoutes);
		List<Routes> routesList = routesManagerImpl
				.getAllRoutes();

		assertFalse(routesList.isEmpty());
	}
	
	@Test
	public final void testFindAllRoutesEmptyList() {
		List<Routes> listOfRoutes = new ArrayList<Routes>();

		when(mockRoutesDaoImpl.getAllEntities()).thenReturn(
				listOfRoutes);
		List<Routes> routesList = routesManagerImpl
				.getAllRoutes();

		assertTrue((listOfRoutes.size() == routesList.size())
				&& (listOfRoutes.containsAll(routesList)));
	}
	
	@Test
	public final void getRoutersListByStationNameArrivingTest() {
		String stationNameArrival = "Sknyliv";
		Time timeArrivalMin=parseStringToTime("00:05:00");
		Time timeArrivalMax=parseStringToTime("23:55:00");
		
		List<RouteTrip> listOfRoutes = new ArrayList<RouteTrip>();
		RouteTrip routeTrip = mock(RouteTrip.class);
		listOfRoutes.add(routeTrip);

		List<RouteTrip> expectedRouteTrips = Collections
				.singletonList(routeTrip);

		when(
				mockRoutesDaoImpl.findRoutersListByStationNameArriving(stationNameArrival, timeArrivalMin, timeArrivalMax)).
thenReturn(listOfRoutes);
		
		List<RouteTrip> actualRouteTrips = routesManagerImpl
				.findRoutersListByStationNameArriving(stationNameArrival, timeArrivalMin, timeArrivalMax);

		assertTrue(Iterables.elementsEqual(expectedRouteTrips , actualRouteTrips));
	}

	@Test
	public final void getRoutersListByStationNameArrivingIfEmptyListTest() {
		String stationNameArrival = "Pisochne";
		Time timeArrivalMin=parseStringToTime("00:05:00");
		Time timeArrivalMax=parseStringToTime("23:55:00");

		List<RouteTrip> listOfRouteTrip = new ArrayList<RouteTrip>();
		List<RouteTrip> expectedRouteTrips = new ArrayList<RouteTrip>();

		when(
				mockRoutesDaoImpl.findRoutersListByStationNameArriving(stationNameArrival, timeArrivalMin, timeArrivalMax))
				.thenReturn(listOfRouteTrip);
		List<RouteTrip> actualRouteTrips = routesManagerImpl
				.findRoutersListByStationNameArriving(stationNameArrival, timeArrivalMin, timeArrivalMax);

		assertTrue(Iterables.elementsEqual(expectedRouteTrips , actualRouteTrips));
	}
}