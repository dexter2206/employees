package com.sda.employees.examples;

import com.sda.employees.repository.DepartmentRepository;
import com.sda.employees.repository.PartialDeptNameSpecification;
import com.sda.employees.repository.Specification;

public class RepositoryExample {

    public static void main(String[] args) {
        DepartmentRepository repo = new DepartmentRepository(
                DataSourceFactory.getDataSource());
        Specification spec = new PartialDeptNameSpecification("Service");
        repo.findBy(spec).stream()
                .map(dept -> dept.getDeptName())
                .forEach(System.out::println);
    }
}
