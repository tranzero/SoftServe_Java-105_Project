package com.ita.edu.softserve.validationcontainers.impl;

import java.sql.Time;

import org.springframework.stereotype.Component;

import com.ita.edu.softserve.validationcontainers.TransportsCriteriaContainer;

/**
 * 
 * @author Roman
 * 
 *         Class for storing information using for validation in Trips query
 *         with criteria
 * 
 */

@Component("transportsCriteriaContainerImpl")
// @Scope("request")
public class TransportsCriteriaContainerImpl implements
		TransportsCriteriaContainer {

	public static final String[] TRANSPORTS_ORDER_BY_COLUMNS = {
			"t.transportCode", "t.startTime", "t.routes.routeCode",
			"t.routes.lineId.lineName", "t.seatClass1", "t.seatClass2",
			"t.seatClass3", "t.genPrice" };

	private String transportCode;
	private String routeCode;
	private String lineName;
	private Integer seatClass1;
	private Integer seatClass2;
	private Integer seatClass3;
	private Double genPrice;
	private String minTimeString;
	private String maxTimeString;
	private Time minTime;
	private Time maxTime;
	private String orderByParam;
	private String orderByDirection;

	public TransportsCriteriaContainerImpl() {

	}

	public TransportsCriteriaContainerImpl(String transportCode,
			String routeCode, String lineName, Integer seatClass1,
			Integer seatClass2, Integer seatClass3, Double genPrice,
			String minTimeString, String maxTimeString, Time minTime,
			Time maxTime, String orderByParam, String orderByDirection) {
		super();
		this.transportCode = transportCode;
		this.routeCode = routeCode;
		this.lineName = lineName;
		this.seatClass1 = seatClass1;
		this.seatClass2 = seatClass2;
		this.seatClass3 = seatClass3;
		this.genPrice = genPrice;
		this.minTimeString = minTimeString;
		this.maxTimeString = maxTimeString;
		this.minTime = minTime;
		this.maxTime = maxTime;
		this.orderByParam = orderByParam;
		this.orderByDirection = orderByDirection;
	}

	@Override
	public void setValuableInfo(String transportCode, String routeCode,
			String lineName, Integer seatClass1, Integer seatClass2,
			Integer seatClass3, Double genPrice, String minTimeString,
			String maxTimeString, /*Time minTime, Time maxTime,*/
			String orderByParam, String orderByDirection) {
		this.transportCode = transportCode;
		this.routeCode = routeCode;
		this.lineName = lineName;
		this.seatClass1 = seatClass1;
		this.seatClass2 = seatClass2;
		this.seatClass3 = seatClass3;
		this.genPrice = genPrice;
		this.minTimeString = minTimeString;
		this.maxTimeString = maxTimeString;
//		this.minTime = minTime;
//		this.maxTime = maxTime;
		this.orderByParam = orderByParam;
		this.orderByDirection = orderByDirection;
	}

	/**
	 * @return the transportCode
	 */
	@Override
	public String getTransportCode() {
		return transportCode;
	}

	/**
	 * @param transportCode
	 *            the transportCode to set
	 */
	@Override
	public void setTransportCode(String transportCode) {
		this.transportCode = transportCode;
	}

	/**
	 * @return the routeCode
	 */
	@Override
	public String getRouteCode() {
		return routeCode;
	}

	/**
	 * @param routeCode
	 *            the routeCode to set
	 */
	@Override
	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	/**
	 * @return the lineName
	 */
	@Override
	public String getLineName() {
		return lineName;
	}

	/**
	 * @param lineName
	 *            the lineName to set
	 */
	@Override
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	/**
	 * @return the seatClass1
	 */
	@Override
	public Integer getSeatClass1() {
		return seatClass1;
	}

	/**
	 * @param seatClass1
	 *            the seatClass1 to set
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
	 * @param seatClass2
	 *            the seatClass2 to set
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
	 * @param seatClass3
	 *            the seatClass3 to set
	 */
	@Override
	public void setSeatClass3(Integer seatClass3) {
		this.seatClass3 = seatClass3;
	}

	/**
	 * @return the genPrice
	 */
	@Override
	public Double getGenPrice() {
		return genPrice;
	}

	/**
	 * @param genPrice
	 *            the genPrice to set
	 */
	@Override
	public void setGenPrice(Double genPrice) {
		this.genPrice = genPrice;
	}

	/**
	 * @return the minTimeString
	 */
	@Override
	public String getMinTimeString() {
		return minTimeString;
	}

	/**
	 * @param minTimeString
	 *            the minTimeString to set
	 */
	@Override
	public void setMinTimeString(String minTimeString) {
		this.minTimeString = minTimeString;
	}

	/**
	 * @return the maxTimeString
	 */
	@Override
	public String getMaxTimeString() {
		return maxTimeString;
	}

	/**
	 * @param maxTimeString
	 *            the maxTimeString to set
	 */
	@Override
	public void setMaxTimeString(String maxTimeString) {
		this.maxTimeString = maxTimeString;
	}

	/**
	 * @return the minTime
	 */
	@Override
	public Time getMinTime() {
		return minTime;
	}

	/**
	 * @param minTime
	 *            the minTime to set
	 */
	@Override
	public void setMinTime(Time minTime) {
		this.minTime = minTime;
	}

	/**
	 * @return the maxTime
	 */
	@Override
	public Time getMaxTime() {
		return maxTime;
	}

	/**
	 * @param maxTime
	 *            the maxTime to set
	 */
	@Override
	public void setMaxTime(Time maxTime) {
		this.maxTime = maxTime;
	}

	/**
	 * @return the orderByParam
	 */
	@Override
	public String getOrderByParam() {
		return orderByParam;
	}

	/**
	 * @param orderByParam
	 *            the orderByParam to set
	 */
	@Override
	public void setOrderByParam(String orderByParam) {
		this.orderByParam = orderByParam;
	}

	/**
	 * @return the orderByDirection
	 */
	@Override
	public String getOrderByDirection() {
		return orderByDirection;
	}

	/**
	 * @param orderByDirection
	 *            the orderByDirection to set
	 */
	@Override
	public void setOrderByDirection(String orderByDirection) {
		this.orderByDirection = orderByDirection;
	}

}
