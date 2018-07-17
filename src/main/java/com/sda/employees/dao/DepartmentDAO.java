package com.sda.employees.dao;

import com.sda.employees.model.Department;

public interface DepartmentDAO {

    Department getByNo(String detpNo);
    // zaimplementować poniższe metody w klasie DepartmentSqlDAO
    void create(Department d);
    void update(Department d);
    void delete(Department d);
}
