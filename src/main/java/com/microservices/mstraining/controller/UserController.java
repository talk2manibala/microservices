package com.microservices.mstraining.controller;

import com.microservices.mstraining.model.user.User;
import com.microservices.mstraining.service.UserNotFoundException;
import com.microservices.mstraining.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Autowired
    MessageSource messageSource;

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
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
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

    //Partial Update of the user using patch
    @PatchMapping(path="/userpatch/{userId}",consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> updateSpecificUserPartially(@PathVariable int userId, @RequestBody User user)
    {
        User patchUser = userService.updateUserPartially(userId, user);
        if(patchUser==null)
            throw new UserNotFoundException("user with id "+ userId+" not found");
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("").buildAndExpand(patchUser.getId()).toUri();
        return ResponseEntity.status(HttpStatus.OK).location(uri).body(patchUser);
    }



    // Getting a specific user using query param
    @GetMapping(path="/getuserqueryparam",
            produces = "application/json")
    public ResponseEntity<User> getSpecificUserQuery(@RequestParam("name") String name){
        User user = userService.getUserByName(name);
        if(user==null) throw new UserNotFoundException("user with name "+ name+" not found");
        return ResponseEntity.ok().body(user);
    }


    // Getting a specific user using query param
    @GetMapping(path="/getuserqueryparamId",
            produces = "application/json")
    public ResponseEntity<User> getSpecificUserQueryId(@RequestParam("userId") int userId){
        User user = userService.getUser(userId);
        if(user==null) throw new UserNotFoundException("user with ID "+ userId+" not found");
        return ResponseEntity.ok().body(user);
    }

    //MediaType.APPLICATION_JSON_VALUE
    // Getting a specific user using query param and setting header with mime type
    @GetMapping(path="/getuserqueryparamIdMime",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getSpecificUserQueryIdmime(@RequestParam("userId") int userId){
        User user = userService.getUser(userId);
        if(user==null) throw new UserNotFoundException("user with ID "+ userId+" not found");
        return ResponseEntity.ok().body(user);
    }


    // i18n Localisation for error message
    @GetMapping(path="/usersByQueryParams",
            produces = "application/json")
    public ResponseEntity getAllUsersByQueryParam(@RequestParam("name") String name){
        User user = userService.getUserByName(name);
        Locale locale = LocaleContextHolder.getLocale();
        if(user==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageSource.getMessage("user.not.found",null,locale));
        return new ResponseEntity<User>(user, HttpStatus.OK);

    }

}
