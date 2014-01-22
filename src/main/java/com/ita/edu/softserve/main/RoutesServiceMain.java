/**
 * 
 */
package com.ita.edu.softserve.main;

import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.service.RoutesService;
import com.ita.edu.softserve.service.impl.RoutesServiceImpl;

import java.sql.Time;
import java.util.List;

/**
 * @author Lyubomyr
 * 
 */
public class RoutesServiceMain {

	public RoutesServiceMain() {
	}

	@SuppressWarnings("deprecation")
	public void printRoutesToConsole() {
		RoutesService routesService = new RoutesServiceImpl();
		System.out
				.println("Example: \"Find Routers List By StationId Arriving\"");
		List<Routes> listRoutesArriving = routesService
				.findRoutersListByStationIdArriving(5, new Time(0, 0, 0),
						new Time(23, 59, 0));
		System.out.println("List Routers find by stationId arriving");
		for (Routes routesArriving : listRoutesArriving) {
			System.out.println(routesArriving.getRouteId() + " "
					+ routesArriving.getRouteCode() + " "
					+ routesArriving.getStartTime());
		}

		System.out
				.println("Example: \"Find Routers List By StationId Departing\"");
		List<Routes> listRoutesDeparting = routesService
				.findRoutersListByStationIdDeparting(5, new Time(0, 0, 0),
						new Time(23, 59, 0));
		System.out.println("/nList Routers find by stationId departing");
		for (Routes routesDeparting : listRoutesDeparting) {
			System.out.println(routesDeparting.getRouteId() + " "
					+ routesDeparting.getRouteCode() + " "
					+ routesDeparting.getStartTime());
		}
	}

	public static void main(String[] args) {
		try {
			RoutesServiceMain routesServiceMain = new RoutesServiceMain();
			routesServiceMain.printRoutesToConsole();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}