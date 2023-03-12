package com.microservices.mstraining.service;

import com.microservices.mstraining.model.user.User;
import com.microservices.mstraining.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserJpaService {

    @Autowired
    UserJpaRepository userJpaRepository;

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
        return userJpaRepository.findAll();
    }

    // get user :  user/1
    public User getUser(int userId) {
        /*Optional<User> byId = userJpaRepository.findById(userId);
        if (byId.isEmpty()) return null;
        return byId.get();*/
        User byId = userJpaRepository.findById(userId).get();
        if (byId==null)
            throw new UserNotFoundException("");
        return byId;
    }

    // create a new user
    // requirements : request body, context url, headers

    public User createUser(User user) {
        User save = userJpaRepository.save(user);
        return save;
    }

    // Delete user :  user/1
    public void deleteUser(int id) {
        userJpaRepository.deleteById(id);
    }

    // update a user
    public User updateUser(int id, User userWithChanges) {
        User actualUser = userJpaRepository.findById(id).get();
        if (userWithChanges==null || actualUser==null)
            throw new UserNotFoundException("User with id "+id+"not found");
        actualUser.setName ( userWithChanges.getName() );
        actualUser.setDob ( userWithChanges.getDob() );
        userJpaRepository.save(actualUser);
        return actualUser;
    }

    //Partial Update of the user
    public User updateUserPartially(int userId, User requestUser)
    {
        int counter = 0;
        for (User user:users)
        {
            if(user.getId()==userId)
            {
                String name = requestUser.getName()!=null ? requestUser.getName() : user.getName();
                LocalDate dob = requestUser.getDob()!=null ? requestUser.getDob() : user.getDob();
                User patchedUser = User.builder().id(userId).name(name).dob(dob).build();
                users.set(counter,patchedUser);
                return patchedUser;
            }
            counter++;
        }
        return null;
    }

    public static User getUserByName(String name){
        for (User user:users){
            if(user.getName().equals(name))return user;
        }
        return null;
    }



}
