package com.ita.edu.softserve.validationcontainers;

import java.sql.Time;

import org.springframework.stereotype.Component;

@Component
public interface TransportsCriteriaContainer {

	void setValuableInfo(String transportCode, String routeCode,
			String lineName, Integer seatClass1, Integer seatClass2,
			Integer seatClass3, Double genPrice, String minTime,
			String maxTime, String orderByParam, String orderByDirection);;

	String getTransportCode();

	void setTransportCode(String transportCode);

	String getRouteCode();

	void setRouteCode(String routeCode);

	String getLineName();

	void setLineName(String lineName);

	Integer getSeatClass1();

	void setSeatClass1(Integer seatClass1);

	Integer getSeatClass2();

	void setSeatClass2(Integer seatClass2);

	Integer getSeatClass3();

	void setSeatClass3(Integer seatClass3);

	Double getGenPrice();

	void setGenPrice(Double genPrice);

	String getMinTimeString();

	void setMinTimeString(String minTimeString);

	String getMaxTimeString();

	void setMaxTimeString(String maxTimeString);

	Time getMinTime();

	void setMinTime(Time minTime);

	Time getMaxTime();

	void setMaxTime(Time maxTime);

	void setOrderByParam(String orderByParam);

	String getOrderByDirection();

	void setOrderByDirection(String orderByDirection);

	String getOrderByParam();
}
