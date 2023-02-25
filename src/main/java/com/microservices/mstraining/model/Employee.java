package com.microservices.mstraining.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Builder(toBuilder = true)
@Component
@Scope("prototype") // This annotation will help to create multiple objects instead of following singleton
public class Employee {

    private int id;
    private String name;
    /*@JsonProperty("first_name")
    private String firstName;*//*@JsonProperty("first_name")
    private String firstName;*/
    private LocalDate dob;

    public Employee() {
        System.out.println("Employee Object created");
    }

}
