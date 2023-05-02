package com.don.demo.concurrent.threadlocal;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Carl Don
 * @version V1.0
 * @ClassName ConnectionUtil
 * @date 2019年08月15日 下午 6:31
 */
public class ConnectionUtil {
	//threadLocal作为当前线程的连接的代表者,使用final
	private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

	//连接池需要初始化，这里不做要求，使用final
	private static final DataSource dataSource = new DruidDataSource();

	//获取当前线程连接，第一次就从datasource中获取
	public static Connection getConnection() throws SQLException {
		Connection connection = threadLocal.get();
		if (connection == null) {
			connection = dataSource.getConnection();
			threadLocal.set(connection);
		}
		return connection;
	}

	//关闭连接，返回连接池
	public static void closeConnection() throws SQLException {
		Connection connection = threadLocal.get();
		threadLocal.set(null);
		if (connection != null) {
			connection.close();
		}
	}
}
