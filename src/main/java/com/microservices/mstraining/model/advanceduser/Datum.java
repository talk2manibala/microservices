package com.microservices.mstraining.model.advanceduser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Builder(toBuilder = true)
@Component
@Scope("prototype")
public class Datum {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;

    public Datum generate(Datum data) {
        this.setId(data.getId());
        this.setEmail(data.getEmail());
        this.setFirstName(data.getFirstName());
        this.setLastName(data.getLastName());
        this.setAvatar(data.getAvatar());
        return this;
    }

}