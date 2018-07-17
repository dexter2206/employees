package com.sda.employees.examples;


import com.sda.employees.dao.DepartmentDAO;
import com.sda.employees.dao.DepartmentSqlDAO;
import com.sda.employees.model.Department;

public class DepartmentDAOExample {

    public static void main(String[] args) {
        DepartmentDAO dao = new DepartmentSqlDAO(DataSourceFactory.getDataSource());
        Department d = dao.getByNo("d002");
        System.out.println(d.getDeptNo() + " " + d.getDeptName());
        Department d2 = new Department("d217", "PR");
        dao.create(d2);
    }
}
