package com.xl.database;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.omg.Dynamic.Parameter;
import org.springframework.util.StringUtils;

import com.xl.agile.modules.sys.entity.User;
import com.xl.util.PropsUtil2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DatabaseHelper {

	   private static final Log LOGGER = LogFactory.getLog(DatabaseHelper.class);

	    private static final ThreadLocal<Connection> CONNECTION_HOLDER;
	    private static final QueryRunner QUERY_RUNNER;
	    private static final BasicDataSource DATA_SOURCE;

	    static {
	        CONNECTION_HOLDER = new ThreadLocal<Connection>();
	        QUERY_RUNNER = new QueryRunner();
	        DATA_SOURCE = new BasicDataSource();
	        //��ʼ�������ļ�
	        Properties conf = PropsUtil2.loadProps("jdbc.properties"); 
	        String driver = conf.getProperty("jdbc.driver");
	        String url = conf.getProperty("jdbc.url");
	        String username = conf.getProperty("jdbc.username");
	        String password = conf.getProperty("jdbc.password");
	        System.out.println("conf:"+conf);
	        System.out.println("driver:"+driver);
	        System.out.println("url:"+url);
	        System.out.println("username:"+username);
	        System.out.println("password:"+password);
	        DATA_SOURCE.setDriverClassName(driver);
	        DATA_SOURCE.setUrl(url);
	        DATA_SOURCE.setUsername(username);
	        DATA_SOURCE.setPassword(password);

	    }

	    /**
	     * ��ȡ���ݿ�����
	     */
	    public static Connection getConnection() {
	        Connection connection = CONNECTION_HOLDER.get();
	        if (connection == null) {
	            try {
	                connection = DATA_SOURCE.getConnection();
	                System.out.println("connection....OK...");
	            } catch (SQLException e) {
	                LOGGER.error("get connection failure", e);
	                throw new RuntimeException(e);
	            } finally {
	                CONNECTION_HOLDER.set(connection);
	            }
	        }
	        return connection;
	    }

	    /**
	     * ��ѯʵ���б�
	     */
	    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
	        List<T> entityList;
	        try {
	            Connection conn = getConnection();
	            entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);
	        } catch (SQLException e) {
	            LOGGER.error("query entity list failure", e);
	            throw new RuntimeException(e);
	        }
	        return entityList;
	    }

	    /**
	     * ��ѯʵ��
	     */
	    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
	        T entity;
	        try {
	            Connection connection = getConnection();
	            entity = QUERY_RUNNER.query(connection, sql, new BeanHandler<T>(entityClass), params);
	        } catch (SQLException e) {
	            LOGGER.error("query entity failure", e);
	            throw new RuntimeException(e);
	        }
	        return entity;
	    }

	    /**
	     * ִ�в�ѯ���
	     */
	    public static List<Map<String, Object>> executeQuery(String sql, Object... params) {
	        List<Map<String, Object>> result;
	        try {
	            Connection connection = getConnection();
	            result = QUERY_RUNNER.query(connection, sql, new MapListHandler(), params);
	        } catch (Exception e) {
	            LOGGER.error("execute query failure", e);
	            throw new RuntimeException(e);
	        }
	        return result;
	    }

	    /**
	     * ִ�и�����䣨update��insert��delete��
	     */
	    public static int executeUpdate(String sql, Object... params) {
	        int rows = 0;
	        try {
	            Connection connection = getConnection();
	            rows = QUERY_RUNNER.update(connection, sql, params);
	        } catch (SQLException e) {
	            LOGGER.error("execute update failure", e);
	            throw new RuntimeException(e);
	        }
	        return rows;
	    }

	    /**
	     * ����ʵ��
	     */
	    public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {
	        if (StringUtils.isEmpty(fieldMap)) {
	            LOGGER.error("can not insert entity: fieldMap is empty");
	            return false;
	        }

	        String sql = "insert into " + getTableName(entityClass);
	        StringBuilder columns = new StringBuilder(" (");
	        StringBuilder values = new StringBuilder(" (");
	        for (String fieldName : fieldMap.keySet()) {
	            columns.append(fieldName).append(", ");
	            values.append("?, ");
	        }
	        columns.replace(columns.lastIndexOf(", "), columns.length(), ") ");
	        values.replace(values.lastIndexOf(", "), values.length(), ") ");
	        sql += columns + " values " + values;

	        Object[] params = fieldMap.values().toArray();
	        return executeUpdate(sql, params) == 1;
	    }

	    /**
	     * ����ʵ��
	     */
	    public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap) {
	        if (StringUtils.isEmpty(fieldMap)) {
	            LOGGER.error("can not update entity: fieldMap is empty");
	            return false;
	        }

	        String sql = "update " + getTableName(entityClass) + " set ";
	        StringBuilder columns = new StringBuilder();
	        for (String fieldName : fieldMap.keySet()) {
	            columns.append(fieldName).append("=?, ");
	        }
	        sql += columns.substring(0, columns.lastIndexOf(", ")) + " where id=?";

	        List<Object> paramList = new ArrayList<Object>();
	        paramList.addAll(fieldMap.values());
	        paramList.add(id);
	        Object[] params = paramList.toArray();
	        return executeUpdate(sql, params) == 1;
	    }

	    /**
	     * ɾ��ʵ��
	     */
	    public static <T> boolean deleteEntity(Class<T> entityClass, long id) {
	        String sql = "delete from " + getTableName(entityClass) + " where id=?";
	        return executeUpdate(sql, id) == 1;
	    }

	    /**
	     * ͨ��������ȡ����
	     */
	    private static String getTableName(Class<?> entityClass) {
	        return entityClass.getSimpleName();
	    }

	    /**
	     * ִ��SQL�ļ�
	     */
	    public static void executeSqlFile(String filePath) {
	        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	        try {
	            String sql;
	            while ((sql = reader.readLine()) != null) {
	                executeUpdate(sql);
	            }
	        } catch (IOException e) {
	            LOGGER.error("execute sql file failure", e);
	            throw new RuntimeException(e);
	        }
	    }

	    /**
	     * ��������
	     */
	    public static void beginTransaction() {
	        Connection conn = getConnection();
	        if (conn != null) {
	            try {
	                conn.setAutoCommit(false);
	            } catch (SQLException e) {
	                LOGGER.error("begin transaction failure", e);
	                throw new RuntimeException(e);
	            } finally {
	                CONNECTION_HOLDER.set(conn);
	            }
	        }
	    }

	    /**
	     * �ύ����
	     */
	    public static void commitTransaction() {
	        Connection conn = getConnection();
	        if (conn != null) {
	            try {
	                conn.commit();
	                conn.close();
	            } catch (SQLException e) {
	                LOGGER.error("commit transaction failure", e);
	                throw new RuntimeException(e);
	            } finally {
	                CONNECTION_HOLDER.remove();
	            }
	        }
	    }

	    /**
	     * �ع�����
	     */
	    public static void rollbackTransaction() {
	        Connection conn = getConnection();
	        if (conn != null) {
	            try {
	                conn.rollback();
	                conn.close();
	            } catch (SQLException e) {
	                LOGGER.error("rollback transaction failure", e);
	                throw new RuntimeException(e);
	            } finally {
	                CONNECTION_HOLDER.remove();
	            }
	        }
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatabaseHelper.getConnection();
		User user =new User();
		//--select  id  (test OK )
		String sql="select * from user where id=5";
		user=DatabaseHelper.queryEntity(User.class,sql,null);
		if(user != null) {
			System.out.println("ID:"+user.getId());
			System.out.println("Name:"+user.getName());
			System.out.println("Pwd:"+user.getPwd());
			System.out.println("Sex:"+user.getSex());
			System.out.println("Info:"+user.getInfo());
		}else {
			System.out.println("is NUll");
		}

		//--select  id  end
	}

}
