package com.springboot.restapi.controllers;

import com.springboot.restapi.error.CustomError;
import com.springboot.restapi.models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.springboot.restapi.services.EmployeeService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinit Badrike on 5/18/2018.
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * Retrieve All employees
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> listAllEmployees() {
        Iterable<Employee> employees = employeeService.findAllEmployees();
        List<Employee> employeeList = new ArrayList<>();
        employees.forEach(employeeList::add);
        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    /**
     * Retrieve Single Employee
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getEmployee(@PathVariable("id") long id) {
        logger.info("Fetching Employee with id {}", id);
        Employee Employee = employeeService.findById(id);
        if (Employee == null) {
            logger.error("Employee with id {} not found.", id);
            return new ResponseEntity<>(new CustomError("Employee with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Employee, HttpStatus.OK);
    }

    /**
     * Create an Employee
     * @param employee
     * @param ucBuilder
     * @return
     */
    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Employee : {}", employee);

        if (employeeService.employeeExists(employee)) {
            logger.error("Unable to create. A Employee with name {} already exists", employee.getName());
            return new ResponseEntity<>(new CustomError("Unable to create. A Employee with name " +
                    employee.getName() + " already exist."),HttpStatus.CONFLICT);
        }
        employeeService.saveEmployee(employee);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/Employee/{id}").buildAndExpand(employee.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    /**
     * Update an Employee
     * @param id
     * @param employee
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
        logger.info("Updating Employee with id {}", id);

        Employee currentEmployee = employeeService.findById(id);

        if (currentEmployee == null) {
            logger.error("Unable to update. Employee with id {} not found.", id);
            return new ResponseEntity<>(new CustomError("Unable to upate. Employee with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentEmployee.setName(employee.getName());

        employeeService.updateEmployee(currentEmployee);
        return new ResponseEntity<>(currentEmployee, HttpStatus.OK);
    }

    /**
     * Delete an Employee
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Employee with id {}", id);

        Employee employee = employeeService.findById(id);
        if (employee == null) {
            logger.error("Unable to delete. Employee with id {} not found.", id);
            return new ResponseEntity<>(new CustomError("Unable to delete. Employee with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
    }

}
