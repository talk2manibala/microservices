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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

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
        when(userJpaRepository.findAll()).thenReturn(users);

        // act
        List<User> userList = userJpaService.getAllUsers();

        // assert
        Assertions.assertEquals(2, userList.size());
        Assertions.assertEquals("Bala", userList.get(1).getName());
    }

    @Test
    public void get_specific_user() {
        // arrange
        User user1 = User.builder().id(1).name("Mani").dob(LocalDate.now().minusYears(20)).build();
        User user2 = User.builder().id(2).name("Bala").dob(LocalDate.now().minusYears(20)).build();
        when(userJpaRepository.findById(anyInt()))
                .thenReturn(Optional.of(user1))
                .thenReturn(Optional.of(user2));
        //when(userJpaRepository.findById(1)).thenReturn(Optional.of(user1));

        // act
        User user_1 = userJpaService.getUser(1);
        User user_2 = userJpaService.getUser(2);

        // assert
        Assertions.assertEquals("Mani", user_1.getName());
        Assertions.assertEquals("Bala", user_2.getName());

    }

}
