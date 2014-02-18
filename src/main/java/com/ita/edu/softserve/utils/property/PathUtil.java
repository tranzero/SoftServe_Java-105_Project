package com.ita.edu.softserve.utils.property;



import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component("pathUtil")
public class PathUtil {

	public PathUtil() {
	}

	public Map<String, String> getPathMap() {
		Properties properties = new Properties();

		Map<String, String> propertiesMap = new HashMap<String, String>();
		try (InputStream is = getClass().getClassLoader().getResourceAsStream(
				"path.properties")) {
			properties.load(is);
			
			@SuppressWarnings("rawtypes")
			Enumeration enumeration = properties.keys();
			while (enumeration.hasMoreElements()) {
				String key = (String) enumeration.nextElement();
				propertiesMap.put(key, (String) properties.get(key));
			}
			return propertiesMap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
