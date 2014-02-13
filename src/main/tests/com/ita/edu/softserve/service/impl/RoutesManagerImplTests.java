package com.ita.edu.softserve.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.collect.Iterables;

import java.sql.Time;

import org.junit.Test;

import com.ita.edu.softserve.dao.impl.LinesDAOImpl;
import com.ita.edu.softserve.dao.impl.RoutesDAOImpl;
import com.ita.edu.softserve.dao.impl.StationsDAOImpl;
import com.ita.edu.softserve.dao.impl.TransportsDaoImpl;
import com.ita.edu.softserve.dao.impl.UsersDAOImpl;
import com.ita.edu.softserve.manager.RoutesManager;
import com.ita.edu.softserve.manager.impl.AdminManagerImpl;
import com.ita.edu.softserve.manager.impl.LinesManagerImpl;
import com.ita.edu.softserve.manager.impl.RouteTrip;
import com.ita.edu.softserve.manager.impl.RoutesManagerImpl;
import com.ita.edu.softserve.manager.impl.StationsManagerImpl;
import com.ita.edu.softserve.manager.impl.TransportTravel;
import com.ita.edu.softserve.manager.impl.TransportsManagerImpl;

/**
 * Class under test {@link com.ita.edu.softserve.manager.impl.RoutesManagerImpl}
 * 
 * @author Lyubomyr
 * 
 */
public class RoutesManagerImplTests {

	/**
	 * Mock object.
	 */
	private RoutesDAOImpl mockRoutesDaoImpl;

	/**
	 * RoutesManagerImpl.
	 */
	private RoutesManagerImpl routesManagerImpl;
	
	/**
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@Before
	public final void setUp() throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {

		mockRoutesDaoImpl = mock(RoutesDAOImpl.class);

		routesManagerImpl = new RoutesManagerImpl();
		Field fild = routesManagerImpl.getClass().getDeclaredField(
				"routeDao");

		fild.setAccessible(true);
		fild.set(routesManagerImpl, mockRoutesDaoImpl);

	}
	
	@SuppressWarnings("deprecation")
	@Test(expected = IllegalArgumentException.class)
	public void testFindRoutersListByStationIdArrivingExceptionIsThrownIncorectId() {
		RoutesManager routesServiceArriving = new RoutesManagerImpl();
		routesServiceArriving.findRoutersListByStationNameArriving(null, new Time(
				0, 0, 0), new Time(23, 59, 0));
	}

	@SuppressWarnings("deprecation")
	@Test(expected = IllegalArgumentException.class)
	public void testFindRoutersListByStationIdDepartingExceptionIsThrownIncorectId() {
		RoutesManager routesServiceDeparting = new RoutesManagerImpl();
		routesServiceDeparting.findRoutersListByStationNameDeparting(null,
				new Time(0, 0, 0), new Time(23, 59, 0));
	}

	@SuppressWarnings("deprecation")
	@Test(expected = IllegalArgumentException.class)
	public void testFindRoutersListByStationIdArrivingExceptionIsThrownIncorectTime() {
		RoutesManager routesServiceArriving = new RoutesManagerImpl();
		routesServiceArriving.findRoutersListByStationNameArriving("Pisochne", new Time(
				23, 59, 0), new Time(0, 0, 0));
	}

	@SuppressWarnings("deprecation")
	@Test(expected = IllegalArgumentException.class)
	public void testFindRoutersListByStationIdDepartingExceptionIsThrownIncorectTime() {
		RoutesManager routesServiceDeparting = new RoutesManagerImpl();
		routesServiceDeparting.findRoutersListByStationNameDeparting("Pisochne", new Time(
				23, 59, 0), new Time(0, 0, 0));
	}
	
	/**
	 * Test for method {@link
	 * com.ita.edu.softserve.service.impl.RoutesManagerImpl#
	 * findRoutersListByStationNameArriving(stationNameArrival, timeArrivalMin, timeArrivalMax)}
	 */
	@SuppressWarnings("deprecation")
	@Test
	public final void findRoutersListByStationNameArrivingTest() {
		String arrStationName="Pisochne";
		Time arrTimeMin = new Time(0, 0, 0);
		Time arrTimeMax = new Time(23, 59, 0);
		
		List<RouteTrip> listOfRoutesTrip = new ArrayList<RouteTrip>();
		RouteTrip routeTrip= mock(RouteTrip.class);
		listOfRoutesTrip.add(routeTrip);		
		
		List<RouteTrip> expectedRouteTrip = Collections.singletonList(routeTrip);
		
		when(mockRoutesDaoImpl.findRoutersListByStationNameArriving(arrStationName, arrTimeMin, arrTimeMax)).thenReturn(
				listOfRoutesTrip);
		List<RouteTrip> actualRouteTrip = routesManagerImpl
				.findRoutersListByStationNameArriving(arrStationName, arrTimeMin, arrTimeMax);
		
		assertTrue(Iterables.elementsEqual(expectedRouteTrip, actualRouteTrip));		
	}
	
	/**
	 * Test for method {@link
	 * com.ita.edu.softserve.service.impl.RoutesManagerImpl#
	 * findRoutersListByStationNameArriving(stationNameArrival, timeArrivalMin, timeArrivalMax)}
	 */
	@SuppressWarnings("deprecation")
	@Test
	public final void findRoutersListByStationNameDepartingTest() {
		String depStationName="Pisochne";
		Time depTimeMin = new Time(0, 0, 0);
		Time depTimeMax = new Time(23, 59, 0);
		
		List<RouteTrip> listOfRoutesTrip = new ArrayList<RouteTrip>();
		RouteTrip routeTrip= mock(RouteTrip.class);
		listOfRoutesTrip.add(routeTrip);		
		
		List<RouteTrip> expectedRouteTrip = Collections.singletonList(routeTrip);
		
		when(mockRoutesDaoImpl.findRoutersListByStationNameDeparting(depStationName, depTimeMin, depTimeMax)).thenReturn(
				listOfRoutesTrip);
		List<RouteTrip> actualRouteTrip = routesManagerImpl
				.findRoutersListByStationNameDeparting(depStationName, depTimeMin,depTimeMax);
		
		assertTrue(Iterables.elementsEqual(expectedRouteTrip, actualRouteTrip));		
	}
	
}