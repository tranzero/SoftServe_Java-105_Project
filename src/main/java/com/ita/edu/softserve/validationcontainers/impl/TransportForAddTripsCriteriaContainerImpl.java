package com.ita.edu.softserve.validationcontainers.impl;

import com.ita.edu.softserve.validationcontainers.TransportForAddTripsCriteriaContainer;

public class TransportForAddTripsCriteriaContainerImpl implements TransportForAddTripsCriteriaContainer {
	
	public static final String[] ORDER_BY_COLUMNS= {"t.transportCode",
		"t.routes.routeCode",
		"t.routes.routeName",
		"t.seatclass1",
		"t.seatclass2",
		"t.seatclass3",
		"t.genPrice",
		"t.startTime"};
	
	private String transportCode;
	private String routeName;
	private String routesCode;
	private Integer seatClass1;
	private Integer seatClass2;
	private Integer seatClass3;
	private String priceName;
	private Double price;
	private String orderByCriteria;
	private String orderByDirection;	
	
	/**
	 * @return the transportCode
	 */
	@Override
	public String getTransportCode() {
		return transportCode;
	}

	/**
	 * @param transportCode the transportCode to set
	 */
	@Override
	public void setTransportCode(String transportCode) {
		this.transportCode = transportCode;
	}

	/**
	 * @return the routeName
	 */
	@Override
	public String getRouteName() {
		return routeName;
	}

	/**
	 * @param routeName the routeName to set
	 */
	@Override
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	/**
	 * @return the routesCode
	 */
	@Override
	public String getRoutesCode() {
		return routesCode;
	}

	/**
	 * @param routesCode the routesCode to set
	 */
	@Override
	public void setRoutesCode(String routesCode) {
		this.routesCode = routesCode;
	}

	/**
	 * @return the seatClass1
	 */
	@Override
	public Integer getSeatClass1() {
		return seatClass1;
	}

	/**
	 * @param seatClass1 the seatClass1 to set
	 */
	@Override
	public void setSeatClass1(Integer seatClass1) {
		this.seatClass1 = seatClass1;
	}

	/**
	 * @return the seatClass2
	 */
	@Override
	public Integer getSeatClass2() {
		return seatClass2;
	}

	/**
	 * @param seatClass2 the seatClass2 to set
	 */
	@Override
	public void setSeatClass2(Integer seatClass2) {
		this.seatClass2 = seatClass2;
	}

	/**
	 * @return the seatClass3
	 */
	@Override
	public Integer getSeatClass3() {
		return seatClass3;
	}

	/**
	 * @param seatClass3 the seatClass3 to set
	 */
	@Override
	public void setSeatClass3(Integer seatClass3) {
		this.seatClass3 = seatClass3;
	}

	/**
	 * @return the priceName
	 */
	@Override
	public String getPriceName() {
		return priceName;
	}

	/**
	 * @param priceName the priceName to set
	 */
	@Override
	public void setPriceName(String priceName) {
		this.priceName = priceName;
	}

	/**
	 * @return the price
	 */
	@Override
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	@Override
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the orderByCriteria
	 */
	@Override
	public String getOrderByCriteria() {
		return orderByCriteria;
	}

	/**
	 * @param orderByCriteria the orderByCriteria to set
	 */
	@Override
	public void setOrderByCriteria(String orderByCriteria) {
		this.orderByCriteria = orderByCriteria;
	}

	/**
	 * @return the orderByDirection
	 */
	@Override
	public String getOrderByDirection() {
		return orderByDirection;
	}

	/**
	 * @param orderByDirection the orderByDirection to set
	 */
	@Override
	public void setOrderByDirection(String orderByDirection) {
		this.orderByDirection = orderByDirection;
	}

	/**
	 * @param transportCode
	 * @param routeName
	 * @param routesCode
	 * @param seatClass1
	 * @param seatClass2
	 * @param seatClass3
	 * @param priceName
	 * @param orderByCriteria
	 * @param orderByDirection
	 */
	public TransportForAddTripsCriteriaContainerImpl(String transportCode,
			String routeName, String routesCode, Integer seatClass1,
			Integer seatClass2, Integer seatClass3, String priceName,
			String orderByCriteria, String orderByDirection) {
		super();
		this.transportCode = transportCode;
		this.routeName = routeName;
		this.routesCode = routesCode;
		this.seatClass1 = seatClass1;
		this.seatClass2 = seatClass2;
		this.seatClass3 = seatClass3;
		this.priceName = priceName;
		this.orderByCriteria = orderByCriteria;
		this.orderByDirection = orderByDirection;
	}

	
	/**
	 * @param transportCode
	 * @param routeName
	 * @param routesCode
	 * @param seatClass1
	 * @param seatClass2
	 * @param seatClass3
	 * @param priceName
	 * @param orderByCriteria
	 * @param orderByDirection
	 * @return
	 */
	
	public TransportForAddTripsCriteriaContainerImpl(){
		super();
	}
	
	@Override
	public void setValuableParams(String transportCode,
			String routeName, String routesCode, Integer seatClass1,
			Integer seatClass2, Integer seatClass3, String price,
			String orderByCriteria, String orderByDirection) {
		this.transportCode = transportCode;
		this.routeName = routeName;
		this.routesCode = routesCode;
		this.seatClass1 = seatClass1;
		this.seatClass2 = seatClass2;
		this.seatClass3 = seatClass3;
		this.priceName = price;
		this.orderByCriteria = orderByCriteria;
		this.orderByDirection = orderByDirection;
	}

}
