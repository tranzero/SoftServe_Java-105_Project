package com.ita.edu.softserve.validationcontainers;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.edu.softserve.entity.Role;

/**
 * interface UserCriteriaContainer - for pagging 1
 * 
 * @author iryna
 * 
 */
@Component
public interface UserCriteriaContainer {

	void setValuableInfo(String searchString, String minDateString,
			String maxDateString, Boolean isRegUser, Boolean isManager,
			Boolean isAdmin, String orderByParam, String orderByDirection);

	String getSearchString();

	void setSearchString(String searchString);

	List<Role> getRoleArray();

	void setRoleArray(List<Role> roleArray);

	Date getMinDate();

	void setMinDate(Date minDate);

	Date getMaxDate();

	void setMaxDate(Date maxDate);

	String getMinDateString();

	void setMinDateString(String minDateString);

	String getMaxDateString();

	void setMaxDateString(String maxDateString);

	Boolean getIsRegUser();

	void setIsRegUser(Boolean isRegUser);

	Boolean getIsManager();

	void setIsManager(Boolean isManager);

	Boolean getIsAdmin();

	void setIsAdmin(Boolean isAdmin);

	String getOrderByParam();

	void setOrderByParam(String orderByParam);

	String getOrderByDirection();

	void setOrderByDirection(String orderByDirection);

}
