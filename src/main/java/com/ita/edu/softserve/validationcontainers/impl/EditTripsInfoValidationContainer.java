package com.ita.edu.softserve.validationcontainers.impl;

import java.util.Locale;

public class EditTripsInfoValidationContainer {
	private String transportId;
	private String startDate;
	private String remSeatClass1;
	private String remSeatClass2;
	private String remSeatClass3;
	private Locale localeParam;
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
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the remSeatClass1
	 */
	public String getRemSeatClass1() {
		return remSeatClass1;
	}
	/**
	 * @param remSeatClass1 the remSeatClass1 to set
	 */
	public void setRemSeatClass1(String remSeatClass1) {
		this.remSeatClass1 = remSeatClass1;
	}
	/**
	 * @return the remSeatClass2
	 */
	public String getRemSeatClass2() {
		return remSeatClass2;
	}
	/**
	 * @param remSeatClass2 the remSeatClass2 to set
	 */
	public void setRemSeatClass2(String remSeatClass2) {
		this.remSeatClass2 = remSeatClass2;
	}
	/**
	 * @return the remSeatClass3
	 */
	public String getRemSeatClass3() {
		return remSeatClass3;
	}
	/**
	 * @param remSeatClass3 the remSeatClass3 to set
	 */
	public void setRemSeatClass3(String remSeatClass3) {
		this.remSeatClass3 = remSeatClass3;
	}

}
