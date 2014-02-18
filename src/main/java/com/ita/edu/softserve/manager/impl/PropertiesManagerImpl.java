package com.ita.edu.softserve.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ita.edu.softserve.manager.PropertiesManager;
import com.ita.edu.softserve.utils.property.PreferenceUtil;
import com.ita.edu.softserve.utils.property.PropertyKeys;



@Service("propertiesManager")
public class PropertiesManagerImpl implements PropertiesManager {

	@Autowired
	PreferenceUtil preferenceUtil;
	


	public PropertiesManagerImpl() {
	}

	@Override
	public String getMainPath() {
		return preferenceUtil
				.checkPathExistance(PropertyKeys.MAIN_PATH_PROPERTY_NAME);
	}

	@Override
	public void setMainPath(String path) {
		preferenceUtil.setValue(PropertyKeys.MAIN_PATH_PROPERTY_NAME, path);
	}

	@Override
	public String getLogsPath() {
		return preferenceUtil
				.checkPathExistance(PropertyKeys.LOGS_PATH_PROPERTY_NAME);
	}

	@Override
	public void setLogsPath(String path) {
		preferenceUtil.setValue(PropertyKeys.LOGS_PATH_PROPERTY_NAME, path);
	}

	
}
