package com.microservices.mstraining.service;

import com.microservices.mstraining.model.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    public static ArrayList<Employee> emplmoyeeList = new ArrayList<>();
    public static int id = 0;

    static {
        Employee roger = Employee.builder().id(++id).name("Roger").dob(LocalDate.now().minusYears(20)).build();
        Employee rafa = Employee.builder().id(++id).name("Rafa").dob(LocalDate.now().minusYears(20)).build();
        Employee rafael = Employee.builder().id(++id).name("Rafael").dob(LocalDate.now().minusYears(20)).build();
        emplmoyeeList.add(roger);
        emplmoyeeList.add(rafa);
        emplmoyeeList.add(rafael);
    }

    public List<Employee> getAllEmplmoyees() {
        return emplmoyeeList;
    }

    // get employee :  employee/1
    public Employee getEmployee(int id) {
        Employee employee = emplmoyeeList.stream().filter(x->x.getId()==id).findFirst().orElse(null);
        if (employee==null) throw new EmployeeNotFoundException("Employee with id "+id+"not found");
        return employee;
    }

    // create a new employee
    // requirements : request body, context url, headers

    public Employee createEmployee(Employee employee) {
        Employee newEmployee = Employee.builder().id(++id).name(employee.getName()).dob(employee.getDob()).build();
        emplmoyeeList.add(newEmployee);
        return newEmployee;
    }


    // delete a employee

    // update a empmloyee

}
