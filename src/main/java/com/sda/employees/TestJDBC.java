package com.sda.employees;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestJDBC {

    public static void main(String[] args) throws SQLException, NamingException, PropertyVetoException {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/employees" );
        cpds.setUser("root");
        cpds.setPassword("sda2");
        DataSource ds;

// the settings below are optional -- c3p0 can work with defaults
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        Connection conn = cpds.getConnection();//DriverManager.getConnection("jdbc:mysql://localhost:3306/employees","root", "sda");
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM employees LIMIT 15");
        while(rs.next()) {
            System.out.println(rs.getString("last_name"));
        
    }
}
