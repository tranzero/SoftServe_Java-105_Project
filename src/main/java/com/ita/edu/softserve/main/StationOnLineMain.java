package com.ita.edu.softserve.main;

import java.util.List;

import com.ita.edu.softserve.entity.StationsOnLine;
import com.ita.edu.softserve.manager.StationOnLineManager;
import com.ita.edu.softserve.manager.StationsManager;
import com.ita.edu.softserve.manager.impl.StationOnLineManagerImpl;
import com.ita.edu.softserve.manager.impl.StationsManagerImpl;

public class StationOnLineMain {

	public static void main(String[] args) {
		StationOnLineManager lm = (StationOnLineManager) StationOnLineManagerImpl.getInstance();
		StationsManager st = (StationsManager) StationsManagerImpl.getInstance();
//		System.out.println("START!");
//		//lm.removeStation(2, 1);
//		System.out.println("KOOL!!!");
//		try {
//			List<Stations> str = st.findAllStations();
//			lm.updateStationOnLine(3, str);
//		} catch (StationManagerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		str.add("Stryy");
//		str.add("Ugers'ko");
//		str.add("Dmytriya");
//		str.add("Zadorognia");
//		lm.addStationsToLine(3, str);
//		System.out.println("ALL IS GOOD!!!");
		
		List<StationsOnLine> stationList = lm.findStationsOnLine(2);
		
		for (StationsOnLine station : stationList) {
			System.out.println(station.getStationId().getStationName());
		}
	}

}
