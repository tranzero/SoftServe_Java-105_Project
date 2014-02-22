package com.ita.edu.softserve.main;

import java.util.List;

import com.ita.edu.softserve.entity.Responses;
import com.ita.edu.softserve.manager.ResponsesManager;
import com.ita.edu.softserve.manager.impl.ResponsesManagerImpl;

public class ResponsesMain {

	public static void main(String[] args) {
		ResponsesManager rManager = (ResponsesManager) ResponsesManagerImpl.getInstance();
		
		List<Responses> rList = rManager.getResponsesByRouteId(1);
		System.out.println("/************* BY ROUTE ID *************/");
		for (Responses r : rList) {
			System.out.println(r.getUser().getUserName() + " " + r.getComment());
		}
		
		System.out.println("/************* BY TRIP ID *************/");
		rList = rManager.getResponsesByTripId(9);
		for (Responses r : rList) {
			System.out.println(r.getUser().getUserName() + " " + r.getComment());
		}

		System.out.println("/************* BY TRANSPORT ID *************/");
		rList = rManager.getResponsesByTransportId(4);
		for (Responses r : rList) {
			System.out.println(r.getUser().getUserName() + " " + r.getComment());
		}
}

}
