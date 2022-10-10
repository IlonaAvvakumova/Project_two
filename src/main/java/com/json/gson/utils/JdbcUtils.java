package com.json.gson.utils;

import java.sql.*;

public class JdbcUtils {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/labels?useSSL=false";


    static final String USER = "root";
    static final String PASSWORD = "159753";
    private static Connection connection = null;

    private static Connection  getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
    }
    public static PreparedStatement createStatement(String sql) throws SQLException {
        try {
           connection = JdbcUtils.getConnection();
            return connection.prepareStatement(sql);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            return null;
        }/*finally {
            connection.close(); //надо ли тут?
        }*/
    }
}
