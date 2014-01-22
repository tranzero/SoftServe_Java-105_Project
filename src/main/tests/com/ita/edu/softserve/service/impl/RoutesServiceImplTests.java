/**
 * 
 */
package com.ita.edu.softserve.service.impl;

import java.sql.Time;

import org.junit.Test;

import com.ita.edu.softserve.service.RoutesService;
import com.ita.edu.softserve.service.impl.RoutesServiceImpl;

/**
 * @author Lyubomyr
 * 
 */
public class RoutesServiceImplTests {

	@SuppressWarnings("deprecation")
	@Test(expected = IllegalArgumentException.class)
	public void testFindRoutersListByStationIdArrivingExceptionIsThrownIncorectId() {
		RoutesService routesServiceArriving = new RoutesServiceImpl();
		routesServiceArriving.findRoutersListByStationIdArriving(-1, new Time(
				0, 0, 0), new Time(23, 59, 0));
	}

	@SuppressWarnings("deprecation")
	@Test(expected = IllegalArgumentException.class)
	public void testFindRoutersListByStationIdDepartingExceptionIsThrownIncorectId() {
		RoutesService routesServiceDeparting = new RoutesServiceImpl();
		routesServiceDeparting.findRoutersListByStationIdDeparting(-1,
				new Time(0, 0, 0), new Time(23, 59, 0));
	}

	@SuppressWarnings("deprecation")
	@Test(expected = IllegalArgumentException.class)
	public void testFindRoutersListByStationIdArrivingExceptionIsThrownIncorectTime() {
		RoutesService routesServiceArriving = new RoutesServiceImpl();
		routesServiceArriving.findRoutersListByStationIdArriving(-1, new Time(
				23, 59, 0), new Time(0, 0, 0));
	}

	@SuppressWarnings("deprecation")
	@Test(expected = IllegalArgumentException.class)
	public void testFindRoutersListByStationIdDepartingExceptionIsThrownIncorectTime() {
		RoutesService routesServiceDeparting = new RoutesServiceImpl();
		routesServiceDeparting.findRoutersListByStationIdDeparting(5, new Time(
				23, 59, 0), new Time(0, 0, 0));
	}
}