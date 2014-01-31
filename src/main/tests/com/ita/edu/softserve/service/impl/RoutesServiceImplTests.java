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
public class RoutesServiceImplTests {

	@SuppressWarnings("deprecation")
	@Test(expected = IllegalArgumentException.class)
	public void testFindRoutersListByStationIdArrivingExceptionIsThrownIncorectId() {
		RoutesManager routesServiceArriving = new RoutesManagerImpl();
		routesServiceArriving.findRoutersListByStationIdArriving(-1, new Time(
				0, 0, 0), new Time(23, 59, 0));
	}

	@SuppressWarnings("deprecation")
	@Test(expected = IllegalArgumentException.class)
	public void testFindRoutersListByStationIdDepartingExceptionIsThrownIncorectId() {
		RoutesManager routesServiceDeparting = new RoutesManagerImpl();
		routesServiceDeparting.findRoutersListByStationIdDeparting(-1,
				new Time(0, 0, 0), new Time(23, 59, 0));
	}

	@SuppressWarnings("deprecation")
	@Test(expected = IllegalArgumentException.class)
	public void testFindRoutersListByStationIdArrivingExceptionIsThrownIncorectTime() {
		RoutesManager routesServiceArriving = new RoutesManagerImpl();
		routesServiceArriving.findRoutersListByStationIdArriving(-1, new Time(
				23, 59, 0), new Time(0, 0, 0));
	}

	@SuppressWarnings("deprecation")
	@Test(expected = IllegalArgumentException.class)
	public void testFindRoutersListByStationIdDepartingExceptionIsThrownIncorectTime() {
		RoutesManager routesServiceDeparting = new RoutesManagerImpl();
		routesServiceDeparting.findRoutersListByStationIdDeparting(5, new Time(
				23, 59, 0), new Time(0, 0, 0));
	}
}