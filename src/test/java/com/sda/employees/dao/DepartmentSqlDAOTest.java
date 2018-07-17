package com.sda.employees.dao;

import com.sda.employees.model.Department;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentSqlDAOTest {
    @Mock
    private DataSource ds;
    @Mock
    private Connection c;
    @Mock
    private PreparedStatement stmt;
    @Mock
    private ResultSet rs;

    private Department dept;

    @Before
    public void setUp() throws Exception {
        assertNotNull(ds);
        when(c.prepareStatement(any(String.class))).thenReturn(stmt);
        when(ds.getConnection()).thenReturn(c);
        when(stmt.executeQuery()).thenReturn(rs);
        dept = new Department("d012", "Cleaning Service");
    }

    @Test
    public void getByNoPreparesStatement() throws Exception {
        when(rs.next()).thenReturn(true);
        when(rs.getString("dept_no")).thenReturn("d012");
        when(rs.getString("dept_name")).thenReturn("Cleaning Servie");
        DepartmentDAO dao = new DepartmentSqlDAO(ds);
        String queryStatement = "SELECT * FROM departments WHERE dept_no = ?";
        dao.getByNo("d012");
        verify(c, times(1)).prepareStatement(queryStatement);
        verify(stmt, times(1)).setString(1, "d012");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByNoChecksDeptNoNotNull() throws Exception {
        when(rs.next()).thenReturn(true);
        when(rs.getString("dept_no")).thenReturn("d012");
        when(rs.getString("dept_name")).thenReturn("Cleaning Servie");
        DepartmentDAO dao = new DepartmentSqlDAO(ds);
        Department dept = dao.getByNo(null);
    }
}
