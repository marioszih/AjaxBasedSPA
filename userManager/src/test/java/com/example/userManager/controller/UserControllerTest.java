package com.example.userManager.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import com.example.userManager.model.User;
import com.example.userManager.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;



@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMv;

    @MockBean
    private UserRepository userRep;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1);
        user.setName("marios");
        user.setSurname("zich");
        user.setEmail("marioszich@gmail.com");
    }

    @Test
    public void getAllUsersTest() throws Exception{
        when(userRep.findAll()).thenReturn(Arrays.asList(user));

        mockMv.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(1))
        .andExpect(jsonPath("$[0].name").value("marios"))
        .andExpect(jsonPath("$[0].surname").value("zich"))
        .andExpect(jsonPath("$[0].email").value("marioszich@gmail.com"));
    }

    @Test
    public void createUserTest() throws Exception{
        when(userRep.save(any(User.class))).thenReturn(user);

        mockMv.perform(post("/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(user)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.name").value("marios"))
        .andExpect(jsonPath("$.surname").value("zich"))
        .andExpect(jsonPath("$.email").value("marioszich@gmail.com"));
    }

    @Test
    public void deleteUserTest() throws Exception{
        when(userRep.existsById(1)).thenReturn(true);
        doNothing().when(userRep).deleteById(any(Integer.class));

        mockMv.perform(delete("/user/1"))
        .andExpect(status().isOk());
    }

    @Test
    public void getUserTest() throws Exception{
        when(userRep.findById(any(Integer.class))).thenReturn(Optional.of(user));

        mockMv.perform(get("/edit/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.name").value("marios"))
        .andExpect(jsonPath("$.surname").value("zich"))
        .andExpect(jsonPath("$.email").value("marioszich@gmail.com"));
    }

    @Test
    public void editUserTest() throws Exception{
        when(userRep.getReferenceById(any(Integer.class))).thenReturn(user);

        User editedUser = new User();
        editedUser.setName("giannis");
        editedUser.setSurname("char");
        editedUser.setEmail("giannischar@gmail.com");
        mockMv.perform(put("/edit/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(editedUser)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("giannis"))
        .andExpect(jsonPath("$.surname").value("char"))
        .andExpect(jsonPath("$.email").value("giannischar@gmail.com"));
    }

}
