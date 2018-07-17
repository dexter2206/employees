package com.sda.employees.examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by dexter on 16.07.18.
 */
public class ConnectionFactory {

    private static final String url = "jdbc:mysql://155.158.138.131:3306/employees?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String user = "root";
    private static final String password = "sda";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
