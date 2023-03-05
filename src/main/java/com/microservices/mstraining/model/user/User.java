package com.microservices.mstraining.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Entity(name="user_details")
public class User {
    //@NotNull(message="Id can't be null")
    // @JsonIgnore
    @Id
    private Integer id;
    @NotBlank
    @NotEmpty
    //@JsonProperty("user_name")
    @NotNull(message="Name is required")
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
