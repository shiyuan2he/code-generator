/**
 * Project Name:testJava
 * File Name:DbUtil.java
 * Date:2015年11月13日下午6:00:34
 */

package com.hsy.mybatis.generator.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hsy.mybatis.generator.config.Configuration;

/**
 * ClassName:DbUtil <br/>
 * Function: 数据库连接工具类. <br/>
 * Date: 2015年11月13日 下午6:00:34 <br/>
 */

public class DbUtil {
    private static Logger logger = LogManager.getLogger(DbUtil.class.getName());
    private static Connection connection = null;
    //获取数据库连接
    public static Connection getConn() {
        if (connection != null) return connection;
        try {
            String driverName = Configuration.getString("jdbc.driverName");
            Class.forName(driverName);
            String jdbcUrl = Configuration.getString("jdbc.url");
            String userName = Configuration.getString("jdbc.username");
            String password = Configuration.getString("jdbc.password");
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
        } catch (Exception e) {
            logger.error("数据连接异常", e);
            e.printStackTrace();
        }
        return connection;
    }

    //closeConn:关闭连接
    public static void closeConn(Connection conn, Statement stat, ResultSet resultSet) {
        try {
            if (conn != null)
                conn.close();
            if (stat != null)
                stat.close();
            if (resultSet != null)
                resultSet.close();
            logger.info("关闭资源成功。。。");
        } catch (SQLException e) {
            logger.error("关闭连接异常", e);
            e.printStackTrace();
        }
    }
}
