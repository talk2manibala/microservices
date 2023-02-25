package com.microservices.mstraining.controller;

import com.microservices.mstraining.model.User;
import com.microservices.mstraining.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(path="/user")
    public User createUser(@RequestBody User user) {
        User newUser =  userService.createUser(user);
        return newUser;
    }

}
