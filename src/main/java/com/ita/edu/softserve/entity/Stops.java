package com.ita.edu.softserve.entity;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author admin
 * 
 *         The persistent class for the STOPS database table.
 */
@Entity
@Table(name = "stops")

public class Stops extends BaseEntity {

	@Id
	@Column(name = "STOPID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int stopId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROUTEID")
	private Routes routeId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STATIONONLINEID")
	private StationsOnLine stationOnLineId;

	@Column(name = "ARRIVAL")
	private Time arrival;

	@Column(name = "DEPARTURE")
	private Time departure;

	/**
	 * Default Constructor
	 */
	public Stops() {
	}

	/**
	 * @return the stopId
	 */
	public int getStopId() {
		return stopId;
	}

	/**
	 * @param stopId
	 *            the stopId to set
	 */
	public void setStopId(int stopId) {
		this.stopId = stopId;
	}

	/**
	 * @return the routeId
	 */
	public Routes getRouteId() {
		return routeId;
	}

	/**
	 * @param routeId
	 *            the routeId to set
	 */
	public void setRouteId(Routes routeId) {
		this.routeId = routeId;
	}

	/**
	 * @return the stationOnLineID
	 */
	public StationsOnLine getStationOnLineID() {
		return stationOnLineId;
	}

	/**
	 * @param stationOnLineID
	 *            the stationOnLineID to set
	 */
	public void setStationOnLineID(StationsOnLine stationOnLineID) {
		this.stationOnLineId = stationOnLineID;
	}

	/**
	 * @return the arrival
	 */
	public Time getArrival() {
		return arrival;
	}

	/**
	 * @param arrival
	 *            the arrival to set
	 */
	public void setArrival(Time arrival) {
		this.arrival = arrival;
	}

	/**
	 * @return the departure
	 */
	public Time getDeparture() {
		return departure;
	}

	/**
	 * @param departure
	 *            the departure to set
	 */
	public void setDeparture(Time departure) {
		this.departure = departure;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + stopId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stops other = (Stops) obj;
		if (stopId != other.stopId)
			return false;
		return true;
	}

}