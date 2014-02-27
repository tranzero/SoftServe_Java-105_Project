package com.ita.edu.softserve.validationcontainers.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.validationcontainers.UserCriteriaContainer;
@Component
public class UsersCriteriaContainerImpl implements UserCriteriaContainer {
	private String searchString;
	private List<Role> roleArray;
	private Date minDate;
	private Date maxDate;
	private String minDateString;
	private String maxDateString;
	private Boolean isRegUser;
	private Boolean isManager;
	private Boolean isAdmin;
	private String orderByParam;
	private String orderByDirection;
	
	public UsersCriteriaContainerImpl(){
		super();
	}
	
	@Override
	public void setValuableInfo(String searchString, String minDateString,
			String maxDateString, Boolean isRegUser, Boolean isManager,
			Boolean isAdmin, String orderByParam, String orderByDirection) {
		this.searchString = searchString;
		this.minDateString = minDateString;
		this.maxDateString = maxDateString;
		this.isRegUser = isRegUser;
		this.isManager = isManager;
		this.isAdmin = isAdmin;
		this.orderByParam = orderByParam;
		this.orderByDirection = orderByDirection;
	}
	
	
	public UsersCriteriaContainerImpl(String searchString, String minDateString,
			String maxDateString, Boolean isRegUser, Boolean isManager,
			Boolean isAdmin, String orderByParam, String orderByDirection) {
		super();
		this.searchString = searchString;
		this.minDateString = minDateString;
		this.maxDateString = maxDateString;
		this.isRegUser = isRegUser;
		this.isManager = isManager;
		this.isAdmin = isAdmin;
		this.orderByParam = orderByParam;
		this.orderByDirection = orderByDirection;
	}
	/**
	 * @return the searchString
	 */
	@Override
	public String getSearchString() {
		return searchString;
	}
	/**
	 * @param searchString the searchString to set
	 */
	@Override
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	/**
	 * @return the roleArray
	 */
	@Override
	public List<Role> getRoleArray() {
		return roleArray;
	}
	/**
	 * @param roleArray the roleArray to set
	 */
	@Override
	public void setRoleArray(List<Role> roleArray) {
		this.roleArray = roleArray;
	}
	/**
	 * @return the minDate
	 */
	@Override
	public Date getMinDate() {
		return minDate;
	}
	/**
	 * @param minDate the minDate to set
	 */
	@Override
	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}
	/**
	 * @return the maxDate
	 */
	@Override
	public Date getMaxDate() {
		return maxDate;
	}
	/**
	 * @param maxDate the maxDate to set
	 */
	@Override
	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}
	/**
	 * @return the minDateString
	 */
	@Override
	public String getMinDateString() {
		return minDateString;
	}
	/**
	 * @param minDateString the minDateString to set
	 */
	@Override
	public void setMinDateString(String minDateString) {
		this.minDateString = minDateString;
	}
	/**
	 * @return the maxDateString
	 */
	@Override
	public String getMaxDateString() {
		return maxDateString;
	}
	/**
	 * @param maxDateString the maxDateString to set
	 */
	@Override
	public void setMaxDateString(String maxDateString) {
		this.maxDateString = maxDateString;
	}
	/**
	 * @return the isRegUser
	 */
	@Override
	public Boolean getIsRegUser() {
		return isRegUser;
	}
	/**
	 * @param isRegUser the isRegUser to set
	 */
	@Override
	public void setIsRegUser(Boolean isRegUser) {
		this.isRegUser = isRegUser;
	}
	/**
	 * @return the isManager
	 */
	@Override
	public Boolean getIsManager() {
		return isManager;
	}
	/**
	 * @param isManager the isManager to set
	 */
	@Override
	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}
	/**
	 * @return the isAdmin
	 */
	@Override
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	/**
	 * @param isAdmin the isAdmin to set
	 */
	@Override
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * @return the orderByParam
	 */
	@Override
	public String getOrderByParam() {
		return orderByParam;
	}

	/**
	 * @param orderByParam the orderByParam to set
	 */
	@Override
	public void setOrderByParam(String orderByParam) {
		this.orderByParam = orderByParam;
	}

	/**
	 * @return the orderByDirection
	 */
	@Override
	public String getOrderByDirection() {
		return orderByDirection;
	}

	/**
	 * @param orderByDirection the orderByDirection to set
	 */
	@Override
	public void setOrderByDirection(String orderByDirection) {
		this.orderByDirection = orderByDirection;
	}
	
	
	
}
