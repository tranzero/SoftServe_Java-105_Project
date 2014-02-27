package com.ita.edu.softserve.main;

import java.sql.Time;
import java.util.List;

import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.TransportsManager;
import com.ita.edu.softserve.manager.impl.TransportTravel;
import com.ita.edu.softserve.manager.impl.TransportsManagerImpl;
import com.ita.edu.softserve.utils.ParseUtil;

public class TransportManagerMain {

	public static void main(String[] args) {

		TransportsManager transportManager = (TransportsManager) TransportsManagerImpl
				.getInstance();

		// getTransportsList(transportManager);

		// getTransportByTwoStForPage(transportManager);

		getTransportsListByCriteria(transportManager);
	}

	private static void getTransportsListByCriteria(
			TransportsManager transportManager) {
		System.out.println("************************************************");

		Time time = ParseUtil.parseStringToTime("16:00:00");
		Routes route = new Routes();
		route.setRouteId(1);
		List<Transports> transportsList2 = transportManager
				.getTransportsListByCriteria(1, 30, "T0", time,
						"1000000000003", 100, 10, 10, 0.0);

		System.out.println("Transport code" + "  Time      " + "Routes Code " + "Seats");
		for (Transports transport : transportsList2) {
			System.out.print(transport.getTransportCode() + "	");
			System.out.print(transport.getStartTime()+" ");
			System.out.print(transport.getRoutes().getRouteCode()+" ");
			System.out.print(transport.getSeatclass1()+" ");
			System.out.print(transport.getSeatclass2()+" ");
			System.out.print(transport.getSeatclass3()+" ");
			System.out.println(transport.getGenPrice());
		}
	}

	private static void getTransportsList(TransportsManager transportManager) {
		List<Transports> transportsList = transportManager.getAllTransports();
		System.out.println("************************************************");

		System.out.println("LINE ID " + " LINE NAME");
		for (Transports transport : transportsList) {
			System.out.println(transport.getRoutes().getLineId().getClass()
					.getSimpleName()
					+ ":	" + transport.getRoutes().getLineId().getLineName());
		}

		List<TransportTravel> transportTravelList = transportManager
				.getTransportByTwoStations("Sknyliv", "Stryy");
		for (TransportTravel tt : transportTravelList) {
			System.out.println(tt.getTransport().getTransportCode() + " "
					+ tt.getDepartureTime() + " " + tt.getArrivalTime() + " "
					+ tt.getDuration());
		}
	}

	private static void getTransportByTwoStForPage(
			TransportsManager transportManager) {
		System.out.println("************************************************");
		List<TransportTravel> trTravelList = transportManager
				.getTransportByTwoStForPage("Pisochne", "Sknyliv", 1, 10,
						new String("2013/02/20"), 0);
		for (TransportTravel tt : trTravelList) {
			System.out.println(tt);
		}
	}
}
