package com.sda.employees.dao;

import com.sda.employees.model.Department;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentSqlDAO implements DepartmentDAO{

    private DataSource ds;
    final String byDeptNoQuery = "SELECT * FROM departments WHERE dept_no = ?";
    final String createStatement = "INSERT INTO departments(dept_no, dept_name) VALUES(?, ?)";
    final String updateStatement = "UPDATE departments SET dept_name = ? WHERE dept_no = ?";
    final String deleteStatement = "DELETE FROM departments WHERE dept_no = ?";

    public DepartmentSqlDAO(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Department getByNo(String deptNo) {
        if(deptNo == null) {
            throw new IllegalArgumentException("Invalid department number");
        }
        ResultSet rs = null;
        try(Connection conn = ds.getConnection();
            PreparedStatement stmt = conn.prepareStatement(byDeptNoQuery)) {
            stmt.setString(1, deptNo);
            rs = stmt.executeQuery();
            if(rs.next()) {
                return new Department(
                        rs.getString("dept_no"),
                        rs.getString("dept_name"));
            } else {
                throw new RuntimeException("No such department");
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error getting department data.");
        } finally {
            try { if(rs != null) { rs.close(); }} catch(Exception e) {}
        }
    }

    @Override
    public void create(Department d) {
        try(Connection conn = ds.getConnection();
        PreparedStatement ps = conn.prepareStatement(createStatement)) {
            ps.setString(1, d.getDeptNo());
            ps.setString(2, d.getDeptName());
            if(ps.executeUpdate() != 1) {
                throw new RuntimeException("Something odd happened.");
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Creating new department failed.");
        }
    }

    @Override
    public void update(Department d) {
        try(Connection conn = ds.getConnection();
        PreparedStatement ps = conn.prepareStatement(updateStatement)) {
            ps.setString(1, d.getDeptName());
            ps.setString(2, d.getDeptNo());
            if(ps.executeUpdate() != 1) {
                throw new RuntimeException("Something odd happened.");
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Updating department failed");
        }
    }

    @Override
    public void delete(Department d) {

    }
}
