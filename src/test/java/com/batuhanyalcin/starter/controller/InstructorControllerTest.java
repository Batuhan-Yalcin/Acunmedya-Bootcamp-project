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

import com.batuhanyalcin.starter.controller.impl.InstructorControllerİmpl;
import com.batuhanyalcin.starter.dto.DtoInstructor;
import com.batuhanyalcin.starter.dto.DtoInstructorIU;
import com.batuhanyalcin.starter.service.InstructorService;

public class InstructorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private InstructorService instructorService;

    @InjectMocks
    private InstructorControllerİmpl instructorController;

    private DtoInstructor dtoInstructor;
    private DtoInstructorIU dtoInstructorIU;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(instructorController)
                .build();

        dtoInstructor = new DtoInstructor();
        dtoInstructor.setCompanyName("Test Şirketi");

        dtoInstructorIU = new DtoInstructorIU();
        dtoInstructorIU.setCompanyName("Test Şirketi");
    }

    @Test
    void testSaveInstructor() throws Exception {
        when(instructorService.saveUser(any(DtoInstructorIU.class))).thenReturn(dtoInstructor);

        mockMvc.perform(post("/api/instructors/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"companyName\":\"Test Şirketi\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName", is("Test Şirketi")));
    }

    @Test
    void testGetAllInstructors() throws Exception {
        List<DtoInstructor> instructorList = new ArrayList<>();
        instructorList.add(dtoInstructor);

        when(instructorService.getUserAll()).thenReturn(instructorList);

        mockMvc.perform(get("/api/instructors/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].companyName", is("Test Şirketi")));
    }

    @Test
    void testGetInstructorById() throws Exception {
        when(instructorService.getUserById(anyLong())).thenReturn(dtoInstructor);

        mockMvc.perform(get("/api/instructors/getById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName", is("Test Şirketi")));
    }

    @Test
    void testUpdateInstructor() throws Exception {
        when(instructorService.updateUser(anyLong(), any(DtoInstructorIU.class))).thenReturn(dtoInstructor);

        mockMvc.perform(put("/api/instructors/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"companyName\":\"Test Şirketi\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName", is("Test Şirketi")));
    }

    @Test
    void testDeleteInstructor() throws Exception {
        when(instructorService.deleteUser(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/instructors/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
} 