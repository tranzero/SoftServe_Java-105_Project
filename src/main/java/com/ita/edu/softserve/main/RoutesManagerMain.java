/**
 * 
 */
package com.ita.edu.softserve.main;

import java.sql.Time;
import java.util.List;

import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.manager.RoutesManager;
import com.ita.edu.softserve.manager.impl.RouteTrip;
import com.ita.edu.softserve.manager.impl.RoutesManagerImpl;

/**
 * @author Lyubomyr
 * 
 */
public class RoutesManagerMain {

	public RoutesManagerMain() {
	}

	@SuppressWarnings("deprecation")
	public void printRoutesToConsole() {
		RoutesManager routesService = (RoutesManager) RoutesManagerImpl
				.getInstance();
		System.out
				.println("Example: \"Find Routers List By StationName Arriving\"");
		String arrStationName="Pisochne";
		Time arrTimeMin = new Time(0, 0, 0);
		Time arrTimeMax = new Time(23, 59, 0);
		System.out.println("arrStationName = " + arrStationName + " arrTimeMin = "
				+ arrTimeMin + " arrTimeMax = " + arrTimeMax + "\n");
		List<RouteTrip> listRoutesArriving = routesService
				.findRoutersListByStationNameArriving(arrStationName, arrTimeMin,arrTimeMax);

		for (RouteTrip routesArriving : listRoutesArriving) {
			System.out.println(routesArriving.getRoute().getRouteId() + " "
					+ routesArriving.getRoute().getLineId().getLineName() + " "
					+ routesArriving.getRoute().getRouteCode() + " "
					+ routesArriving.getStartTime());
		}

		System.out
				.println("\nExample: \"Find Routers List By StationName Departing\"");
		String depStationName="Pisochne";
		Time depTimeMin = new Time(0, 0, 0);
		Time depTimeMax = new Time(23, 59, 0);
		System.out.println("depStationName = " + depStationName + " depTimeMin = "
				+ depTimeMin + " depTimeMax = " + depTimeMax + "\n");
		List<RouteTrip> listRoutesDeparting = routesService
				.findRoutersListByStationNameDeparting(depStationName, depTimeMin,
						depTimeMax);
		for (RouteTrip routesDeparting : listRoutesDeparting) {
			System.out.println(routesDeparting.getRoute().getRouteId() + " "
					+ routesDeparting.getRoute().getLineId().getLineName() + " "
					+ routesDeparting.getRoute().getRouteCode() + " "
					+ routesDeparting.getStartTime());
		}
	}

	public static void main(String[] args) {
		try {
			RoutesManagerMain routesManagerMain = new RoutesManagerMain();
			routesManagerMain.printRoutesToConsole();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}