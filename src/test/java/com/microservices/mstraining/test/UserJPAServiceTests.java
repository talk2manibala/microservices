package com.microservices.mstraining.test;

import com.microservices.mstraining.model.user.User;
import com.microservices.mstraining.repository.UserJpaRepository;
import com.microservices.mstraining.service.UserJpaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class) // Either use this annotation or use the setup method with BeforeEach; Both same
public class UserJPAServiceTests {

    @Mock
    UserJpaRepository userJpaRepository;

    @InjectMocks
    UserJpaService userJpaService;

    @BeforeEach // Either use this method or use the @ExtendWith(MockitoExtension.class in class level; Both same
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void get_all_users() {
        // arrange
        List<User> users = Arrays.asList(User.builder().id(1).name("Mani").dob(LocalDate.now().minusYears(20)).build(),
                User.builder().id(2).name("Bala").dob(LocalDate.now().minusYears(20)).build());
        Mockito.when(userJpaRepository.findAll()).thenReturn(users);

        // act
        List<User> userList = userJpaService.getAllUsers();

        // assert
        Assertions.assertEquals(2, userList.size());
        Assertions.assertEquals("Bala", userList.get(1).getName());
    }

}
