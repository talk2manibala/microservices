package com.microservices.mstraining.controller;

import com.microservices.mstraining.model.User;
import com.microservices.mstraining.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(path = "/api/message", method = RequestMethod.GET)
    public String greet() {
        return "Welcome to MicroServices Learning Session !!";
    }

    @GetMapping(path = "/users")
    public List<User> getUsers() {
        return userService.getAllEmplmoyees();
    }

    // To get specific user
    @GetMapping("/user/{id}")
    public User getSpecificUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    // To create a user
    @PostMapping(path="/user", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser =  userService.createUser(user);
        if (newUser==null) {
            return ResponseEntity.internalServerError().body(newUser);
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).body(newUser);
        // return new ResponseEntity<User>(newUser, HttpStatusCode.valueOf(201));
        // return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

}
