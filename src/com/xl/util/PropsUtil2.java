package com.xl.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * �����ļ������� org.slf4j��Ҫ����bean-validator-3.0-JBoss-4.0.2.jar��
 */
public class PropsUtil2 {

	private static final Log LOGGER = LogFactory.getLog(PropsUtil2.class);

	/**
	 * ���������ļ�
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
	 * ��ȡ�ַ������ԣ�Ĭ��ֵΪ���ַ�����
	 */
	public static String getString(Properties props, String key) {
		return getString(props, key, "");
	}

	/**
	 * ��ȡ�ַ������ԣ���ָ��Ĭ��ֵ��
	 */
	public static String getString(Properties props, String key, String defaultValue) {
		String value = defaultValue;
		if (props.containsKey(key)) {
			value = props.getProperty(key);
		}
		return value;
	}

	/**
	 * ��ȡ��ֵ�����ԣ�Ĭ��ֵΪ 0��
	 */
/*	public static int getInt(Properties props, String key) {
		return getInt(props, key, 0);
	}*/

	// ��ȡ��ֵ�����ԣ���ָ��Ĭ��ֵ��
/*	public static int getInt(Properties props, String key, int defaultValue) {
		int value = defaultValue;
		if (props.containsKey(key)) {
			value = CastUtil.castInt(props.getProperty(key));
		}
		return value;
	}*/

	/**
	 * ��ȡ���������ԣ�Ĭ��ֵΪ false��
	 */
/*	public static boolean getBoolean(Properties props, String key) {
		return getBoolean(props, key, false);
	}*/

	/**
	 * ��ȡ���������ԣ���ָ��Ĭ��ֵ��
	 */
/*	public static boolean getBoolean(Properties props, String key, boolean defaultValue) {
		boolean value = defaultValue;
		if (props.containsKey(key)) {
			value = CastUtil.castBoolean(props.getProperty(key));
		}
		return value;
	}*/

	/**
	 * ��ȡ�����ļ���Ϣ
	 */
/*	public void getProperties() {
		Properties pro = PropsUtil.loadProps("config/jdbc.properties");
		HTTP_URL = pro.getProperty("xahttp.url");
	}*/
}