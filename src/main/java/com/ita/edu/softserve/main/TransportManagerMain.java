package com.ita.edu.softserve.main;

import java.util.List;

import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.TransportsManager;
import com.ita.edu.softserve.manager.impl.TransportTravel;
import com.ita.edu.softserve.manager.impl.TransportsManagerImpl;

public class TransportManagerMain {

	public static void main(String[] args) {

		TransportsManager stationsManager = (TransportsManager) TransportsManagerImpl
				.getInstance();
		List<Transports> transportsList = stationsManager.getAllTransports();

		System.out.println("LINE ID " + " LINE NAME");
		for (Transports transport : transportsList) {
			System.out.println(transport.getRoutes().getLineId().getClass()
					.getSimpleName()
					+ ":	" + transport.getRoutes().getLineId().getLineName());
		}

		List<TransportTravel> transportTravelList = stationsManager
				.getTransportByTwoStations("Sknyliv", "Stryy");
		for (TransportTravel tt : transportTravelList) {
			System.out.println(tt.getTransport().getTransportCode() + " "
					+ tt.getDepartureTime() + " " + tt.getArrivalTime() + " "
					+ tt.getDuration());
		}

		System.out.println("/*****************************/");
		List<TransportTravel> trTravelList = stationsManager
				.getTransportByTwoStForPage("Pisochne", "Sknyliv", 1, 10, new String("2013/02/20"), 0);
		for (TransportTravel tt : trTravelList) {
			System.out.println(tt);
		}
	}
}
