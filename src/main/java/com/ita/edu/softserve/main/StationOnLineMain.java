package com.ita.edu.softserve.main;

import com.ita.edu.softserve.manager.StationOnLineManager;
import com.ita.edu.softserve.manager.impl.StationOnLineManagerImpl;

public class StationOnLineMain {

	public static void main(String[] args) {
		StationOnLineManager lm = (StationOnLineManager) StationOnLineManagerImpl.getInstance();
		System.out.println("START!");
		lm.removeStation(2, 1);
		System.out.println("KOOL!!!");
	}

}
