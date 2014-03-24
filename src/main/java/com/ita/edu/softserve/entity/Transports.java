package com.ita.edu.softserve.entity;

import java.sql.Time;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * The persistent class for the transports database table.
 */
@Entity
@Table(name = "transports")
@NamedQueries({
		@NamedQuery(name = Transports.TRANSPORTS_FIND_ALL, query = Transports.TRANSPORTS_FIND_ALL_QUERY),
		@NamedQuery(name = Transports.TRANSPORTS_FIND_COUNT, query = Transports.TRANSPORTS_FIND_COUNT_QUERY),
		@NamedQuery(name = Transports.FIND_BY_TRANSPORTCODE, query = Transports.FIND_BY_TRANSPORTCODE_QUERY),
		@NamedQuery(name = Transports.FIND_BY_ROUTEID, query = Transports.FIND_BY_ROUTEID_QUERY),
		@NamedQuery(name = Transports.FIND_TRANSPORTS_COUNT, query = Transports.FIND_TRANSPORTS_COUNT_QUERY),
		@NamedQuery(name = Transports.FIND_TRANSPORTS_FOR_ADD_TRIPS_COUNT, query = Transports.FIND_TRANSPORTS_FOR_ADD_TRIPS_COUNT_QUERY),
		@NamedQuery(name = Transports.FIND_BY_TWO_STATIONS, query = Transports.FIND_BY_TWO_STATIONS_QUERY),
		@NamedQuery(name = Transports.FIND_BY_TWO_STATIONS_AND_DATE, query = Transports.FIND_BY_TWO_STATIONS_AND_DATE_QUERY),
		@NamedQuery(name = Transports.FIND_BY_DATE, query = Transports.FIND_BY_DATE_QUERY), })
public class Transports extends BaseEntity {

	/**
	 * Defines the name of transport code parameter.
	 */
	public static final String TRANSPORT_CODE_NAME = "transportCode";

	/**
	 * Defines the name of route code parameter.
	 */
	public static final String ROUTE_CODE_NAME = "routeCode";

	/**
	 * Defines the name of route name parameter.
	 */
	public static final String ROUTE_NAME_NAME = "routeName";

	/**
	 * Defines the name of line name parameter.
	 */
	public static final String LINE_NAME_NAME = "lineName";

	/**
	 * Defines the name of seat class 1 parameter.
	 */
	public static final String SEAT_CLASS1_NAME = "seatClass1";

	/**
	 * Defines the name of seat class 2 parameter.
	 */
	public static final String SEAT_CLASS2_NAME = "seatClass2";

	/**
	 * Defines the name of seat class 3 parameter.
	 */
	public static final String SEAT_CLASS3_NAME = "seatClass3";

	/**
	 * Defines the name of general price name parameter.
	 */
	public static final String GEN_PRICE_NAME = "genPrice";

	/**
	 * Defines the name of minimum time parameter.
	 */
	public static final String MIN_TIME_NAME = "minTime";

	/**
	 * Defines the name of passed value parameter.
	 */
	public static final String PASSED_VALUE_NAME = "passedValue";

	/**
	 * Defines the name of passed id parameter.
	 */
	public static final String PASSED_ID_NAME = "passedId";

	/**
	 * Defines the name of maximum time parameter.
	 */
	public static final String MAX_TIME_NAME = "maxTime";
	
	/**
	 * Common part for all ORDER BY using queries
	 */
	public static final String GENERAL_ORDER_PART = ", t.transportId ";

	public static final String ORDER_BY_CRITERIA_NAME = "orderByCriteria";
	public static final String ORDER_BY_DIRECTION_NAME = "orderByDirection";

	public static final String FIND_BY_DATE = "Transports.findByDate";
	public static final String FIND_BY_DATE_QUERY = "SELECT t FROM Transports t JOIN t.trips tr WHERE tr.startDate  = ?1";

	public static final String TRANSPORTS_FIND_ALL = "Transports.findAll";
	public static final String TRANSPORTS_FIND_ALL_QUERY = "SELECT t FROM Transports t";

	public static final String TRANSPORTS_FIND_COUNT = "Transports.findCount";
	public static final String TRANSPORTS_FIND_COUNT_QUERY = "SELECT COUNT (t.transportId) FROM Transports t";

	public static final String FIND_BY_TRANSPORTCODE = "Transports.findByTransportCode";
	public static final String FIND_BY_TRANSPORTCODE_QUERY = "SELECT t FROM Transports t WHERE t.transportCode = ?1";

	public static final String FIND_BY_ROUTEID = "Transports.findByRouteId";
	public static final String FIND_BY_ROUTEID_QUERY = "SELECT t FROM Transports t WHERE t.routes.routeId = ?1";

	/*------------------------------------------------------------------------------------------------------------*/
	/**
	 * Name of query which is used for selecting count of transports from DB
	 * with criteria. Used in paging.
	 */
	public static final String FIND_TRANSPORTS_COUNT = "Transports.findTransportsCount";

	/**
	 * Query which is used for selecting count of trips from DB with criteria.
	 * Used in paging.
	 */
	public static final String FIND_TRANSPORTS_COUNT_QUERY = "SELECT COUNT(t.transportId) FROM Transports t WHERE t.transportCode LIKE :"
			+ TRANSPORT_CODE_NAME
			+ " AND t.routes.routeCode LIKE :"
			+ ROUTE_CODE_NAME
			+ " AND t.routes.routeName LIKE :"
			+ ROUTE_NAME_NAME
			+ " AND t.seatclass1 >= :"
			+ SEAT_CLASS1_NAME
			+ " AND t.seatclass2 >= :"
			+ SEAT_CLASS2_NAME
			+ " AND t.seatclass3 >= :"
			+ SEAT_CLASS3_NAME
			+ " AND t.genPrice < :" + GEN_PRICE_NAME;

	/**
	 * Query which is used for selecting transports from DB using criteria.
	 * Compatible with paging.
	 */
	public static final String FIND_TRANSPORTS_QUERY = "SELECT t FROM Transports t WHERE t.transportCode LIKE :"
			+ TRANSPORT_CODE_NAME
			+ " AND t.routes.routeCode LIKE :"
			+ ROUTE_CODE_NAME
			+ " AND t.routes.routeName LIKE :"
			+ ROUTE_NAME_NAME
			+ " AND t.seatclass1 >= :"
			+ SEAT_CLASS1_NAME
			+ " AND t.seatclass2 >= :"
			+ SEAT_CLASS2_NAME
			+ " AND t.seatclass3 >= :"
			+ SEAT_CLASS3_NAME
			+ " AND t.genPrice < :" + GEN_PRICE_NAME + " ORDER BY ";

	/*------------------------------------------------------------------------------------------------------------*/

	public static final String FIND_TRANSPORTS_FOR_ADD_TRIPS_COUNT = "Transports.findTransportsForAddTripsCount";
	public static final String FIND_TRANSPORTS_FOR_ADD_TRIPS_COUNT_QUERY = "SELECT COUNT(t.transportId) FROM Transports t WHERE t.transportCode LIKE :"
			+ TRANSPORT_CODE_NAME
			+ " AND t.routes.routeCode LIKE :"
			+ ROUTE_CODE_NAME
			+ " AND t.routes.routeName LIKE :"
			+ ROUTE_NAME_NAME
			+ " AND "
			+ "t.seatclass1 >= :"
			+ SEAT_CLASS1_NAME
			+ " AND t.seatclass2 >= :"
			+ SEAT_CLASS2_NAME
			+ " AND t.seatclass3 >= :"
			+ SEAT_CLASS3_NAME
			+ " AND t.genPrice < :" + GEN_PRICE_NAME;

	public static final String FIND_TRANSPORTS_FOR_ADD_TRIPS_QUERY = "SELECT t FROM Transports t WHERE t.transportCode LIKE :"
			+ TRANSPORT_CODE_NAME
			+ " AND t.routes.routeCode LIKE :"
			+ ROUTE_CODE_NAME
			+ " AND t.routes.routeName LIKE :"
			+ ROUTE_NAME_NAME
			+ " AND "
			+ "t.seatclass1 >= :"
			+ SEAT_CLASS1_NAME
			+ " AND t.seatclass2 >= :"
			+ SEAT_CLASS2_NAME
			+ " AND t.seatclass3 >= :"
			+ SEAT_CLASS3_NAME
			+ " AND t.genPrice < :" + GEN_PRICE_NAME + " ORDER BY ";

	/**
	 * Query which is used for selecting index of transport from DB with
	 * criteria. Part 1. Used in paging in trip editing.
	 */
	public static final String FIND_TRANSPORTS_FOR_ADD_TRIPS_INDEX_QUERY_PART1 = "SELECT COUNT(t.transportId) FROM Transports t WHERE (t.transportCode LIKE :"
			+ TRANSPORT_CODE_NAME
			+ " AND t.routes.routeCode LIKE :"
			+ ROUTE_CODE_NAME
			+ " AND t.routes.routeName LIKE :"
			+ ROUTE_NAME_NAME
			+ " AND "
			+ "t.seatclass1 >= :"
			+ SEAT_CLASS1_NAME
			+ " AND t.seatclass2 >= :"
			+ SEAT_CLASS2_NAME
			+ " AND t.seatclass3 >= :"
			+ SEAT_CLASS3_NAME
			+ " AND t.genPrice < :" + GEN_PRICE_NAME + ") AND ((";

	/**
	 * Query which is used for selecting index of transport from DB with
	 * criteria. Part 2. Used in paging in trip editing.
	 */
	public static final String FIND_TRANSPORTS_FOR_ADD_TRIPS_INDEX_QUERY_PART2 = " :"
			+ PASSED_VALUE_NAME + ") OR ((";

	/**
	 * Query which is used for selecting index of transport from DB with
	 * criteria. Part 3. Used in paging in trip editing.
	 */
	public static final String FIND_TRANSPORTS_FOR_ADD_TRIPS_INDEX_QUERY_PART3 = " = :"
			+ PASSED_VALUE_NAME + ") AND (t.transportId ";

	/**
	 * Query which is used for selecting index of transport from DB with
	 * criteria. Part 4. Used in paging in trip editing.
	 */
	public static final String FIND_TRANSPORTS_FOR_ADD_TRIPS_INDEX_QUERY_PART4 = " :"
			+ PASSED_ID_NAME + ")))";

	/**
	 * Name of query which is used for finding transport by two stations
	 * including stops at these stations
	 */
	public static final String FIND_BY_TWO_STATIONS = "Transports.findByTwoStations";

	/**
	 * Query to find transport by two stations including stops at these stations
	 */
	public static final String FIND_BY_TWO_STATIONS_QUERY = "SELECT "
			+ "NEW com.ita.edu.softserve.manager.impl.TransportTravel(t, TIME(TIME(s.departure) + TIME(t.startTime)), TIME(MAX(s.arrival)), TIME(TIME(MAX(s.arrival)) - TIME(s.departure))) "
			+ "FROM Transports t "
			+ "JOIN t.routes r "
			+ "JOIN r.stops s "
			+ "JOIN s.stationOnLineId sol "
			+ "JOIN sol.stationId st "
			+ "WHERE t.routes.routeId in "
			+ "(SELECT s1.routeId.routeId "
			+ "FROM Stops s1, Stops s2 "
			+ "WHERE s1.stationOnLineId.stationId.stationName LIKE ?1 "
			+ "and s2.stationOnLineId.stationId.stationName LIKE ?2 "
			+ "and s1.stationOnLineId.lineId.lineId = s2.stationOnLineId.lineId.lineId "
			+ "and s2.stationOnLineId.stationOrderNum > s1.stationOnLineId.stationOrderNum"
			+ ") and (sol.stationId.stationName LIKE ?1 or sol.stationId.stationName LIKE ?2) "
			+ "GROUP BY t.transportId";

	/**
	 * Name of query which is used for finding transport by two stations and
	 * date including stops at these stations
	 */
	public static final String FIND_BY_TWO_STATIONS_AND_DATE = "Transports.findByTwoStationsAndDate";

	/**
	 * Query to find transport by two stations and date including stops at these
	 * stations
	 */
	public static final String FIND_BY_TWO_STATIONS_AND_DATE_QUERY = "SELECT "
			+ "NEW com.ita.edu.softserve.manager.impl.TransportTravel(t, TIME(TIME(s.departure) + TIME(t.startTime)), TIME(MAX(s.arrival)), TIME(TIME(MAX(s.arrival)) - TIME(s.departure)), tr) "
			+ "FROM Trips tr "
			+ "JOIN tr.transport t "
			+ "JOIN t.routes r "
			+ "JOIN r.stops s "
			+ "JOIN s.stationOnLineId sol "
			+ "JOIN sol.stationId st "
			+ "WHERE tr.transport.routes.routeId in "
			+ "(SELECT s1.routeId.routeId "
			+ "FROM Stops s1, Stops s2 "
			+ "WHERE s1.stationOnLineId.stationId.stationName LIKE ?1 "
			+ "and s2.stationOnLineId.stationId.stationName LIKE ?2 "
			+ "and s1.stationOnLineId.lineId.lineId = s2.stationOnLineId.lineId.lineId "
			+ "and s2.stationOnLineId.stationOrderNum > s1.stationOnLineId.stationOrderNum "
			+ "and tr.startDate = ?3"
			+ ") and (sol.stationId.stationName LIKE ?1 or sol.stationId.stationName LIKE ?2) "
			+ "GROUP BY tr.tripId";

	@Id
	@Column(name = "TRANSPORTID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer transportId;

	@Column(name = "TRANSPORTCODE", nullable = false, length = 20)
	private String transportCode;

	// @DateTimeFormat(pattern="hh:mm:ss")
	@Column(name = "STARTTIME", nullable = false)
	private Time startTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROUTEID")
	private Routes routes;
	@Column(name = "SEATCLASS1", nullable = false)
	private int seatclass1;

	@Column(name = "SEATCLASS2", nullable = false)
	private int seatclass2;

	@Column(name = "SEATCLASS3", nullable = false)
	private int seatclass3;

	@Column(name = "GENPRICE", nullable = false)
	private double genPrice;

	@OneToMany(mappedBy = "transport", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Set<Trips> trips;

	public Transports() {
		super();
	}

	public Transports(String transportCode, Time startTime, Routes routes,
			int seatclass1, int seatclass2, int seatclass3, double genPrice) {
		this();
		this.setTransportCode(transportCode);
		this.setStartTime(startTime);
		this.setRoutes(routes);
		this.setSeatclass1(seatclass1);
		this.setSeatclass2(seatclass2);
		this.setSeatclass3(seatclass3);
		this.setGenPrice(genPrice);
	}

	/**
	 * @return the transport Id
	 */
	public Integer getTransportId() {
		return transportId;
	}

	/**
	 * @param transportId
	 *            the transport Id to set
	 */
	public void setTransportId(Integer transportId) {
		this.transportId = transportId;
	}

	/**
	 * @return the transport code
	 */
	public String getTransportCode() {
		return transportCode;
	}

	/**
	 * @param transportCode
	 *            the transport code to set
	 */
	public void setTransportCode(String transportCode) {
		// Assert.hasText(transportCode,
		// "Transport Code must not be null or empty");
		this.transportCode = transportCode;
	}

	/**
	 * @return the start time
	 */
	public Time getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the start time to set
	 */
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the route
	 */
	public Routes getRoutes() {
		return routes;
	}

	/**
	 * @param routes
	 *            the route to set
	 */
	public void setRoutes(Routes routes) {
		this.routes = routes;
	}

	/**
	 * @return the seat class 1
	 */
	public int getSeatclass1() {
		return seatclass1;
	}

	/**
	 * @param seatclass1
	 *            the seat class 1 to set
	 */
	public void setSeatclass1(int seatclass1) {
		this.seatclass1 = seatclass1;
	}

	/**
	 * @return the seat class 2
	 */
	public int getSeatclass2() {
		return seatclass2;
	}

	/**
	 * @param seatclass2
	 *            the seat class 2 to set
	 */
	public void setSeatclass2(int seatclass2) {
		this.seatclass2 = seatclass2;
	}

	/**
	 * @return the seat class 3
	 */
	public int getSeatclass3() {
		return seatclass3;
	}

	/**
	 * @param seatclass3
	 *            the seat class 3 to set
	 */
	public void setSeatclass3(int seatclass3) {
		this.seatclass3 = seatclass3;
	}

	/**
	 * @return the general price
	 */
	public double getGenPrice() {
		return genPrice;
	}

	/**
	 * @param genprice
	 *            the general price to set
	 */
	public void setGenPrice(double genprice) {
		this.genPrice = genprice;
	}

	/**
	 * @return the trips
	 */
	public Set<Trips> getTrips() {
		return trips;
	}

	/**
	 * @param trips
	 *            the trips to set
	 */
	public void setTrips(Set<Trips> trips) {
		this.trips = trips;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(transportCode).append(startTime)
				.append(routes).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transports other = (Transports) obj;

		return new EqualsBuilder().append(transportCode, other.transportCode)
				.append(startTime, other.startTime)
				.append(routes, other.routes)
				.append(transportId, other.transportId).isEquals();
	}
}
