package com.ita.edu.softserve.entity;

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



/**
 * @author admin _ The persistent class for the ROUTES database table.
 */
@Entity
@Table(name = "routes")
@NamedQueries({
		@NamedQuery(name = Routes.ROUTES_FIND_ALL, query = Routes.ROUTES_FIND_ALL_QUERY),
		@NamedQuery(name = Routes.ROUTES_ALL_ORDER_BY, query = Routes.ROUTES_ALL_ORDER_BY_QUERY),
		@NamedQuery(name = Routes.FIND_BY_CODE, query = Routes.FIND_BY_CODE_QUERY),
		@NamedQuery(name = Routes.ROUTES_FIND_COUNT, query = Routes.ROUTES_FIND_COUNT_QUERY),
		@NamedQuery(name = Routes.FIND_BY_LINEID, query = Routes.FIND_BY_LINEID_QUERY),
		@NamedQuery(name = Routes.FIND_BY_ARRIVING_STATION_NAME_AND_TIME_INTERVAL, query = Routes.GET_ROUTES_BY_ARRIVING_STATION_NAME_AND_TIME_INTERVAL),
		@NamedQuery(name = Routes.FIND_BY_DEPARTING_STATION_NAME_AND_TIME_INTERVAL, query = Routes.GET_ROUTES_BY_DEPARTING_STATION_NAME_AND_TIME_INTERVAL),
		@NamedQuery(name = Routes.GET_ROUTES_BY_ARRIVING_STATION_NAME_AND_TIME_INTERVAL_COUNT, query = Routes.GET_ROUTES_BY_ARRIVING_STATION_NAME_AND_TIME_INTERVAL_QUERY),
		@NamedQuery(name = Routes.GET_ROUTES_BY_DEPARTING_STATION_NAME_AND_TIME_INTERVAL_COUNT, query = Routes.GET_ROUTES_BY_DEPARTING_STATION_NAME_AND_TIME_INTERVAL_QUERY) })
public class Routes extends BaseEntity {
	/**
	 * Defines the name of station name parameter
	 */
	public static final String STATION_NAME = "stationName";
	public static final String LINE_NAME = "lineName";
	public static final String LINE_ID = "lineId";

	public static final String FIND_BY_CODE = "Routes.findByCode";
	public static final String FIND_BY_CODE_QUERY = "SELECT r FROM Routes r WHERE r.routeCode = ?1";
	
	public static final String ROUTES_FIND_ALL = "Routes.RoutesfindAll";
	public static final String ROUTES_FIND_ALL_QUERY = "SELECT r FROM Routes r";
	
	public static final String STATIONS_NAME_FIND_BY_CRITERIA_QUERY = "SELECT st.stationName FROM Stations st WHERE"
			+ " st.stationName LIKE :"
			+ STATION_NAME
			+ " ORDER BY st.stationName";
	
	public static final String STATIONS_NAME_ON_LINE_FIND_BY_CRITERIA_QUERY = 
			 "SELECT st.stationName FROM StationOnLine sol"+
			" INNER JOIN sol.stationOnLineId st";
			// + " FROM StationOnLine sol "
			// + "INNER JOIN sol.stationOnLineId st "
			// + "INNER JOIN sol.stationId st ";
	
		//	  "SELECT stl.lineId.lineName"
			//+ " FROM StationOnLine stl"
		//	+ " INNER JOIN stl.stationId st";
		/*	+ " WHERE st.stationName LIKE :"
			+ STATION_NAME
			+ " AND sol.lineId = 1"
			+ " ORDER BY st.stationName";*/
	
	public static final String LINE_NAME_FIND_BY_CRITERIA_QUERY = "SELECT l.lineName FROM Lines l WHERE"
			+ " l.lineName LIKE :"
			+ LINE_NAME
			+ " ORDER BY l.lineName";

	public static final String ROUTES_ALL_ORDER_BY = "Routes.RoutesAllOrderBy";
	public static final String ROUTES_ALL_ORDER_BY_QUERY = "SELECT r FROM Routes r ORDER BY r.routeCode";

	public static final String ROUTES_FIND_COUNT = "Routes.findCount";
	public static final String ROUTES_FIND_COUNT_QUERY = "SELECT COUNT (r.routeId) FROM Routes r";

	public static final String FIND_BY_LINEID = "Routes.findByLineId";
	public static final String FIND_BY_LINEID_QUERY = "SELECT r FROM Routes r WHERE r.lineId.lineId = ?1";

	private static final String FIND_ROUTES_TRIP =
			  "FROM Routes r "
			+ "INNER JOIN r.transports t "
			+ "INNER JOIN r.stops s "
			+ "INNER JOIN s.stationOnLineId sol "
			+ "INNER JOIN sol.stationId st "
			+ "WHERE st.stationName = ?1 AND ";

	public static final String FIND_BY_ARRIVING_STATION_NAME_AND_TIME_INTERVAL = "Routes.findByArrivingStationNameAndTimeInterval";
	public static final String GET_ROUTES_BY_ARRIVING_STATION_NAME_AND_TIME_INTERVAL = "SELECT "
			+ "NEW com.ita.edu.softserve.manager.impl.RouteTrip(r, TIME(SUBTIME(ADDTIME(NOW(),ADDTIME(t.startTime, s.arrival)), CURTIME()))) "
			+ FIND_ROUTES_TRIP
			+ "TIME(SUBTIME(ADDTIME(NOW(),ADDTIME(t.startTime, s.arrival)), CURTIME())) "
			+ "BETWEEN ?2 AND ?3";

	public static final String FIND_BY_DEPARTING_STATION_NAME_AND_TIME_INTERVAL = "Routes.findByDepartingStationNameAndTimeInterval";
	public static final String GET_ROUTES_BY_DEPARTING_STATION_NAME_AND_TIME_INTERVAL = "SELECT "
			+ "new com.ita.edu.softserve.manager.impl.RouteTrip(r, TIME(SUBTIME(ADDTIME(NOW(),ADDTIME(t.startTime, s.departure)), CURTIME()))) "
			+ FIND_ROUTES_TRIP
			+ "TIME(SUBTIME(ADDTIME(NOW(),ADDTIME(t.startTime, s.departure)), CURTIME())) "
			+ "BETWEEN ?2 AND ?3";

	public static final String GET_ROUTES_BY_ARRIVING_STATION_NAME_AND_TIME_INTERVAL_COUNT = "Routes.findByArrivingStationNameAndTimeIntervalCount";
	public static final String GET_ROUTES_BY_ARRIVING_STATION_NAME_AND_TIME_INTERVAL_QUERY = "SELECT "
			+ "COUNT(r.routeId) "
			+ FIND_ROUTES_TRIP
			+ "TIME(SUBTIME(ADDTIME(NOW(),ADDTIME(t.startTime, s.arrival)), CURTIME())) "
			+ "BETWEEN ?2 AND ?3";

	public static final String GET_ROUTES_BY_DEPARTING_STATION_NAME_AND_TIME_INTERVAL_COUNT = "Routes.findByDepartingStationNameAndTimeIntervalCount";
	public static final String GET_ROUTES_BY_DEPARTING_STATION_NAME_AND_TIME_INTERVAL_QUERY = "SELECT "
			+ "COUNT(r.routeId) "
			+ FIND_ROUTES_TRIP
			+ "TIME(SUBTIME(ADDTIME(NOW(),ADDTIME(t.startTime, s.departure)), CURTIME())) "
			+ "BETWEEN ?2 AND ?3";

	@Id
	@Column(name = "ROUTEID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer routeId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LINEID")
	private Lines lineId;

	@OneToMany(mappedBy = "routeId", fetch = FetchType.LAZY)
	private Set<Stops> stops;

	@Column(name = "ROUTECODE", length = 20)
	private String routeCode;
	
	@Column(name = "ROUTENAME", length = 53)
	private String routeName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STATIONSTARTID")
	private Stations stationStartId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STATIONENDID")
	private Stations stationEndId;

	@OneToMany(mappedBy = "routes", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Set<Transports> transports;
	
	/**
	 * Default Constructor
	 */
	public Routes() {
	}

	/**
	 * @return the routeId
	 */
	public Integer getRouteId() {
		return routeId;
	}

	/**
	 * @param routeId
	 *            the routeId to set
	 */
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	/**
	 * @return the lineId
	 */
	public Lines getLineId() {
		return lineId;
	}

	/**
	 * @param lineId
	 *            the lineId to set
	 */
	public void setLineId(Lines lineId) {
		this.lineId = lineId;
	}

	/**
	 * @return the stops
	 */
	public Set<Stops> getStops() {
		return stops;
	}

	/**
	 * @param stops
	 *            the stops to set
	 */
	public void setStops(Set<Stops> stops) {
		this.stops = stops;
	}

	/**
	 * @return the routeCode
	 */
	public String getRouteCode() {
		return routeCode;
	}

	/**
	 * @param routeCode
	 *            the routeCode to set
	 */
	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}
	
	/**
	 * @return the routeName
	 */
	public String getRouteName() {
		return routeName;
	}
	
	/**
	 * @param routeName
	 *            the routeName to set
	 */
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	
	/**
	 * @return the stationStartId
	 */
	public Stations getStationStartId() {
		return stationStartId;
	}

	/**
	 * @param stationStartId
	 *            the stationStartId to set
	 */
	public void setStationStartId(Stations stationStartId) {
		this.stationStartId = stationStartId;
	}
	
	/**
	 * @return the stationEndId
	 */
	public Stations getStationEndId() {
		return stationEndId;
	}

	/**
	 * @param stationEndId
	 *            the stationEndId to set
	 */
	public void setStationEndId(Stations stationEndId) {
		this.stationEndId = stationEndId;
	}

	@Override
	public int hashCode() {
		return routeId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Routes other = (Routes) obj;

		return routeId.equals(other.routeId);
	}

}