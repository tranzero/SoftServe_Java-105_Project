package com.ita.edu.softserve.main;

import java.util.List;

import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.service.StationsManager;

public class StationServiceMain implements MainIface {

	public static void main(String[] args) {
		
		StationsManager stationsManager = (StationsManager) appContext
				.getBean("stationsManager");

		List<Stations> stationList = stationsManager.findAllStations();
		System.out.println("STATION CODE " + " STATION NAME");
		for (Stations station : stationList) {
			System.out.println(station.getStationCode() + " "
					+ station.getStationName());
		}
	}
}
