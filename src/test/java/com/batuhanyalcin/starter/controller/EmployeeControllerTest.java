package com.batuhanyalcin.starter.controller;

import java.util.ArrayList;
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

import com.batuhanyalcin.starter.controller.impl.EmployeeControllerİmpl;
import com.batuhanyalcin.starter.dto.DtoEmployee;
import com.batuhanyalcin.starter.dto.DtoEmployeeIU;
import com.batuhanyalcin.starter.service.EmployeeService;

public class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeControllerİmpl employeeController;

    private DtoEmployee dtoEmployee;
    private DtoEmployeeIU dtoEmployeeIU;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(employeeController)
                .build();

        dtoEmployee = new DtoEmployee();
        dtoEmployee.setPozition("Developer");

        dtoEmployeeIU = new DtoEmployeeIU();
        dtoEmployeeIU.setPozition("Developer");
    }

    @Test
    void testSaveEmployee() throws Exception {
        when(employeeService.saveUser(any(DtoEmployeeIU.class))).thenReturn(dtoEmployee);

        mockMvc.perform(post("/api/employees/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"pozition\":\"Developer\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pozition", is("Developer")));
    }

    @Test
    void testGetAllEmployees() throws Exception {
        List<DtoEmployee> employeeList = new ArrayList<>();
        employeeList.add(dtoEmployee);

        when(employeeService.getUserAll()).thenReturn(employeeList);

        mockMvc.perform(get("/api/employees/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].pozition", is("Developer")));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        when(employeeService.getUserById(anyLong())).thenReturn(dtoEmployee);

        mockMvc.perform(get("/api/employees/getById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pozition", is("Developer")));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        when(employeeService.updateUser(anyLong(), any(DtoEmployeeIU.class))).thenReturn(dtoEmployee);

        mockMvc.perform(put("/api/employees/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"pozition\":\"Developer\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pozition", is("Developer")));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        when(employeeService.deleteUser(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/employees/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
} 