package com.microservices.mstraining.controller;

import com.microservices.mstraining.model.user.User;
import com.microservices.mstraining.model.user.UserV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class VersioningController {

    @GetMapping("/user/v1")
    public User getV1User() {
        return User.builder().id(1).name("Krishna").address("Chennai").dob(LocalDate.now()).build();
    }

    @GetMapping("/user/v2")
    public UserV2 getV2User() {
        UserV2.Name name = UserV2.Name.builder()
                                .firstName("Mani")
                                .lastName("Bala")
                                .build();
        UserV2.Address address = UserV2.Address.builder()
                                .street("VV Street")
                                .city("Chennai")
                                .country("India")
                                .pin("600072")
                                .build();
        return UserV2.builder().id(1)
                                .name(name)
                                .address(address)
                                .dob(LocalDate.now().minusYears(18))
                                .build();
    }

}
