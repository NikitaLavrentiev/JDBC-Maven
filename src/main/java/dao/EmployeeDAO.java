package dao;


import models.Employee;

import java.util.List;

public interface EmployeeDAO {

    void create(Employee stuff);

    Employee readById(int id);

    List<Employee> readAll();

    void updateEmployeeEntity(Employee stuff);

    void deleteEmployeeEntity(Employee stuff);
}
