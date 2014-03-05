/**
 * 
 */
package com.ita.edu.softserve.validationcontainers;

import org.springframework.stereotype.Component;

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
