package com.springboot.restapi.services;

import com.springboot.restapi.models.Employee;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by Vinit Badrike on 10/22/2018.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private EmployeeService employeeService;

public UserDetailsServiceImpl(EmployeeService employeeService) {
    this.employeeService = employeeService;
}
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeService.findByUserName(username);
        if (employee == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(employee.getUsername(), employee.getPassword(), Collections.emptyList());
    }
}