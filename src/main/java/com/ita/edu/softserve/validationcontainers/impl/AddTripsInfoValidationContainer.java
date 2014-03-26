package com.ita.edu.softserve.validationcontainers.impl;

import java.util.Locale;

public class AddTripsInfoValidationContainer {
	
	private String from;
	private String to;
	private String transportId;
	
	private Locale localeParam;

	/**
	 * @return the minDate
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param minDate the minDate to set
	 */
	public void setFrom(String minDate) {
		this.from = minDate;
	}

	/**
	 * @return the maxDate
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param maxDate the maxDate to set
	 */
	public void setTo(String maxDate) {
		this.to = maxDate;
	}

	/**
	 * @return the transportId
	 */
	public String getTransportId() {
		return transportId;
	}

	/**
	 * @param transportId the transportId to set
	 */
	public void setTransportId(String transportId) {
		this.transportId = transportId;
	}

	/**
	 * @return the localeParam
	 */
	public Locale getLocaleParam() {
		return localeParam;
	}

	/**
	 * @param localeParam the localeParam to set
	 */
	public void setLocaleParam(Locale localeParam) {
		this.localeParam = localeParam;
	}
	
}
