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
 * 
 * @author Roman
 */
@Entity
@Table(name = "transports")
@NamedQueries({ 
		@NamedQuery(name = Transports.TRANSPORTS_FIND_ALL, query = Transports.TRANSPORTS_FIND_ALL_QUERY),
		@NamedQuery(name = Transports.TRANSPORTS_FIND_COUNT, query = Transports.TRANSPORTS_FIND_COUNT_QUERY),
		@NamedQuery(name = Transports.FIND_BY_TRANSPORTCODE, query = Transports.FIND_BY_TRANSPORTCODE_QUERY),
		@NamedQuery(name = Transports.FIND_BY_ROUTEID, query = Transports.FIND_BY_ROUTEID_QUERY),

		@NamedQuery(name = Transports.FIND_TRANSPORTS_LIST_BY_CRITERIA, query = Transports.FIND_TRANSPORTS_LIST_BY_CRITERIA_QUERY),
		
		@NamedQuery(name = Transports.FIND_BY_TWO_STATIONS, query = Transports.FIND_BY_TWO_STATIONS_QUERY),
		@NamedQuery(name = Transports.FIND_BY_TWO_STATIONS_AND_DATE, query = Transports.FIND_BY_TWO_STATIONS_AND_DATE_QUERY),/*,
		@NamedQuery(name = Transports.FIND_BY_TS_ORDER_BY_LNAME, query = Transports.FIND_BY_TS_ORDER_BY_LNAME_QUERY),
        @NamedQuery(name = Transports.FIND_BY_TS_ORDER_BY_TCODE, query = Transports.FIND_BY_TS_ORDER_BY_TCODE_QUERY),
        @NamedQuery(name = Transports.FIND_BY_TS_ORDER_BY_DEP, query = Transports.FIND_BY_TS_ORDER_BY_DEP_QUERY),
        @NamedQuery(name = Transports.FIND_BY_TS_ORDER_BY_DURATION, query = Transports.FIND_BY_TS_ORDER_BY_DURATION_QUERY)
        */
		@NamedQuery(name = Transports.FIND_BY_DATE, query = Transports.FIND_BY_DATE_QUERY),
})
public class Transports extends BaseEntity {

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
		
	public static final String FIND_TRANSPORTS_LIST_BY_CRITERIA = "Transports.findTransportsListByCriteria";
	public static final String FIND_TRANSPORTS_LIST_BY_CRITERIA_QUERY = "SELECT t FROM Transports t "
			+ "WHERE "
			+ "t.transportCode LIKE :transportCode "
			+ "OR "
			+ "t.startTime = :startTime "
			+ "OR "
			+ "t.routes.routeCode LIKE :routeCode "
			+ "OR "
			+ "t.seatclass1 > :seatclass1 "
			+ "OR "
			+ "t.seatclass2 > :seatclass2 "
			+ "OR "
			+ "t.seatclass3 > :seatclass3 "
			+ "OR "
			+ "t.genPrice < :genPrice "
			;

	/*------------------------------------------------------------------------------------------------------------*/

	public static final String FIND_BY_TWO_STATIONS = "Transports.findByTwoStations";
	public static final String FIND_BY_TWO_STATIONS_QUERY ="SELECT "
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
	
	public static final String FIND_BY_TWO_STATIONS_AND_DATE = "Transports.findByTwoStationsAndDate";
	public static final String FIND_BY_TWO_STATIONS_AND_DATE_QUERY ="SELECT "
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
/*
	public static final String FIND_BY_TS_ORDER_BY_LNAME = "Transports.findByTSOrderByLName";
    public static final String FIND_BY_TS_ORDER_BY_LNAME_QUERY ="SELECT "
			+ "NEW com.ita.edu.softserve.manager.impl.TransportTravel(t, TIME(TIME(s.departure) + TIME(t.startTime)), TIME(MAX(s.arrival)), TIME(TIME(MAX(s.arrival)) - TIME(s.departure))) "
            + "FROM Transports t "
            + "JOIN t.routes r "
            + "JOIN r.stops s "
            + "JOIN s.stationOnLineId sol "
            + "JOIN sol.stationId st "
            + "WHERE t.routes.routeId in "
            + "(SELECT s1.routeId.routeId "
            + "FROM Stops s1, Stops s2 "
            + "WHERE s1.stationOnLineId.stationId.stationName = ?1 "
            + "and s2.stationOnLineId.stationId.stationName = ?2 "
            + "and s1.stationOnLineId.lineId.lineId = s2.stationOnLineId.lineId.lineId "
            + "and s2.stationOnLineId.stationOrderNum > s1.stationOnLineId.stationOrderNum"
            + ") and (sol.stationId.stationName=?1 or sol.stationId.stationName=?2) "
            + "GROUP BY t.transportCode "
            + "ORDER BY t.routes.lineId.lineName";
	
	public static final String FIND_BY_TS_ORDER_BY_TCODE = "Transports.findByTwoStationsOrderByTCode";
	public static final String FIND_BY_TS_ORDER_BY_TCODE_QUERY ="SELECT "
			+ "NEW com.ita.edu.softserve.manager.impl.TransportTravel(t, TIME(TIME(s.departure) + TIME(t.startTime)), TIME(MAX(s.arrival)), TIME(TIME(MAX(s.arrival)) - TIME(s.departure))) "
			+ "FROM Transports t "
			+ "JOIN t.routes r "
			+ "JOIN r.stops s "
			+ "JOIN s.stationOnLineId sol "
			+ "JOIN sol.stationId st "
			+ "WHERE t.routes.routeId in "
			+ "(SELECT s1.routeId.routeId "
			+ "FROM Stops s1, Stops s2 "
			+ "WHERE s1.stationOnLineId.stationId.stationName = ?1 "
			+ "and s2.stationOnLineId.stationId.stationName = ?2 "
			+ "and s1.stationOnLineId.lineId.lineId = s2.stationOnLineId.lineId.lineId "
			+ "and s2.stationOnLineId.stationOrderNum > s1.stationOnLineId.stationOrderNum"
			+ ") and (sol.stationId.stationName=?1 or sol.stationId.stationName=?2) "
			+ "GROUP BY t.transportCode "
			+ "ORDER BY t.transportCode";

	public static final String FIND_BY_TS_ORDER_BY_DEP = "Transports.findByTwoStationsOrderByDep";
	public static final String FIND_BY_TS_ORDER_BY_DEP_QUERY ="SELECT "
			+ "NEW com.ita.edu.softserve.manager.impl.TransportTravel(t, TIME(TIME(s.departure) + TIME(t.startTime)), TIME(MAX(s.arrival)), TIME(TIME(MAX(s.arrival)) - TIME(s.departure))) "
			+ "FROM Transports t "
			+ "JOIN t.routes r "
			+ "JOIN r.stops s "
			+ "JOIN s.stationOnLineId sol "
			+ "JOIN sol.stationId st "
			+ "WHERE t.routes.routeId in "
			+ "(SELECT s1.routeId.routeId "
			+ "FROM Stops s1, Stops s2 "
			+ "WHERE s1.stationOnLineId.stationId.stationName = ?1 "
			+ "and s2.stationOnLineId.stationId.stationName = ?2 "
			+ "and s1.stationOnLineId.lineId.lineId = s2.stationOnLineId.lineId.lineId "
			+ "and s2.stationOnLineId.stationOrderNum > s1.stationOnLineId.stationOrderNum"
			+ ") and (sol.stationId.stationName=?1 or sol.stationId.stationName=?2) "
			+ "GROUP BY t.transportCode "
			+ "ORDER BY TIME(TIME(s.departure) + TIME(t.startTime))";

	public static final String FIND_BY_TS_ORDER_BY_DURATION = "Transports.findByTwoStationsOrderByDuration";
	public static final String FIND_BY_TS_ORDER_BY_DURATION_QUERY ="SELECT "
			+ "NEW com.ita.edu.softserve.manager.impl.TransportTravel(t, TIME(TIME(s.departure) + TIME(t.startTime)), TIME(MAX(s.arrival)), TIME(TIME(MAX(s.arrival)) - TIME(s.departure))) "
			+ "FROM Transports t "
			+ "JOIN t.routes r "
			+ "JOIN r.stops s "
			+ "JOIN s.stationOnLineId sol "
			+ "JOIN sol.stationId st "
			+ "WHERE t.routes.routeId in "
			+ "(SELECT s1.routeId.routeId "
			+ "FROM Stops s1, Stops s2 "
			+ "WHERE s1.stationOnLineId.stationId.stationName = ?1 "
			+ "and s2.stationOnLineId.stationId.stationName = ?2 "
			+ "and s1.stationOnLineId.lineId.lineId = s2.stationOnLineId.lineId.lineId "
			+ "and s2.stationOnLineId.stationOrderNum > s1.stationOnLineId.stationOrderNum"
			+ ") and (sol.stationId.stationName=?1 or sol.stationId.stationName=?2) "
			+ "GROUP BY t.transportCode "
			+ "ORDER BY MAX(s.arrival) DESC";
*/
	
	
	@Id
	@Column(name = "TRANSPORTID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer transportId;

	@Column(name = "TRANSPORTCODE", nullable = false, length = 20)
	private String transportCode;
	
//	@DateTimeFormat(pattern="hh:mm:ss")
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
	 * @param transportId the transport Id to set
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
	 * @param transportCode the transport code to set
	 */
	public void setTransportCode(String transportCode) {
//		Assert.hasText(transportCode, "Transport Code must not be null or empty");
		this.transportCode = transportCode;
	}

	/**
	 * @return the start time
	 */
	public Time getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the start time to set
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
	 * @param routes the route to set
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
	 * @param seatclass1 the seat class 1 to set
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
	 * @param seatclass2 the seat class 2 to set
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
	 * @param seatclass3 the seat class 3 to set
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
	 * @param genprice the general price to set
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
		return new HashCodeBuilder()
				.append(transportCode)
				.append(startTime)
				.append(routes)
				.hashCode();
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

		return new EqualsBuilder()
				.append(transportCode, other.transportCode)
				.append(startTime, other.startTime)
				.append(routes, other.routes)
				.append(transportId, other.transportId)
				.isEquals();
	}
}
