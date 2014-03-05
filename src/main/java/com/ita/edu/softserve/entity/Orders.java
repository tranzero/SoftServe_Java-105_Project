package com.ita.edu.softserve.entity;

import java.io.Serializable;
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
 * @author nvrubl
 * 
 *         The persistent class for the ORDERS database table.
 */
@Entity
@Table(name = "orders")
@NamedQueries({
		@NamedQuery(name = Orders.FIND_ALL, query = Orders.FIND_ALL_QUERY),
		@NamedQuery(name = Orders.FIND_BY_USERID, query = Orders.FIND_BY_USERID_QUERY),
		@NamedQuery(name = Orders.FIND_ORDER_LIST_COUNT, query = Orders.FIND_ORDER_LIST_COUNT_QUERY),
		@NamedQuery(name = Orders.FIND_ORDER_LIST_FOR_PAGING, query = Orders.FIND_ORDER_LIST_FOR_PAGING_QUERY),
		@NamedQuery(name = Orders.FIND_BY_USERID_AND_ORDER_DATE, query = Orders.FIND_BY_USERID_AND_ORDER_DATE_QUERY)})
public class Orders extends BaseEntity {

	
	
	public static final String FIND_ORDER_LIST_COUNT = "Orders.findOrdersListCount";
	public static final String FIND_ORDER_LIST_COUNT_QUERY = "SELECT COUNT (o.orderId) FROM Orders o";

	public static final String FIND_ORDER_LIST_FOR_PAGING = "Orders.findOrdersListForPaging";
	public static final String FIND_ORDER_LIST_FOR_PAGING_QUERY = "SELECT o FROM Orders o ORDER BY o.orderId";

	public static final String FIND_BY_USERID = "Orders.findByUserId";
	public static final String FIND_BY_USERID_QUERY = "SELECT o FROM Orders o WHERE o.userId.userId = ?1";

	public static final String FIND_ALL = "Orders.findAll";
	public static final String FIND_ALL_QUERY = "SELECT o FROM Orders o";
	
	public static final String FIND_BY_USERID_AND_ORDER_DATE = "Orders.findByUserIdAndOrderDate";
	public static final String FIND_BY_USERID_AND_ORDER_DATE_QUERY = "select o from Orders o where o.userId.userId = ?1 and o.orderDate = ?2";

	@Id
	@Column(name = "ORDERID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USERID")
	private Users userId;
	
	@Column(name = "ORDERDATE")
	private Date orderDate;
	
	
	/**
	 * Default Constructor
	 */
	public Orders() {
		this.setOrderDate();
	}

	/**
	 * Constructor with parameters:
	 * 
	 * @param orderId
	 * @param userId
	 * @param tripId
	 */
	public Orders(Users userId) {
		super();
		this.userId=userId;
	}
	

	public Orders(Users userId, Date orderDate) {
		super();
		this.userId = userId;
		this.orderDate = orderDate;
	}

	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * @param the
	 *            orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the userId
	 */
	public Users getUserId() {
		return userId;
	}

	/**
	 * @param the
	 *            userId the userId to set
	 */
	public void setUserId(Users userId) {
		this.userId = userId;
	}
	
	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	/**
	 * @param orderDate the orderDate to set
	 */
	private void setOrderDate() {
		this.orderDate=new Date();
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(orderId).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		return new EqualsBuilder().append(orderId, other.orderId).isEquals();
	}

	@Override
	public String toString() {
		return "orderId=" + orderId + ", userId=" + userId.getUserId() + ", oderDate="
				+ orderDate + " ";
	}

	
}
