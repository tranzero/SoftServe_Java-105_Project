package com.ita.edu.softserve.entity;

import java.util.Date;

import javax.persistence.*;

/**
 * @author admin
 * 
 *         The persistent class for the STOPS database table.
 */
@Entity
@Table(name = "STOPS")
@NamedQuery(name = Stops.FIND_BY_ID, query = Stops.FIND_BY_ID_QUERY)
public class Stops {

	public static final String FIND_BY_ID = "Stops.findByID";
	public static final String FIND_BY_ID_QUERY = "SELECT u FROM Users u WHERE u.stopId = ?1";

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
	private Date arrival;

	@Column(name = "DEPARTURE")
	private Date departure;

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
	public Date getArrival() {
		return arrival;
	}

	/**
	 * @param arrival
	 *            the arrival to set
	 */
	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	/**
	 * @return the departure
	 */
	public Date getDeparture() {
		return departure;
	}

	/**
	 * @param departure
	 *            the departure to set
	 */
	public void setDeparture(Date departure) {
		this.departure = departure;
	}

}