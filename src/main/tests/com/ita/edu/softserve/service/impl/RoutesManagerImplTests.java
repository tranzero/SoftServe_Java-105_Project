/**
 * 
 */
package com.ita.edu.softserve.service.impl;

import java.sql.Time;

import org.junit.Test;

import com.ita.edu.softserve.manager.RoutesManager;
import com.ita.edu.softserve.manager.impl.RoutesManagerImpl;

/**
 * @author Lyubomyr
 * 
 */
public class RoutesManagerImplTests {

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
}