package com.ita.edu.softserve.utils.property;



import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.prefs.*;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.ita.edu.softserve.utils.LoggerProgrammaticalConfigurator;



@Component("preferenceUtil")
@DependsOn({"pathUtil", "loggerProgrammaticalConfigurator"})
public class PreferenceUtil {
	private static final Logger LOGGER = Logger.getLogger(PreferenceUtil.class);

	private static final String PREFERENCE_NODE = "LV105JAVA";

	@Autowired
	private PathUtil pathUtil;

	

	@Autowired
	private LoggerProgrammaticalConfigurator loggerProgrammaticalConfigurator;

	Preferences pref = Preferences.userRoot().node(PREFERENCE_NODE);
	Map<String, String> standartValues;

	public PreferenceUtil() {
	}

	@PostConstruct
	public void postConstructInitialization() {

		standartValues = pathUtil.getPathMap();

		this.initPreferences();
		this.createMainPath();
		this.createPaths();
		this.initLogger(this.getAbsolutePath(this
				.getValue(PropertyKeys.LOGS_PATH_PROPERTY_NAME)));
		
	}

	public String getValue(String key) {
		return pref.get(key, "empty");
	}

	public void setValue(String key, String value) {
		pref.put(key, value);
	}

	public String getAbsolutePath(String path) {

		// Replace windows path delimiters with unix-like.
		path = path.replaceAll("\\\\", "/");
		File file = new File(path);
		if (file.isAbsolute()) {
			return path;
		} else {

			String mainPathString = this
					.getValue(PropertyKeys.MAIN_PATH_PROPERTY_NAME);
			if (mainPathString.charAt(mainPathString.length() - 1) != '/')
				mainPathString += "/";

			return mainPathString + path;
		}

	}

	public void createPath(String path) {

		File filePath = new File(path);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}

	}

	

	public void initLogger(String logsPath) {
		// Configure logger facility
		loggerProgrammaticalConfigurator.init(logsPath);
		LOGGER.info("Logger Configuration completed.");
	}

	public void createMainPath() {
		this.createPath(pref.get(PropertyKeys.MAIN_PATH_PROPERTY_NAME,
				standartValues.get(PropertyKeys.MAIN_PATH_PROPERTY_NAME)));
	}

	public void createPaths() {
		Set<String> pathSet = new HashSet<>();

		// Get all key which are paths
		for (String key : standartValues.keySet()) {
			if (key.contains("path.")) {
				pathSet.add(key);
			}
		}

		// Delete path.main key
		pathSet.remove(PropertyKeys.MAIN_PATH_PROPERTY_NAME);

		// Create all paths in file system
		for (String path : pathSet) {
			this.createPath(this.getAbsolutePath(this.getValue((path))));
		}
	}

	public String checkPathExistance(String pathPropertyName) {
		if(pathPropertyName.equals(PropertyKeys.MAIN_PATH_PROPERTY_NAME)){
			String resultPath = this.getValue(pathPropertyName);
			this.createPath(resultPath);
			return resultPath;
		} else {
			String resultPath = this.getAbsolutePath(this.getValue(pathPropertyName));
			this.createPath(resultPath);
			return resultPath;
		}
	}
	
	public void initPreferences() {
		Iterator<Map.Entry<String, String>> entries = standartValues.entrySet()
				.iterator();
		while (entries.hasNext()) {
			Map.Entry<String, String> entry = entries.next();
			if (pref.get((String) entry.getKey(), "none").equals("none")) {
				pref.put(entry.getKey(), entry.getValue());
			}
		}
	}
}
