package com.puxinxiaolin.JUCStudy.Thread.threadLocal;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Description: 模拟数据库连接
 * @Author: YccLin
 * @Date: 2024/10/15
 */
public class ConnectionManager {
    // 创建并返回一个新的数据库连接
    private static final ThreadLocal<Connection> connectionThreadLocal = ThreadLocal
            .withInitial(ConnectionManager::createNewConnection);

    public static Connection getConnection() {
        return connectionThreadLocal.get();
    }

    public static void closeConnection() {
        Connection connection = connectionThreadLocal.get();
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                connectionThreadLocal.remove();
            }
        }
    }

    private static Connection createNewConnection() {
        // 实现创建数据库连接的逻辑
        return null;
    }
}
