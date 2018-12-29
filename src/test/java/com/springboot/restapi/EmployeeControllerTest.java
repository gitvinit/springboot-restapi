package com.springboot.restapi;

import com.springboot.restapi.controllers.EmployeeController;
import com.springboot.restapi.models.Employee;
import com.springboot.restapi.services.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
/**
 * WebMvcTest is really a unit test of your controller if it has dependency,
 * you'll have to provide them yourself (either a config or a mock of some kind)
 */
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService service;

    @Test
    public void getAllEmployees()
            throws Exception {

        Employee alex = new Employee();

        List<Employee> allEmployees = new ArrayList<>(Arrays.asList(alex));
        //allEmployees.add(alex);

        given(service.findAllEmployees()).willReturn(allEmployees);

        mvc.perform(get("/api/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getSingleEmployee()
            throws Exception {

        Employee alex = new Employee();

        List<Employee> allEmployees = new ArrayList<>();
        allEmployees.add(alex);

        given(service.findById(Mockito.anyLong())).willReturn(alex);

        mvc.perform(get("/api/employees/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getSingleEmployeeNotFound()
            throws Exception {

        Employee alex = new Employee();

        List<Employee> allEmployees = new ArrayList<>();
        allEmployees.add(alex);

        given(service.findById(Mockito.eq(1))).willReturn(alex);

        mvc.perform(get("/api/employees/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
