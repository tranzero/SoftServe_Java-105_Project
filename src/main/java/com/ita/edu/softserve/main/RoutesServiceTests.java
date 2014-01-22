/**
 * 
 */
package com.ita.edu.softserve.main;

import java.sql.Time;

import org.junit.Test;

import com.ita.edu.softserve.service.RoutesService;
import com.ita.edu.softserve.service.impl.RoutesServiceImpl;

/**
 * @author Lyubomyr
 * 
 */
public class RoutesServiceTests {

	@SuppressWarnings("deprecation")
	@Test(expected = IllegalArgumentException.class)
	public void testFindRoutersListByStationIdArrivingExceptionIsThrown() {
		RoutesService routesServiceArriving = new RoutesServiceImpl();
		routesServiceArriving.findRoutersListByStationIdArriving(-1, new Time(
				0, 0, 0), new Time(23, 59, 0));
	}

	@SuppressWarnings("deprecation")
	@Test(expected = IllegalArgumentException.class)
	public void testFindRoutersListByStationIdDepartingExceptionIsThrown() {
		RoutesService routesServiceDeparting = new RoutesServiceImpl();
		routesServiceDeparting.findRoutersListByStationIdDeparting(-1,
				new Time(0, 0, 0), new Time(23, 59, 0));
	}
}