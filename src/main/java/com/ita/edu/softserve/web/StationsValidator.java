/**
 * 
 */
package com.ita.edu.softserve.web;

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

@Component
public class StationsValidator implements Validator {

	public static final String STATIONS_CODE_PATERN = "^[0-9]{5,15}$";
	public static final String STATIONS_NAME_PATERN = "^[a-zA-Z0-9]{5,15}$";

	/**
	 * For using some methods from StationManager.
	 */
	@Autowired
	private StationsManager stationManager;

	@Override
	public boolean supports(Class<?> clazz) {
		return Stations.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Stations station = (Stations) target;

		validateStationExist(station.getStationId(), station.getStationName(),
				errors);
		validateStationCode(station.getStationCode(), errors);
		validateStationName(station.getStationName(), errors);
	}

	private void validateStationCode(String stationCode, Errors errors) {

		if (stationCode == null || stationCode == "") {
			errors.rejectValue("stationCode", "stationCode.empty");
		} else {
			if (stationCode.matches(STATIONS_CODE_PATERN) == false) {
				errors.rejectValue("stationCode", "stationCode.matcher");
			}
		}
	}

	private void validateStationName(String stationName, Errors errors) {

		if (stationName == null || stationName == "") {
			errors.rejectValue("stationName", "stationName.empty");
		} else {
			if (stationName.matches(STATIONS_NAME_PATERN) == false) {
				errors.rejectValue("stationName", "stationName.matcher");
			}
		}
	}

	private void validateStationExist(Integer stationId, String stationName,
			Errors error) {

		if ((stationName != null) && (stationName != "")) {
			Stations station = null;

			try {
				station = stationManager.findByStationName(stationName);

				if ((station.getStationId()).equals(stationId)) {
					return;
				}
				error.rejectValue("stationName", "stationName.exist");
			} catch (RuntimeException e) {
			}
		}
	}

}
