package com.xl.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 属性文件工具类 org.slf4j需要导包bean-validator-3.0-JBoss-4.0.2.jar包
 */
public class PropsUtil2 {

	private static final Log LOGGER = LogFactory.getLog(PropsUtil2.class);

	/**
	 * 加载属性文件
	 */
	public static Properties loadProps(String fileName) {
		Properties props = null;
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			if (is == null) {
				throw new FileNotFoundException(fileName + " file is not found");
			}
			props = new Properties();
			props.load(is);
		} catch (IOException e) {
			LOGGER.error("load properties file failure", e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					LOGGER.error("close input stream failure", e);
				}
			}
		}
		return props;
	}

	/**
	 * 获取字符型属性（默认值为空字符串）
	 */
	public static String getString(Properties props, String key) {
		return getString(props, key, "");
	}

	/**
	 * 获取字符型属性（可指定默认值）
	 */
	public static String getString(Properties props, String key, String defaultValue) {
		String value = defaultValue;
		if (props.containsKey(key)) {
			value = props.getProperty(key);
		}
		return value;
	}

	/**
	 * 获取数值型属性（默认值为 0）
	 */
/*	public static int getInt(Properties props, String key) {
		return getInt(props, key, 0);
	}*/

	// 获取数值型属性（可指定默认值）
/*	public static int getInt(Properties props, String key, int defaultValue) {
		int value = defaultValue;
		if (props.containsKey(key)) {
			value = CastUtil.castInt(props.getProperty(key));
		}
		return value;
	}*/

	/**
	 * 获取布尔型属性（默认值为 false）
	 */
/*	public static boolean getBoolean(Properties props, String key) {
		return getBoolean(props, key, false);
	}*/

	/**
	 * 获取布尔型属性（可指定默认值）
	 */
/*	public static boolean getBoolean(Properties props, String key, boolean defaultValue) {
		boolean value = defaultValue;
		if (props.containsKey(key)) {
			value = CastUtil.castBoolean(props.getProperty(key));
		}
		return value;
	}*/

	/**
	 * 读取配置文件信息
	 */
/*	public void getProperties() {
		Properties pro = PropsUtil.loadProps("config/jdbc.properties");
		HTTP_URL = pro.getProperty("xahttp.url");
	}*/
}