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

    @GetMapping(path = "/users", produces = "application/json")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users =  userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    // To get specific user
    @GetMapping(path="/user/{id}", produces = "application/json")
    public ResponseEntity<User> getSpecificUser(@PathVariable int id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok().body(user);
    }

    // To create a user
    @PostMapping(path="/user", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user.getName()==null) return ResponseEntity.internalServerError().body(user);
        User newUser =  userService.createUser(user);
        if (newUser==null) {
            return ResponseEntity.internalServerError().body(newUser);
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).body(newUser);
        // return new ResponseEntity<User>(newUser, HttpStatusCode.valueOf(201));
        // return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    // To delete specific user
    @DeleteMapping(path="/userd/{id}")
    public ResponseEntity deleteSpecificUser(@PathVariable int id) {
        User user = userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    // To update a user
    @PutMapping(path="/user/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        if (user.getName()==null) return ResponseEntity.internalServerError().body(user);
        User updatedUser =  userService.updateUser(id, user);
        if (updatedUser==null) {
            return ResponseEntity.internalServerError().body(updatedUser);
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updatedUser.getId()).toUri();
        return ResponseEntity.ok().body(updatedUser);
        // return new ResponseEntity<User>(newUser, HttpStatusCode.valueOf(201));
        // return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

}
