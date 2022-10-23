package com.json.gson.utils;

import java.sql.*;

public class JdbcUtils {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/liqui_base_db_test?useSSL=false";


    static final String USER = "root";
    static final String PASSWORD = "159753";
    private static Connection connection = null;

    private static Connection  getConnection() throws SQLException, ClassNotFoundException {
        if(connection == null) {
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        }
        return connection;
    }
    public static PreparedStatement createStatement(String sql) throws SQLException {
        try {
           connection = JdbcUtils.getConnection();
            return connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
