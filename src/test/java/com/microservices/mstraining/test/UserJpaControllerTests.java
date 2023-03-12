package com.microservices.mstraining.test;

import com.microservices.mstraining.controller.UserJpaController;
import com.microservices.mstraining.model.user.User;
import com.microservices.mstraining.service.UserJpaService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.print.attribute.standard.Media;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(UserJpaController.class)
@ExtendWith(SpringExtension.class)
public class UserJpaControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserJpaService userJpaService;

    @Test
    @SneakyThrows
    public void should_return_all_users()  {

        // arrange
        List<User> users = Arrays.asList(User.builder().id(1).name("Mani").dob(LocalDate.now().minusYears(20)).build(),
                User.builder().id(2).name("Bala").dob(LocalDate.now().minusYears(20)).build());
        Mockito.when(userJpaService.getAllUsers()).thenReturn(users);

        // act and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/jpa/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print());

    }

    @Test
    @SneakyThrows
    public void should_get_one_user()  {

        // arrange
        User user = User.builder().id(1).name("Mani").dob(LocalDate.now().minusYears(20)).build();
        Mockito.when(userJpaService.getUser(anyInt())).thenReturn(user);

        // act and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/jpa/user/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("Mani"))
                .andExpect(MockMvcResultMatchers.jsonPath("dob").value(LocalDate.now().minusYears(20)))
                .andDo(print());
    }

}
