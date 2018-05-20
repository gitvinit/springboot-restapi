package com.springboot.restapi.services;

import com.springboot.restapi.models.Employee;

/**
 * Created by Vinit Badrike on 5/18/2018.
 */
public interface EmployeeService {
    Employee findById(long id);

    Employee findByName(String name);

    void saveEmployee(Employee Employee);

    void updateEmployee(Employee Employee);

    void deleteEmployeeById(long id);

    Iterable<Employee> findAllEmployees();

    boolean employeeExists(Employee Employee);
}
