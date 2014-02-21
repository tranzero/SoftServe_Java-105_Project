package com.ita.edu.softserve.entity;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the responses database table.
 * 
 */
@Entity
@Table(name="responses")
@NamedQueries({
	@NamedQuery(name = Responses.FIND_BY_ROUTE_ID, query = Responses.FIND_BY_ROUTE_ID_QUERY),
	@NamedQuery(name = Responses.FIND_BY_TRIP_ID, query = Responses.FIND_BY_TRIP_ID_QUERY),
	@NamedQuery(name = Responses.FIND_BY_TRANSPORT_ID, query = Responses.FIND_BY_TRANSPORT_ID_QUERY)
})
public class Responses extends BaseEntity {

	public static final String FIND_BY_ROUTE_ID = "Responses.findByRouteId";
	public static final String FIND_BY_ROUTE_ID_QUERY = "SELECT r FROM Responses AS r WHERE r.trip.transport.routes.routeId = ?1";

	public static final String FIND_BY_TRIP_ID = "Responses.findByTripId";
	public static final String FIND_BY_TRIP_ID_QUERY = "SELECT r FROM Responses AS r WHERE r.trip.tripId = ?1";
	
	public static final String FIND_BY_TRANSPORT_ID = "Responses.findByTranportId";
	public static final String FIND_BY_TRANSPORT_ID_QUERY = "SELECT r FROM Responses AS r WHERE r.trip.transport.transportId = ?1";

	@Id
	@Column(name = "RESPONSEID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer responseid;

	@Column(name="`COMMENT`", nullable = false, length = 200)
	private String comment;

	@Column(name = "`DATE`", nullable = false)
	private Date date;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USERID")
	private Users user;

	//bi-directional many-to-one association to Trip
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="TRIPID")
	private Trips trip;

	@Column(name = "`checked`", nullable = false, columnDefinition="TINYINT(1) default 'false'")
	private Boolean checked;

	public Responses() {
	}

	public Integer getResponseid() {
		return this.responseid;
	}

	public void setResponseid(Integer responseid) {
		this.responseid = responseid;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Users getUser() {
		return this.user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Trips getTrip() {
		return this.trip;
	}

	public void setTrip(Trips trip) {
		this.trip = trip;
	}

	public Boolean isChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

}