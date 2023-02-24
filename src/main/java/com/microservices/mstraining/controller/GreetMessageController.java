package com.microservices.mstraining.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
public class GreetMessageController {

    @RequestMapping(path = "/api/message", method = RequestMethod.GET)
    public String greet() {
        return "Welcome to MicroServices Learning Session !!";
    }

    @GetMapping(path = "/employees")
    public List<Employee> getEmployees() {
        Employee roger = Employee.builder().id(1).name("Roger").dob(LocalDate.now().minusYears(20)).build();
        Employee rafa = Employee.builder().id(1).name("Rafa").dob(LocalDate.now().minusYears(20)).build();
        return Arrays.asList(roger, rafa);
    }

}
