/**
 * 
 */
package com.ita.edu.softserve.main;

import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.manager.LinesManager;
import com.ita.edu.softserve.manager.RoutesManager;
import com.ita.edu.softserve.manager.impl.LinesManagerImpl;
import com.ita.edu.softserve.manager.impl.RoutesManagerImpl;

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
		RoutesManager routesService = (RoutesManager) RoutesManagerImpl
				.getInstance();
		System.out
				.println("Example: \"Find Routers List By StationId Arriving\"");
		int arrStationId = 5;
		Time arrTimeMin = new Time(0, 0, 0);
		Time arrTimeMax = new Time(23, 59, 0);
		System.out.println("arrStationId = " + arrStationId + " arrTimeMin = "
				+ arrTimeMin + " arrTimeMax = " + arrTimeMax + "\n");
		List<Routes> listRoutesArriving = routesService
				.findRoutersListByStationIdArriving(arrStationId, arrTimeMin,
						arrTimeMax);

		for (Routes routesArriving : listRoutesArriving) {
			System.out.println(routesArriving.getRouteId() + " "
					+ routesArriving.getLineId().getLineName() + " "
					+ routesArriving.getRouteCode() + " "
					+ routesArriving.getStartTime());
		}

		System.out
				.println("\nExample: \"Find Routers List By StationId Departing\"");
		int depStationId = 5;
		Time depTimeMin = new Time(0, 0, 0);
		Time depTimeMax = new Time(23, 59, 0);
		System.out.println("depStationId = " + depStationId + " depTimeMin = "
				+ depTimeMin + " depTimeMax = " + depTimeMax + "\n");
		List<Routes> listRoutesDeparting = routesService
				.findRoutersListByStationIdDeparting(depStationId, depTimeMin,
						depTimeMax);
		for (Routes routesDeparting : listRoutesDeparting) {
			System.out.println(routesDeparting.getRouteId() + " "
					+ routesDeparting.getLineId().getLineName() + " "
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