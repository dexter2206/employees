package com.sda.employees.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PartialDeptNameSpecification implements Specification{

    private String namePart;
    private String query = "SELECT * FROM departments WHERE dept_name LIKE ?";

    public PartialDeptNameSpecification(String namePart) {
        this.namePart = namePart;
    }

    @Override
    public PreparedStatement toSql(Connection conn) {
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + namePart + "%");
            return ps;
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error preparing statement.");
        }
    }
}
