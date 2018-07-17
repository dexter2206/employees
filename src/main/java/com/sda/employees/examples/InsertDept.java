package com.sda.employees.examples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by dexter on 16.07.18.
 */
public class InsertDept {

    private static String insert = "INSERT INTO departments(dept_no, dept_name) VALUES(?, ?)";
    public static void main(String[] args) {
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(insert)) {
            stmt.setString(1, "d013");
            stmt.setString(2, "Security");
            int rows_affected = stmt.executeUpdate();
            if(rows_affected == 1) {
                System.out.println("Inserted successfuly");
            } else {
                System.out.println("Not inserted.");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
