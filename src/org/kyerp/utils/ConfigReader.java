package org.kyerp.utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigReader{
	private static Properties	properties	= new Properties();
	static {
		try {
// properties.load(ConfigReader.class.getClassLoader().getResourceAsStream(
// "siteurl.properties"));
			// URL classPath = Thread.currentThread().getContextClassLoader().getResource("");
			// URL classPath = Thread.currentThread().getContextClassLoader().getResourceAsStream("conf/config.properties");
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("conf/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String read(String key) {
		return (String) properties.get(key);
	}
}
