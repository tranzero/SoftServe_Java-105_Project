package com.ita.edu.softserve.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * @author admin
 * 
 *         The persistent class for the STATIONS database table.
 */

@Entity
@Table(name = "stations")
@NamedQuery(name = Stations.FIND_BY_NAME, query = Stations.FIND_BY_NAME_QUERY)
public class Stations extends BaseEntity {

	public static final String FIND_BY_NAME = "Station.findByName";
	public static final String FIND_BY_NAME_QUERY = "SELECT u FROM Stations u WHERE u.stationName = ?1";

	@Id
	@Column(name = "STATIONID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int stationId;

	@Column(name = "STATIONCODE", unique = true)
	private String stationCode;

	@Column(name = "STATIONNAME", length = 100)
	private String stationName;

	/* Bi-directional one-to-many association to StationsOnLine */
	@OneToMany(mappedBy = "stationId", fetch = FetchType.LAZY)
	private Set<StationsOnLine> stationsOnLines = new HashSet<StationsOnLine>();

	/**
	 * Default Constructor
	 */
	public Stations() {
	}

	/**
	 * @param stationCode
	 * @param stationName
	 *            Constructor with parametrs stationCode, stationName.
	 */
	public Stations(String stationCode, String stationName) {
		this.stationCode = stationCode;
		this.stationName = stationName;
	}

	/**
	 * @return the stationId
	 */
	public int getStationId() {
		return stationId;
	}

	/**
	 * @param stationId
	 *            the stationId to set
	 */
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	/**
	 * @return the stationCode
	 */
	public String getStationCode() {
		return stationCode;
	}

	/**
	 * @param stationCode
	 *            the stationCode to set
	 */
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	/**
	 * @return the stationName
	 */
	public String getStationName() {
		return stationName;
	}

	/**
	 * @param stationName
	 *            the stationName to set
	 */
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	/**
	 * @return the stationsOnLines
	 */
	public Set<StationsOnLine> getStationsOnLines() {
		return stationsOnLines;
	}

	/**
	 * @param stationsOnLines
	 *            the stationsOnLines to set
	 */
	public void setStationsOnLines(Set<StationsOnLine> stationsOnLines) {
		this.stationsOnLines = stationsOnLines;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((stationName == null) ? 0 : stationName.hashCode());
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
		Stations other = (Stations) obj;
		if (stationName == null) {
			if (other.stationName != null)
				return false;
		} else if (!stationName.equals(other.stationName))
			return false;
		return true;
	}

}
