/**
 * 
 */
package com.ita.edu.softserve.validationcontainers;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ita.edu.softserve.entity.Role;

/**
 * @author admin
 *
 */

@Component
public interface StationsCriteriaContainer {
	
	void setValuableInfo(String searchString, String orderByParam, String orderByDirection);

	String getSearchString();

	void setSearchString(String searchString);

	String getOrderByParam();

	void setOrderByParam(String orderByParam);

	String getOrderByDirection();

	void setOrderByDirection(String orderByDirection);


}
