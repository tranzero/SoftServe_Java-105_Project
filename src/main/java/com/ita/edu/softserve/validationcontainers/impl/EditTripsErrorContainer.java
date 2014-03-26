package com.ita.edu.softserve.validationcontainers.impl;

public class EditTripsErrorContainer {
	private Boolean wrongDate;
	private Boolean wrongTransportId;
	private Boolean wrongSeats;
	/**
	 * @return the wrongDate
	 */
	public Boolean getWrongDate() {
		return wrongDate;
	}
	/**
	 * @param wrongDate the wrongDate to set
	 */
	public void setWrongDate(Boolean wrongDate) {
		this.wrongDate = wrongDate;
	}
	/**
	 * @return the wrongTransportId
	 */
	public Boolean getWrongTransportId() {
		return wrongTransportId;
	}
	/**
	 * @param wrongTransportId the wrongTransportId to set
	 */
	public void setWrongTransportId(Boolean wrongTransportId) {
		this.wrongTransportId = wrongTransportId;
	}
	/**
	 * @return the wrongSeats
	 */
	public Boolean getWrongSeats() {
		return wrongSeats;
	}
	/**
	 * @param wrongSeats the wrongSeats to set
	 */
	public void setWrongSeats(Boolean wrongSeats) {
		this.wrongSeats = wrongSeats;
	}
	
}
