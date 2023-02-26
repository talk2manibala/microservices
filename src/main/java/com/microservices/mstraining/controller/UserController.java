package com.microservices.mstraining.controller;

import com.microservices.mstraining.model.User;
import com.microservices.mstraining.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/users")
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
        if (user.getName()==null || user.getName().isEmpty() || user.getDob()==null || user.getDob().toString().isEmpty()) {
            user.setRespMsg("Name and Date of birth is mandatory");
            return ResponseEntity.unprocessableEntity().body(user);
        }
        User newUser =  userService.createUser(user);
        if (newUser==null) {
            return ResponseEntity.internalServerError().body(newUser);
        }
        newUser.setRespMsg("Success");
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).body(newUser);
        // return new ResponseEntity<User>(newUser, HttpStatusCode.valueOf(201));
        // return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    // To delete specific user
    @DeleteMapping(path="/userd/{id}")
    public ResponseEntity deleteSpecificUser(@PathVariable int id) {
        boolean isTrue = userService.deleteUser(id);
        if (!isTrue)
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    // To update a user
    @PutMapping(path="/user/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        if (user.getName()==null && user.getDob()==null) {
            user.setRespMsg("Name and Date of birth is mandatory");
            return ResponseEntity.internalServerError().body(user);
        }
        User updatedUser =  userService.updateUser(id, user);
        return ResponseEntity.ok().body(updatedUser);
    }

    // To get specific user
    @GetMapping(path="/user/v2/{id}", consumes = "applicatoin/json", produces = "application/json")
    public ResponseEntity<User> getSpecificUserV2(@PathVariable int id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok().body(user);
    }




}
