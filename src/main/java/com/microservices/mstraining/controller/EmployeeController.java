package com.microservices.mstraining.controller;

import com.microservices.mstraining.model.Employee;
import com.microservices.mstraining.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {


    @Autowired
    EmployeeService employeeService;

    @RequestMapping(path = "/api/message", method = RequestMethod.GET)
    public String greet() {
        return "Welcome to MicroServices Learning Session !!";
    }

    @GetMapping(path = "/employees")
    public List<Employee> getEmployees() {
        return employeeService.getAllEmplmoyees();
    }

    // To get specific employee
    @GetMapping("/employee/{id}")
    public Employee getSpecificEmployee(@PathVariable int id) {
        return employeeService.getEmployee(id);
    }

    // To create a employee
    @PostMapping(path="/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        Employee newEmployee =  employeeService.createEmployee(employee);
        return newEmployee;
    }

}
