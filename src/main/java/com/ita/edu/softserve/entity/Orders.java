package com.ita.edu.softserve.entity;

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


@Entity
@Table(name = "orders")
@NamedQueries({
	@NamedQuery(name = Orders.FIND_BY_USERID, query = Orders.FIND_BY_USERID_QUERY) })
public class Orders extends BaseEntity {
	
	public static final String FIND_BY_USERID = "Orders.findByUserId";
	public static final String FIND_BY_USERID_QUERY = "SELECT o FROM Orders o WHERE o.userId.userId = ?1";

	
	@Id
	@Column(name = "ORDERID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USERID")
	private Users userId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TRIPID")
	private Trips tripId;

	public Orders() {
		super();
	}

	public Orders(int orderId, Users userId, Trips tripId) {
		super();
		this.setOrderId(orderId);
		this.setTripId(tripId);
		this.setUserId(userId);
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

	public Trips getTripId() {
		return tripId;
	}

	public void setTripId(Trips tripId) {
		this.tripId = tripId;
	}

	@Override
	public String toString() {
		return "orderId=" + orderId + ", userId=" + userId
				+ ", tripId=" + tripId + " ";
	}

}
