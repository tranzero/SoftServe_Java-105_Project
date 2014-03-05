package com.ita.edu.softserve.validationcontainers;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
//@Scope("request")

public interface TripsCriteriaContainer {

	
	String getTransportCode();
	
	void setTransportCode(String transportCode);
	
	String getRouteName();
	
	void setRouteName(String routeName);
	
	Integer getRemSeatClass1();
	
	void setRemSeatClass1(Integer remSeatClass1);
	
	Integer getRemSeatClass2();
	
	void setRemSeatClass2(Integer remSeatClass2);
	
	Integer getRemSeatClass3();
	
	void setRemSeatClass3(Integer remSeatClass3);
	
	String getMinDate();
	
	void setMinDate(String minDateString);

	String getMaxDate();
	
	void setMaxDate(String maxDateString);
	
	Date getMinDateValue();
	
	void setMinDateValue(Date minDate);
	
	Date getMaxDateValue();
	
	void setMaxDateValue(Date maxDate);
	
	String getOrderByParam();
	
	void setOrderByParam(String orderByParam);
	
	String getOrderByDirection();
	
	void setOrderByDirection(String orderByDirection);
	
	void setValuableInfo(String transportCode, String routeName, Integer remSeatClass1,
			Integer remSeatClass2, Integer remSeatClass3, String minDateString,
			String maxDateString, String orderByParam, String orderByDirection);


	
	
}
