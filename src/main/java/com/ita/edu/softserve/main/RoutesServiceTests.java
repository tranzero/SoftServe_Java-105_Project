/**
 * 
 */
package com.ita.edu.softserve.main;

import java.sql.Time;

import org.junit.Test;

import com.ita.edu.softserve.manager.RoutesManager;
import com.ita.edu.softserve.manager.impl.RoutesManagerImpl;

/**
 * @author Lyubomyr
 * 
 */
public class RoutesServiceTests {

	@SuppressWarnings("deprecation")
	@Test(expected = IllegalArgumentException.class)
	public void testFindRoutersListByStationIdArrivingExceptionIsThrown() {
		RoutesManager routesServiceArriving = new RoutesManagerImpl();
		routesServiceArriving.findRoutersListByStationIdArriving(-1, new Time(
				0, 0, 0), new Time(23, 59, 0));
	}

	@SuppressWarnings("deprecation")
	@Test(expected = IllegalArgumentException.class)
	public void testFindRoutersListByStationIdDepartingExceptionIsThrown() {
		RoutesManager routesServiceDeparting = new RoutesManagerImpl();
		routesServiceDeparting.findRoutersListByStationIdDeparting(-1,
				new Time(0, 0, 0), new Time(23, 59, 0));
	}
}