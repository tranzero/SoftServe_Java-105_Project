/**
 * 
 */
package com.ita.edu.softserve.validationcontainers.impl;

import org.springframework.stereotype.Component;

import com.ita.edu.softserve.validationcontainers.StationsCriteriaContainer;

/**
 * @author admin
 * 
 */

@Component
public class StationsCriteriaContainerImpl implements StationsCriteriaContainer {

	public static final String[] STATIONS_ORDER_BY_COLUMNS = { "s.stationCode",
			"s.stationName" };

	private String searchString;
	private String orderByParam;
	private String orderByDirection;
	
	

	/**
	 * 
	 */
	public StationsCriteriaContainerImpl() {

	}

	/**
	 * @param searchString
	 * @param orderByParam
	 * @param orderByDirection
	 */
	public StationsCriteriaContainerImpl(String searchString,
			String orderByParam, String orderByDirection) {
		super();
		this.searchString = searchString;
		this.orderByParam = orderByParam;
		this.orderByDirection = orderByDirection;
	}

	@Override
	public void setValuableInfo(String searchString, String orderByParam,
			String orderByDirection) {
		this.searchString = searchString;
		this.orderByParam = orderByParam;
		this.orderByDirection = orderByDirection;
	}

	@Override
	public String getSearchString() {
		return searchString;
	}

	@Override
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	@Override
	public String getOrderByParam() {
		return orderByParam;
	}

	@Override
	public void setOrderByParam(String orderByParam) {
		this.orderByParam = orderByParam;
	}

	@Override
	public String getOrderByDirection() {
		return orderByDirection;
	}

	@Override
	public void setOrderByDirection(String orderByDirection) {
		this.orderByDirection = orderByDirection;
	}

}
