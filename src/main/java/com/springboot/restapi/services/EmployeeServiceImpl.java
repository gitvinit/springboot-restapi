package com.springboot.restapi.services;

import com.springboot.restapi.models.Employee;
import com.springboot.restapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Vinit Badrike on 5/18/2018.
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    public Iterable<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findById(long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee findByName(String name) {
        Iterable<Employee> employees = employeeRepository.findAll();
        for(Employee Employee : employees){
            if(Employee.getName().equalsIgnoreCase(name)){
                return Employee;
            }
        }
        return null;
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployeeById(long id) {

        employeeRepository.deleteById(id);
    }

    public boolean employeeExists(Employee employee) {
        return employeeRepository.findById(employee.getId()).isPresent();
    }
}
