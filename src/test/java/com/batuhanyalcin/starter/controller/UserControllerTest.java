package com.batuhanyalcin.starter.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.batuhanyalcin.starter.controller.impl.UserControllerİmpl;
import com.batuhanyalcin.starter.dto.DtoUser;
import com.batuhanyalcin.starter.dto.DtoUserIU;
import com.batuhanyalcin.starter.service.UserService;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserControllerİmpl userController;

    private DtoUser dtoUser;
    private DtoUserIU dtoUserIU;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();

        dtoUser = new DtoUser();
        dtoUser.setUsername("testuser");
        dtoUser.setFirstName("Test");
        dtoUser.setLastName("User");
        dtoUser.setEmail("test@example.com");
        dtoUser.setDateOfBirth(new Date());

        dtoUserIU = new DtoUserIU();
        dtoUserIU.setUsername("testuser");
        dtoUserIU.setFirstName("Test");
        dtoUserIU.setLastName("User");
        dtoUserIU.setEmail("test@example.com");
        dtoUserIU.setPassword("password123");
        dtoUserIU.setNationalIdentity(12345678);
        dtoUserIU.setDateOfBirth("2000-01-01");
    }

    @Test
    void testSaveUser() throws Exception {
        when(userService.saveUser(any(DtoUserIU.class))).thenReturn(dtoUser);

        mockMvc.perform(post("/api/users/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testuser\",\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"test@example.com\",\"password\":\"password123\",\"nationalIdentity\":12345678,\"dateOfBirth\":\"2000-01-01\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("testuser")))
                .andExpect(jsonPath("$.firstName", is("Test")))
                .andExpect(jsonPath("$.lastName", is("User")))
                .andExpect(jsonPath("$.email", is("test@example.com")));
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<DtoUser> userList = new ArrayList<>();
        userList.add(dtoUser);

        when(userService.getUserAll()).thenReturn(userList);

        mockMvc.perform(get("/api/users/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].username", is("testuser")))
                .andExpect(jsonPath("$[0].firstName", is("Test")))
                .andExpect(jsonPath("$[0].lastName", is("User")))
                .andExpect(jsonPath("$[0].email", is("test@example.com")));
    }

    @Test
    void testGetUserById() throws Exception {
        when(userService.getUserById(anyLong())).thenReturn(dtoUser);

        mockMvc.perform(get("/api/users/getById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("testuser")))
                .andExpect(jsonPath("$.firstName", is("Test")))
                .andExpect(jsonPath("$.lastName", is("User")))
                .andExpect(jsonPath("$.email", is("test@example.com")));
    }

    @Test
    void testUpdateUser() throws Exception {
        when(userService.updateUser(anyLong(), any(DtoUserIU.class))).thenReturn(dtoUser);

        mockMvc.perform(put("/api/users/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testuser\",\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"test@example.com\",\"password\":\"password123\",\"nationalIdentity\":12345678,\"dateOfBirth\":\"2000-01-01\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("testuser")))
                .andExpect(jsonPath("$.firstName", is("Test")))
                .andExpect(jsonPath("$.lastName", is("User")))
                .andExpect(jsonPath("$.email", is("test@example.com")));
    }

    @Test
    void testDeleteUser() throws Exception {
        when(userService.deleteUser(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/users/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
} 