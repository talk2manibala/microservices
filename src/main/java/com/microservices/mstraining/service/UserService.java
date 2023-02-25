package com.microservices.mstraining.service;

import com.microservices.mstraining.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public static ArrayList<User> emplmoyeeList = new ArrayList<>();
    public static int id = 0;

    static {
        User roger = User.builder().id(++id).name("Roger").dob(LocalDate.now().minusYears(20)).build();
        User rafa = User.builder().id(++id).name("Rafa").dob(LocalDate.now().minusYears(20)).build();
        User rafael = User.builder().id(++id).name("Rafael").dob(LocalDate.now().minusYears(20)).build();
        emplmoyeeList.add(roger);
        emplmoyeeList.add(rafa);
        emplmoyeeList.add(rafael);
    }

    public List<User> getAllEmplmoyees() {
        return emplmoyeeList;
    }

    // get user :  user/1
    public User getUser(int id) {
        User user = emplmoyeeList.stream().filter(x->x.getId()==id).findFirst().orElse(null);
        if (user==null) throw new UserNotFoundException("User with id "+id+"not found");
        return user;
    }

    // create a new user
    // requirements : request body, context url, headers

    public User createUser(User user) {
        User newUser = User.builder().id(++id).name(user.getName()).dob(user.getDob()).build();
        emplmoyeeList.add(newUser);
        return newUser;
    }


    // delete a user

    // update a empmloyee

}
