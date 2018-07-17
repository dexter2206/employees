package com.sda.employees.examples;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {

    static final String url = "jdbc:mysql://155.158.138.131:3306/employees?allowPublicKeyRetrieval=true&useSSL=false";
    static final String user = "root";
    static final String password = "sda";

    public static DataSource getDataSource() {
        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
        ds.setUser(user);
        ds.setPassword(password);
        ds.setUrl(url);
        return ds;
    }
}
