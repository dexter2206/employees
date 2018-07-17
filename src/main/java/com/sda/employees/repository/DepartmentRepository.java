package com.sda.employees.repository;


import com.sda.employees.model.Department;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DepartmentRepository {

    private DataSource ds;

    public DepartmentRepository(DataSource ds) {
        this.ds = ds;
    }

    public List<Department> findBy(Specification spec) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Department> result = new LinkedList<>();
        try(Connection conn = ds.getConnection()) {
            ps = spec.toSql(conn);
            rs = ps.executeQuery();
            while(rs.next()) {
                result.add(new Department(rs.getString("dept_no"),
                        rs.getString("dept_name")));
            }
            return result;
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving departments.");
        } finally {
            try { if(rs != null) { rs.close(); }} catch(Exception e) {}
            try { if(ps != null) { ps.close(); }} catch(Exception e) {}
        }
    }
}
