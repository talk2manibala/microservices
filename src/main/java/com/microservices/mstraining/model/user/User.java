package com.microservices.mstraining.model.user;

import jakarta.validation.constraints.*;
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
public class User {
    @NotNull(message="Id can't be null")
    private int id;
    @NotBlank
    @NotEmpty
    @NotNull(message="Name is required ba")
    @Size(min=2, max=10, message="Name should between 2 and 10 in size")
    private String name;
    @Past
    private LocalDate dob;
    private String address;
    private String respMsg;

    public User() {
        System.out.println("User Object created");
    }

}
