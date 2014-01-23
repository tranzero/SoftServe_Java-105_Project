package com.ita.edu.softserve.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.service.StationsService;

public class StationServiceMain {

	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"/META-INF/spring/root-context.xml");
		StationsService stationsDAOImpl = (StationsService) appContext
				.getBean("stationsService");

		List<Stations> stationList = stationsDAOImpl.findAllStations();
		System.out.println("CTATION CODE " + " CTATION NAME");
		for (Stations station : stationList) {
			System.out.println(station.getStationCode() + " "
					+ station.getStationName());
		}
	}
}
