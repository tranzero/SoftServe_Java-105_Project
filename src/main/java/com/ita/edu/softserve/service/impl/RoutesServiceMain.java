/**
 * 
 */
package com.ita.edu.softserve.service.impl;

import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.service.RoutesService;

import java.sql.Time;
import java.util.List;

/**
 * @author Lyubomyr
 *
 */
public class RoutesServiceMain {

	public RoutesServiceMain() {
	}

	public void printRoutesToConsole() {
		RoutesService routesService = new RoutesServiceImpl();
		System.out
				.println("Example: \"Find Routers List By StationId Arriving\"");
		int idStationArriving = 5;
		Time timeArrivalMin = new Time(0, 0, 0);
		Time timeArrivalMax = new Time(23, 59, 0);
		List<Routes> listRoutesArriving = routesService
				.findRoutersListByStationIDArriving(idStationArriving,
						timeArrivalMin, timeArrivalMax);
		System.out.println("List Routers find by stationId arriving");
		for (Routes routesArriving : listRoutesArriving) {
			System.out.println(routesArriving.getRouteId() + " "
					+ routesArriving.getRouteCode() + " "
					+ routesArriving.getStartTime());
		}

		System.out
				.println("Example: \"Find Routers List By StationId Departing\"");
		int idStationDeparting = 5;
		Time timeDepartureMin = new Time(0, 0, 0);
		Time timeDepartureMax = new Time(23, 59, 0);
		List<Routes> listRoutesDeparting = routesService
				.findRoutersListByStationIDDeparting(idStationDeparting,
						timeDepartureMin, timeDepartureMax);
		System.out.println("/nList Routers find by stationId departing");
		for (Routes routesDeparting : listRoutesDeparting) {
			System.out.println(routesDeparting.getRouteId() + " "
					+ routesDeparting.getRouteCode() + " "
					+ routesDeparting.getStartTime());
		}
	}

	public static void main(String[] args) {
		RoutesServiceMain routesServiceMain = new RoutesServiceMain();
		routesServiceMain.printRoutesToConsole();
	}
}
