package com.microservices.mstraining.test;

import com.microservices.mstraining.controller.UserJpaController;
import com.microservices.mstraining.model.user.User;
import com.microservices.mstraining.repository.UserJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@DataJpaTest
//@WebMvcTest(UserJpaController.class)
public class UserJpaRepositoryTests {

    @Autowired
    UserJpaRepository userJpaRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    public void should_get_all_users() {
        // arrange
        User user1 = User.builder().name("Manibala").dob(LocalDate.now().minusYears(20)).address("Chennai").build();
        //userJpaRepository.save(user1);
        testEntityManager.persistAndFlush(user1);
        // act
        List<User> users = userJpaRepository.findAll();

        // assert
        Assertions.assertEquals(4, users.size());
    }

    @Test
    public void should_get_one_user() {
        // arrange
        User user1 = User.builder().id(1).name("Mani").dob(LocalDate.now().minusYears(20)).build();
        userJpaRepository.save(user1);
        User user = userJpaRepository.findById(1).get();
        Assertions.assertEquals("Mani", user.getName());
    }

    @Test
    public void delete_user() {
        User user1 = User.builder().id(1).name("Mani").dob(LocalDate.now().minusYears(20)).build();
        User save = userJpaRepository.save(user1);
        List<User> users = userJpaRepository.findAll();
        Assertions.assertEquals(4, users.size());

        userJpaRepository.deleteById(user1.getId());

        users = userJpaRepository.findAll();
        Assertions.assertEquals(3, users.size());
    }

    @Test
    public void update_user() {
        User user1 = User.builder().id(1).name("Mani").dob(LocalDate.now().minusYears(20)).build();
        User save = userJpaRepository.save(user1);
        User user = userJpaRepository.findById(1).get();
        user.setName("Kandan");
        user.setDob(LocalDate.now().minusYears(15));
        User updatedUser = userJpaRepository.save(user);

        Assertions.assertEquals("Kandan", updatedUser.getName());
        Assertions.assertEquals(LocalDate.now().minusYears(15), updatedUser.getDob());

    }

}

