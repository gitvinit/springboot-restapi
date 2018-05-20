package com.springboot.restapi.repository;

import com.springboot.restapi.models.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Vinit Badrike on 5/18/2018.
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
