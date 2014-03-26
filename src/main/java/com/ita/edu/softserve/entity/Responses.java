package com.ita.edu.softserve.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the responses database table.
 * 
 */
@Entity
@Table(name = "responses")
@NamedQueries({
		@NamedQuery(name = Responses.FIND_BY_ROUTE_ID, query = Responses.FIND_BY_ROUTE_ID_QUERY),
		@NamedQuery(name = Responses.FIND_BY_TRIP_ID, query = Responses.FIND_BY_TRIP_ID_QUERY),
		@NamedQuery(name = Responses.FIND_BY_TRANSPORT_ID, query = Responses.FIND_BY_TRANSPORT_ID_QUERY),
		@NamedQuery(name = Responses.FIND_UNCHECKED_RESPONSES, query = Responses.FIND_UNCHECKED_RESPONSES_QUERY) })
public class Responses extends BaseEntity {

	/**
	 * Name of query to find responses by routeId
	 */
	public static final String FIND_BY_ROUTE_ID = "Responses.findByRouteId";

	/**
	 * Query to find responses by routeId
	 */
	public static final String FIND_BY_ROUTE_ID_QUERY = "SELECT r FROM Responses AS r WHERE r.trip.transport.routes.routeId = ?1";

	/**
	 * Name of query to find responses by tripId
	 */
	public static final String FIND_BY_TRIP_ID = "Responses.findByTripId";

	/**
	 * Query to find responses by tripId
	 */
	public static final String FIND_BY_TRIP_ID_QUERY = "SELECT r FROM Responses AS r WHERE r.trip.tripId = ?1";

	/**
	 * Name of query to find responses by transportId
	 */
	public static final String FIND_BY_TRANSPORT_ID = "Responses.findByTranportId";
	/**
	 * Query to find responses by transportId
	 */
	public static final String FIND_BY_TRANSPORT_ID_QUERY = "SELECT r FROM Responses AS r WHERE r.trip.transport.transportId = ?1";

	/**
	 * Name of query to find unchecked responses
	 */
	public static final String FIND_UNCHECKED_RESPONSES = "Responses.findUncheckedResponses";

	/**
	 * Query to find unchecked responses
	 */
	public static final String FIND_UNCHECKED_RESPONSES_QUERY = "SELECT r FROM Responses AS r WHERE r.checked = 'FALSE'";

	@Id
	@Column(name = "RESPONSEID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer responseid;

	@Column(name = "`COMMENT`", nullable = false, length = 200)
	private String comment;

	@Column(name = "`DATE`", nullable = false)
	private Date date;

	// bi-directional many-to-one association to User
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USERID")
	private Users user;

	// bi-directional many-to-one association to Trip
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TRIPID")
	private Trips trip;

	@Column(name = "`checked`", nullable = false, columnDefinition = "TINYINT(1) default 'false'")
	private Boolean checked;

	public Responses() {
		this.user = null;
		this.trip = null;
		this.comment = null;
		this.date = null;
		this.checked = false;
	}
	
	/**
	 * Constructor for creating response by user and trip
	 * 
	 * @param user - <code>User</code> object who create response
	 * @param trip - <code>Trip</code> object, that the response is for
	 * @param comment - comment text
	 * @param date - date of creating comment
	 */
	public Responses(Users user, Trips trip, String comment, Date date) {
		this.user = user;
		this.trip = trip;
		this.comment = comment;
		this.date = date;
		this.checked = false;
	}
	
	/**
	 * @return responseId
	 */
	public Integer getResponseid() {
		return this.responseid;
	}

	/*
	 * @param responseId - responseId to set
	 */
	public void setResponseid(Integer responseid) {
		this.responseid = responseid;
	}

	/**
	 * @return comment text
	 */
	public String getComment() {
		return this.comment;
	}

	/*
	 * @param comment - comment text to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/*
	 * @return date - date of response
	 */
	public Date getDate() {
		return this.date;
	}

	/*
	 * @param date - date of response to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/*
	 * @return user
	 */
	public Users getUser() {
		return this.user;
	}

	/*
	 * @param user - user to set
	 */
	public void setUser(Users user) {
		this.user = user;
	}

	/*
	 * @return trip
	 */
	public Trips getTrip() {
		return this.trip;
	}

	/*
	 * @param trip - trip to set
	 */
	public void setTrip(Trips trip) {
		this.trip = trip;
	}

	/*
	 * @return checked - <code>Boolean</code> value, that tells if response is
	 * checked already
	 */
	public Boolean isChecked() {
		return checked;
	}

	/*
	 * @param checked - <code>Boolean</code> value to set
	 */
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

}