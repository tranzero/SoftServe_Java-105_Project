package com.ita.edu.softserve.entity;

import java.util.Date;

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
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 
 * @author dnycktc
 * 
 *         Entity class for trips
 * 
 */

@Entity
@Table(name = "trips")
@NamedQueries({
		@NamedQuery(name = Trips.TRIPS_FIND_ALL, query = Trips.TRIPS_FIND_ALL_QUERY),
		@NamedQuery(name = Trips.TRIPS_FIND_COUNT, query = Trips.TRIPS_FIND_COUNT_QUERY),
		@NamedQuery(name = Trips.TRIPS_FIND_CRITERIA_COUNT, query = Trips.TRIPS_FIND_CRITERIA_COUNT_QUERY),
		@NamedQuery(name = Trips.FIND_BY_TRANSPORTID, query = Trips.FIND_BY_TRANSPORTID_QUERY)
		
	})
public class Trips extends BaseEntity {
	/**
	 * Defines the name of transport code parameter
	 */
	public static final String TRANSPORT_CODE_NAME = "transportCode";
	/**
	 * Defines the name of route name parameter
	 */
	public static final String ROUTE_NAME_NAME = "routeName";

	/**
	 * Defines the name of remaining seat class 1 parameter
	 */
	public static final String REM_SEAT_CLASS_1_NAME = "remSeatClass1";
	/**
	 * Defines the name of remaining seat class 2 parameter
	 */
	public static final String REM_SEAT_CLASS_2_NAME = "remSeatClass2";
	/**
	 * Defines the name of remaining seat class 3 parameter
	 */
	public static final String REM_SEAT_CLASS_3_NAME = "remSeatClass3";
	/**
	 * Defines the name of minimum date parameter
	 */
	public static final String MIN_DATE_NAME = "minDate";
	/**
	 * Defines the name of maximum date parameter
	 */
	public static final String MAX_DATE_NAME = "maxDate";

	/**
	 * Defines the name of known trip parameter
	 */
	public static final String KNOWN_TRIP_NAME = "knownTrip";
	
	/**
	 * Name of query which is used for selecting trips from DB. Compatible with
	 * paging.
	 */
	public static final String TRIPS_FIND_ALL = "Trips.findAll";
	/**
	 * Query which is used for selecting trips from DB. Compatible with paging.
	 */
	public static final String TRIPS_FIND_ALL_QUERY = "SELECT tr FROM Trips tr";

	// /**
	// * Name of query which is used for selecting trips from DB using criteria.
	// Compatible with
	// * paging.
	// */
	// public static final String TRIPS_FIND_BY_CRITERIA =
	// "Trips.findByCriteria";
	/**
	 * Query which is used for selecting trips from DB using criteria.
	 * Compatible with paging.
	 */
	public static final String TRIPS_FIND_BY_CRITERIA_QUERY = "SELECT tr FROM Trips tr WHERE"
			+ " tr.transport.transportCode LIKE :"
			+ TRANSPORT_CODE_NAME
			+ " AND tr.transport.routes.routeName LIKE :"
			+ ROUTE_NAME_NAME
			+ " AND tr.remSeatClass1 >= :"
			+ REM_SEAT_CLASS_1_NAME
			+ " AND tr.remSeatClass2 >= :"
			+ REM_SEAT_CLASS_2_NAME
			+ "  AND tr.remSeatClass3 >= :"
			+ REM_SEAT_CLASS_3_NAME
			+ " AND tr.startDate BETWEEN :"
			+ MIN_DATE_NAME
			+ " AND :"
			+ MAX_DATE_NAME + " ORDER BY ";

	/**
	 * Name of query which is used for selecting count of trips from DB with
	 * criteria. Used in paging.
	 */
	public static final String TRIPS_FIND_CRITERIA_COUNT = "Trips.findCriteriaCount";

	/**
	 * Query which is used for selecting count of trips from DB with criteria.
	 * Used in paging.
	 */
	public static final String TRIPS_FIND_CRITERIA_COUNT_QUERY = "SELECT COUNT(tr.tripId) FROM Trips tr WHERE"
			+ " tr.transport.transportCode LIKE :"
			+ TRANSPORT_CODE_NAME
			+ " AND tr.transport.routes.routeName LIKE :"
			+ ROUTE_NAME_NAME
			+ " AND tr.remSeatClass1 >= :"
			+ REM_SEAT_CLASS_1_NAME
			+ " AND tr.remSeatClass2 >= :"
			+ REM_SEAT_CLASS_2_NAME
			+ "  AND tr.remSeatClass3 >= :"
			+ REM_SEAT_CLASS_3_NAME
			+ " AND tr.startDate BETWEEN :"
			+ MIN_DATE_NAME
			+ " AND :"
			+ MAX_DATE_NAME;

	/**
	 * Query which is used for selecting index of trip from DB with criteria.
	 * Used in paging.
	 */
	public static final String TRIPS_FIND_CRITERIA_INDEX_QUERY = "SELECT COUNT(tr.tripId) FROM Trips tr WHERE "
			+ "tr < :"
			+ KNOWN_TRIP_NAME
			+ " AND tr.transport.transportCode LIKE :"
			+ TRANSPORT_CODE_NAME
			+ " AND tr.transport.routes.routeName LIKE :"
			+ ROUTE_NAME_NAME
			+ " AND tr.remSeatClass1 >= :"
			+ REM_SEAT_CLASS_1_NAME
			+ " AND tr.remSeatClass2 >= :"
			+ REM_SEAT_CLASS_2_NAME
			+ "  AND tr.remSeatClass3 >= :"
			+ REM_SEAT_CLASS_3_NAME
			+ " AND tr.startDate BETWEEN :"
			+ MIN_DATE_NAME
			+ " AND :"
			+ MAX_DATE_NAME + " ORDER BY ";
	
	
	/**
	 * Name of query which is used for selecting count of trips from DB. Used in
	 * paging.
	 */
	public static final String TRIPS_FIND_COUNT = "Trips.findCount";

	/**
	 * Query which is used for selecting count of trips from DB. Used in paging.
	 */
	public static final String TRIPS_FIND_COUNT_QUERY = "SELECT COUNT(tr.tripId) FROM Trips tr";

	/**
	 * Name of query which is used for selecting trips relative to some
	 * transport from DB.
	 */
	public static final String FIND_BY_TRANSPORTID = "Trips.findByTransportId";
	/**
	 * Query which is used for selecting trips relative to some transport from
	 * DB.
	 */
	public static final String FIND_BY_TRANSPORTID_QUERY = "SELECT tr FROM Trips tr WHERE tr.transport.transportId = ?1";

	
	
	/**
	 * Identifier field
	 */

	@Id
	@Column(name = "TRIPID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tripId;

	/**
	 * Transport, related to trip
	 */

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TRANSPORTID")
	private Transports transport;

	/**
	 * Remaining quantity of seats (class 1)
	 */

	@Column(name = "REMSEATCLASS1", nullable = false)
	private Integer remSeatClass1;

	/**
	 * Remaining quantity of seats (class 2)
	 */

	@Column(name = "REMSEATCLASS2", nullable = false)
	private Integer remSeatClass2;

	/**
	 * Remaining quantity of seats (class 3)
	 */

	@Column(name = "REMSEATCLASS3", nullable = false)
	private Integer remSeatClass3;

	/**
	 * Day when trip starts
	 */

	@Column(name = "STARTDATE", nullable = false)
	private Date startDate;

	/**
	 * Default constructor for trips
	 */

	public Trips() {
		super();

	}

	/**
	 * Full arguments constructor for trips
	 * 
	 * @param transport
	 *            Transport, related to trip
	 * @param remSeatClass1
	 *            Remaining quantity of seats (class 1)
	 * @param remSeatClass2
	 *            Remaining quantity of seats (class 2)
	 * @param remSeatClass3
	 *            Remaining quantity of seats (class 3)
	 * @param startDate
	 *            Day when trip starts
	 */

	public Trips(Transports transport, Integer remSeatClass1,
			Integer remSeatClass2, Integer remSeatClass3, Date startDate) {
		super();
		this.transport = transport;
		this.remSeatClass1 = remSeatClass1;
		this.remSeatClass2 = remSeatClass2;
		this.remSeatClass3 = remSeatClass3;
		this.startDate = startDate;
	}

	/**
	 * Constructor with minimum arguments
	 * 
	 * @param transport
	 *            Transport, related to trip
	 * @param startDateDay
	 *            when trip starts
	 */

	public Trips(Transports transport, Date startDate) {
		super();
		this.transport = transport;
		this.remSeatClass1 = transport.getSeatclass1();
		this.remSeatClass2 = transport.getSeatclass2();
		this.remSeatClass3 = transport.getSeatclass3();
		this.startDate = startDate;
	}

	/**
	 * @return the tripId
	 */
	public Integer getTripId() {
		return tripId;
	}

	/**
	 * @return the transportId
	 */
	public Transports getTransport() {
		return transport;
	}

	/**
	 * @param transportId
	 *            the transportId to set
	 */
	public void setTransport(Transports transport) {
		this.transport = transport;
	}

	/**
	 * @return the remSeatClass1
	 */
	public Integer getRemSeatClass1() {
		return remSeatClass1;
	}

	/**
	 * @param remSeatClass1
	 *            the remSeatClass1 to set
	 */
	public void setRemSeatClass1(Integer remSeatClass1) {
		this.remSeatClass1 = remSeatClass1;
	}

	/**
	 * @return the remSeatClass2
	 */
	public Integer getRemSeatClass2() {
		return remSeatClass2;
	}

	/**
	 * @param remSeatClass2
	 *            the remSeatClass2 to set
	 */
	public void setRemSeatClass2(Integer remSeatClass2) {
		this.remSeatClass2 = remSeatClass2;
	}

	/**
	 * @return the remSeatClass3
	 */
	public Integer getRemSeatClass3() {
		return remSeatClass3;
	}

	/**
	 * @param remSeatClass3
	 *            the remSeatClass3 to set
	 */
	public void setRemSeatClass3(Integer remSeatClass3) {
		this.remSeatClass3 = remSeatClass3;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Hash code function for trips
	 */

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(transport).append(startDate)
				.hashCode();
	}

	/**
	 * Equals function for trips
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trips other = (Trips) obj;

		return new EqualsBuilder().append(transport, other.transport)
				.append(startDate, other.startDate).isEquals();
	}

	@Override
	public String toString() {
		return "" + tripId;
	}
}
