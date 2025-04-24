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

import com.batuhanyalcin.starter.controller.impl.ApplicantControllerİmpl;
import com.batuhanyalcin.starter.dto.DtoApplicant;
import com.batuhanyalcin.starter.dto.DtoApplicantIU;
import com.batuhanyalcin.starter.service.ApplicantService;

public class ApplicantControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ApplicantService applicantService;

    @InjectMocks
    private ApplicantControllerİmpl applicantController;

    private DtoApplicant dtoApplicant;
    private DtoApplicantIU dtoApplicantIU;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(applicantController)
                .build();

        dtoApplicant = new DtoApplicant();
        dtoApplicant.setAbout("Test hakkında bilgi");

        dtoApplicantIU = new DtoApplicantIU();
        dtoApplicantIU.setAbout("Test hakkında bilgi");
    }

    @Test
    void testSaveApplicant() throws Exception {
        when(applicantService.saveUser(any(DtoApplicantIU.class))).thenReturn(dtoApplicant);

        mockMvc.perform(post("/api/applicants/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"about\":\"Test hakkında bilgi\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.about", is("Test hakkında bilgi")));
    }

    @Test
    void testGetAllApplicants() throws Exception {
        List<DtoApplicant> applicantList = new ArrayList<>();
        applicantList.add(dtoApplicant);

        when(applicantService.getUserAll()).thenReturn(applicantList);

        mockMvc.perform(get("/api/applicants/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].about", is("Test hakkında bilgi")));
    }

    @Test
    void testGetApplicantById() throws Exception {
        when(applicantService.getUserById(anyLong())).thenReturn(dtoApplicant);

        mockMvc.perform(get("/api/applicants/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.about", is("Test hakkında bilgi")));
    }

    @Test
    void testUpdateApplicant() throws Exception {
        when(applicantService.updateUser(anyLong(), any(DtoApplicantIU.class))).thenReturn(dtoApplicant);

        mockMvc.perform(put("/api/applicants/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"about\":\"Test hakkında bilgi\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.about", is("Test hakkında bilgi")));
    }

    @Test
    void testDeleteApplicant() throws Exception {
        when(applicantService.deleteUser(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/applicants/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
} 