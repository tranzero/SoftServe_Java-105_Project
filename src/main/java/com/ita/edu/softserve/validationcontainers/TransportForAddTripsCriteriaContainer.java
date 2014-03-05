package com.ita.edu.softserve.validationcontainers;

public interface TransportForAddTripsCriteriaContainer {

	void setValuableParams(String transportCode, String routeName,
			String routesCode, Integer seatClass1, Integer seatClass2,
			Integer seatClass3, String priceName, String orderByCriteria,
			String orderByDirection);

	String getTransportCode();

	void setTransportCode(String transportCode);

	String getRouteName();

	void setRouteName(String routeName);

	String getRoutesCode();

	void setRoutesCode(String routesCode);

	Integer getSeatClass1();

	void setSeatClass1(Integer seatClass1);

	Integer getSeatClass2();

	void setSeatClass2(Integer seatClass2);

	Integer getSeatClass3();

	void setSeatClass3(Integer seatClass3);

	String getPriceName();

	void setPriceName(String priceName);

	Double getPrice();

	void setPrice(Double price);

	String getOrderByCriteria();

	void setOrderByCriteria(String orderByCriteria);

	String getOrderByDirection();

	void setOrderByDirection(String orderByDirection);

}
