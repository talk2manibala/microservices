package com.microservices.mstraining.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Builder(toBuilder = true)
@Component
@Scope("prototype") // This annotation will help to create multiple objects instead of following singleton
public class UserV2 {

    private int id;
    private Name name;
    //@JsonProperty("first_name")
    //private String firstName;
    private Address address;
    private LocalDate dob;
    private String respMsg;

    public UserV2() {
        System.out.println("User Object created");
    }

    @Data
    @AllArgsConstructor
    @Builder(toBuilder = true)
    @Component
    @Scope("prototype") // This annotation will help to create multiple objects instead of following singleton
    public static class Name {
        private String firstName;
        private String lastName;
    }

    @Data
    @AllArgsConstructor
    @Builder(toBuilder = true)
    @Component
    @Scope("prototype")
    public static class Address {
        private String street;
        private String city;
        private String country;
        private String pin;
    }
}
