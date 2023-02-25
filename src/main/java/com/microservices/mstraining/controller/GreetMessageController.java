package com.microservices.mstraining.controller;

import com.microservices.mstraining.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
public class GreetMessageController {


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

    // To get specific user
    @GetMapping("/employee/{id}")
    public Employee getSpecificEmployee(@PathVariable int id) {
        return employeeService.getEmployee(id);
    }

}
