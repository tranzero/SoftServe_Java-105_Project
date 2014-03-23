/**
 * 
 */
package com.ita.edu.softserve.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.manager.StationsManager;

/**
 * @author admin
 * 
 */

@Component("stationValidator")
public class StationsValidator implements Validator {

	private static final String STATIONS_CODE_PATERN = "^[0-9]{5,15}$";
	private static final String STATIONS_NAME_PATERN = "^[a-zA-Z0-9]{5,15}$";
	
	private static final String STATION_CODE = "stationCode";
	private static final String STATION_NAME = "stationName";
	
	private static final String STATION_CODE_MATCHER = "stationCode.matcher";
	private static final String STATION_NAME_MATCHER = "stationName.matcher";
	
	private static final String STATION_CODE_EMPTY = "stationCode.empty";
	private static final String STATION_NAME_EMPTY = "stationName.empty";
	
	private static final String STATION_NAME_EXIST = "stationName.exist";

	/**
	 * For using some methods from StationManager.
	 */
	@Autowired
	private StationsManager stationManager;

	/**
	 * This Validator validates *just* Stations instance.
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Stations.class.equals(clazz);
	}

	/**
	 * This method provide validation for Stations objects.
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 *      org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		Stations station = (Stations) target;

		validateStationExist(station.getStationId(), station.getStationName(),
				errors);
		validateStationCode(station.getStationCode(), errors);
		validateStationName(station.getStationName(), errors);
	}

	/**
	 * Validate stationCode must be not empty and contain only digits more then 5 and less then 15.
	 * 
	 * @param stationCode
	 * @param errors
	 */
	private void validateStationCode(String stationCode, Errors errors) {

		if (stationCode == null || stationCode == "") {
			errors.rejectValue(STATION_CODE, STATION_CODE_EMPTY);
		} else {
			if (stationCode.matches(STATIONS_CODE_PATERN) == false) {
				errors.rejectValue(STATION_CODE, STATION_CODE_MATCHER);
			}
		}
	}

	/**
	 * Validate stationName must be not empty and contain only letter and digits more then 5 and less then 15.
	 * 
	 * @param stationName
	 * @param errors
	 */
	private void validateStationName(String stationName, Errors errors) {

		if (stationName == null || stationName == "") {
			errors.rejectValue(STATION_NAME, STATION_NAME_EMPTY);
		} else {
			if (stationName.matches(STATIONS_NAME_PATERN) == false) {
				errors.rejectValue(STATION_NAME, STATION_NAME_MATCHER);
			}
		}
	}

	/**
	 * Check if Station object exist in database with such stationName.
	 * 
	 * @param stationId
	 * @param stationName
	 * @param error
	 */
	private void validateStationExist(Integer stationId, String stationName,
			Errors error) {

		if ((stationName != null) && (stationName != "")) {
			Stations station = null;

			try {
				station = stationManager.findByStationName(stationName);

				if ((station.getStationId()).equals(stationId)) {
					return;
				}
				error.rejectValue(STATION_NAME, STATION_NAME_EXIST);
			} catch (RuntimeException e) {
			}
		}
	}

}
