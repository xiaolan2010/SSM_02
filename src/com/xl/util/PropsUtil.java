package com.xl.util;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class PropsUtil {
	    private static final Log logger = LogFactory.getLog(PropsUtil.class);
	    private static final String DEFAULT_ENCODING = "UTF-8";

	    private Properties props = null;

	    public PropsUtil(String propsPath) {
	        this(propsPath, DEFAULT_ENCODING);
	    }

	    public PropsUtil(String propsPath, String encoding) {
	        InputStream is = null;
	        try {
	            if (StringUtils.isBlank(propsPath)) {
	                throw new IllegalArgumentException();
	            }
	            String suffix = ".properties";
	            if (propsPath.lastIndexOf(suffix) == -1) {
	                propsPath += suffix;
	            }
	            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propsPath);
	            if (is != null) {
	                props = new Properties();
	                props.load(new InputStreamReader(is, encoding));
	            }
	        } catch (Exception e) {
	            logger.error("���������ļ�����", e);
	            logger.error(e.toString(), e);
	            throw new RuntimeException(e);
	        } finally {
	            try {
	                if (is != null) {
	                    is.close();
	                }
	            } catch (IOException e) {
	                logger.error("�ͷ���Դ����", e);
	                logger.error(e.toString(), e);
	            }
	        }
	    }

	    /**
	     * ���������ļ�����תΪ Map
	     */
	    public Map<String, String> loadPropsToMap() {
	        Map<String, String> map = new HashMap<String, String>();
	        for (String key : props.stringPropertyNames()) {
	            map.put(key, props.getProperty(key));
	        }
	        return map;
	    }

	    /**
	     * ��ȡ�ַ�������
	     */
	    public String getString(String key) {
	        return props.getProperty(key);
	    }

	    /**
	     * ��ȡ�ַ������ԣ���Ĭ��ֵ
	     */
	    public String getString(String key, String defaultValue) {
	        return props.getProperty(key, defaultValue);
	    }

	    /**
	     * ��ȡ�ַ������ԣ���Ĭ��ֵ��
	     */
	    public static String getString(Properties props, String key, String defalutValue) {
	        String value = defalutValue;
	        if (props.containsKey(key)) {
	            value = props.getProperty(key);
	        }
	        return value;
	    }

	    /**
	     * ��ȡ��ֵ������
	     */
	    public Integer getInt(String key) {
	        return getInt(key, null);
	    }
	    public Integer getInt(String key, Integer defaultValue) {
	        String value = props.getProperty(key);
	        if (value != null)
	            return Integer.parseInt(value.trim());
	        return defaultValue;
	    }

	    /**
	     * ��ȡLong��
	     */
	    public Long getLong(String key) {
	        return getLong(key, null);
	    }
	    public Long getLong(String key, Long defaultValue) {
	        String value = props.getProperty(key);
	        if (value != null)
	            return Long.parseLong(value.trim());
	        return defaultValue;
	    }

	    /**
	     * ��ȡ����������
	     */
	    public Boolean getBoolean(String key) {
	        return getBoolean(key, null);
	    }
	    public Boolean getBoolean(String key, Boolean defaultValue) {
	        String value = props.getProperty(key);
	        if (value != null) {
	            value = value.toLowerCase().trim();
	            if ("true".equals(value))
	                return true;
	            else if ("false".equals(value))
	                return false;
	            throw new RuntimeException("The value can not parse to Boolean : " + value);
	        }
	        return defaultValue;
	    }

	    public boolean containsKey(String key) {
	        return props.containsKey(key);
	    }

	    public Properties getProperties() {
	        return props;
	    }

	    public static void main(String[] args) {
	        //��ʼ�������ļ�
	        PropsUtil propsUtil = new PropsUtil("email");
	        //���Map����
	        Map<String, String> map = propsUtil.loadPropsToMap();
	        //����key���ĳ��ֵ
	        String string = propsUtil.getString("mail.username");
	        System.out.println(string);
	        System.out.println(map.get("mail.username"));
	    }
}
