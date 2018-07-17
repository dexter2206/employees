package com.sda.employees.examples;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by dexter on 17.07.18.
 */
public class DataSourceExample {

    public static void main(String[] args) {
        DataSource ds = DataSourceFactory.getDataSource();
        try(Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM departments")) {
            while(rs.next()) {
                System.out.println(rs.getString("dept_name"));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
