package com.microservices.mstraining.controller;

import com.microservices.mstraining.model.advanceduser.AdvancedUser;
import com.microservices.mstraining.model.user.User;
import com.microservices.mstraining.service.AdvancedUserService;
import com.microservices.mstraining.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdvancedUserController {

    @Autowired
    AdvancedUserService userService;

    // To create a user
    @PostMapping(path="/advanceduser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AdvancedUser> createUser(@RequestBody AdvancedUser user) {
        AdvancedUser newUser = userService.createUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(newUser);
    }

    // To get specific user
    @GetMapping(path="/advanceduser/{id}", produces = "application/json")
    public ResponseEntity<AdvancedUser> getSpecificUser(@PathVariable int id) {
        AdvancedUser user = userService.getUser(id);
        return ResponseEntity.ok().body(user);
    }

}
