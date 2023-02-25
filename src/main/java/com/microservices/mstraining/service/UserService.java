package com.microservices.mstraining.service;

import com.microservices.mstraining.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public static ArrayList<User> users = new ArrayList<>();
    public static int id = 0;

    static {
        User roger = User.builder().id(++id).name("Roger").dob(LocalDate.now().minusYears(20)).build();
        User rafa = User.builder().id(++id).name("Rafa").dob(LocalDate.now().minusYears(20)).build();
        User rafael = User.builder().id(++id).name("Rafael").dob(LocalDate.now().minusYears(20)).build();
        users.add(roger);
        users.add(rafa);
        users.add(rafael);
    }

    public List<User> getAllUsers() {
        return users;
    }

    // get user :  user/1
    public User getUser(int id) {
        User user = users.stream().filter(x->x.getId()==id).findFirst().orElse(null);
        if (user==null) throw new UserNotFoundException("User with id "+id+"not found");
        return user;
    }

    // create a new user
    // requirements : request body, context url, headers

    public User createUser(User user) {
        User newUser = User.builder().id(users.size()+1).name(user.getName()).dob(user.getDob()).build();
        // newUser.setId(++id); // Add id like this also work
        // newUser.setId(users.size()+1); // This will also work
        users.add(newUser);
        return newUser;
    }

    // Delete user :  user/1
    public User deleteUser(int id) {
        User user = getUser(id);
        if (user==null) throw new UserNotFoundException("User with id "+id+"not found");
        users.remove(user);
        return user;
    }

    // update a user
    public User updateUser(int id, User userWithChanges) {
        User actualUser = getUser(id);
        if (userWithChanges==null || actualUser==null) throw new UserNotFoundException("User with id "+id+"not found");
        actualUser.setName(userWithChanges.getName());
        actualUser.setDob(userWithChanges.getDob());
        return actualUser;
    }
}
