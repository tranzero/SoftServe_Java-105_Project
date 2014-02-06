package com.ita.edu.softserve.main;

import java.util.ArrayList;
import java.util.List;

import com.ita.edu.softserve.manager.StationOnLineManager;
import com.ita.edu.softserve.manager.impl.StationOnLineManagerImpl;

public class StationOnLineMain {

	public static void main(String[] args) {
		StationOnLineManager lm = (StationOnLineManager) StationOnLineManagerImpl.getInstance();
		System.out.println("START!");
		//lm.removeStation(2, 1);
		System.out.println("KOOL!!!");
		List<String> str = new ArrayList<String>();
		str.add("Stryy");
		str.add("Ugers'ko");
		str.add("Dmytriya");
		str.add("Zadorognia");
		lm.addStationsToLine(3, str);
		System.out.println("ALL IS GOOD!!!");
	}

}
