package com.ita.edu.softserve.manager;

import org.springframework.stereotype.Service;

@Service("propertiesManager")
public interface PropertiesManager {

	String getMainPath();

	void setMainPath(String path);

	String getLogsPath();

	void setLogsPath(String path);

	String getImgPath();

	void setImgPath(String path);

	String getHostPath();
	
}
