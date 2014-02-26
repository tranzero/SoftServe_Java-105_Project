package com.ita.edu.softserve.validationcontainers.impl;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ita.edu.softserve.validationcontainers.TripsCriteriaContainer;

/**
 * 
 * @author dnycktc
 * 
 *         Class for storing information using for validation in Trips query
 *         with criteria
 * 
 */

@Component
// @Scope("request")
public class TripsCriteriaContainerImpl implements TripsCriteriaContainer {

	public static final String[] TRIPS_ORDER_BY_COLUMNS = {
			"tr.transport.transportCode", "tr.transport.routes.routeName",
			"tr.remSeatClass1", "tr.remSeatClass2", "tr.remSeatClass3",
			"tr.startDate", "tr.transport.startTime" };

	private String transportCode;
	private String routeName;
	private Integer remSeatClass1;
	private Integer remSeatClass2;
	private Integer remSeatClass3;
	private String minDateString;
	private String maxDateString;
	private Date minDate;
	private Date maxDate;
	private String orderByParam;
	private String orderByDirection;

	public TripsCriteriaContainerImpl() {

	}

	public TripsCriteriaContainerImpl(String transportCode, String routeName,
			Integer remSeatClass1, Integer remSeatClass2,
			Integer remSeatClass3, String minDateString, String maxDateString,
			String orderByParam, String orderByDirection) {
		super();
		this.transportCode = transportCode;
		this.routeName = routeName;
		this.remSeatClass1 = remSeatClass1;
		this.remSeatClass2 = remSeatClass2;
		this.remSeatClass3 = remSeatClass3;
		this.minDateString = minDateString;
		this.maxDateString = maxDateString;
		this.orderByParam = orderByParam;
		this.orderByDirection = orderByDirection;
	}

	@Override
	public void setValuableInfo(String transportCode, String routeName,
			Integer remSeatClass1, Integer remSeatClass2,
			Integer remSeatClass3, String minDateString, String maxDateString,
			String orderByParam, String orderByDirection) {
		this.transportCode = transportCode;
		this.routeName = routeName;
		this.remSeatClass1 = remSeatClass1;
		this.remSeatClass2 = remSeatClass2;
		this.remSeatClass3 = remSeatClass3;
		this.minDateString = minDateString;
		this.maxDateString = maxDateString;
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
	 * @return the routeName
	 */
	@Override
	public String getRouteName() {
		return routeName;
	}

	/**
	 * @param routeName
	 *            the routeName to set
	 */
	@Override
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	/**
	 * @return the remSeatClass1
	 */
	@Override
	public Integer getRemSeatClass1() {
		return remSeatClass1;
	}

	/**
	 * @param remSeatClass1
	 *            the remSeatClass1 to set
	 */
	@Override
	public void setRemSeatClass1(Integer remSeatClass1) {
		this.remSeatClass1 = remSeatClass1;
	}

	/**
	 * @return the remSeatClass2
	 */
	@Override
	public Integer getRemSeatClass2() {
		return remSeatClass2;
	}

	/**
	 * @param remSeatClass2
	 *            the remSeatClass2 to set
	 */
	@Override
	public void setRemSeatClass2(Integer remSeatClass2) {
		this.remSeatClass2 = remSeatClass2;
	}

	/**
	 * @return the remSeatClass3
	 */
	@Override
	public Integer getRemSeatClass3() {
		return remSeatClass3;
	}

	/**
	 * @param remSeatClass3
	 *            the remSeatClass3 to set
	 */
	@Override
	public void setRemSeatClass3(Integer remSeatClass3) {
		this.remSeatClass3 = remSeatClass3;
	}

	/**
	 * @return the minDateString
	 */
	@Override
	public String getMinDateString() {
		return minDateString;
	}

	/**
	 * @param minDateString
	 *            the minDateString to set
	 */
	@Override
	public void setMinDateString(String minDateString) {
		this.minDateString = minDateString;
	}

	/**
	 * @return the maxDateString
	 */
	@Override
	public String getMaxDateString() {
		return maxDateString;
	}

	/**
	 * @param maxDateString
	 *            the maxDateString to set
	 */
	@Override
	public void setMaxDateString(String maxDateString) {
		this.maxDateString = maxDateString;
	}

	/**
	 * @return the minDate
	 */
	@Override
	public Date getMinDate() {
		return minDate;
	}

	/**
	 * @param minDate
	 *            the minDate to set
	 */
	@Override
	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	/**
	 * @return the maxDate
	 */
	@Override
	public Date getMaxDate() {
		return maxDate;
	}

	/**
	 * @param maxDate
	 *            the maxDate to set
	 */
	@Override
	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
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
