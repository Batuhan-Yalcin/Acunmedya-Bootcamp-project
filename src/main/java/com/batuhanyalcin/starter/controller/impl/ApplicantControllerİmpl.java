package com.batuhanyalcin.starter.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.batuhanyalcin.starter.controller.ApplicantController;
import com.batuhanyalcin.starter.dto.DtoApplicant;
import com.batuhanyalcin.starter.dto.DtoApplicantIU;
import com.batuhanyalcin.starter.service.ApplicantService;

@RestController
@RequestMapping("/api/applicants")
public class ApplicantControllerİmpl implements ApplicantController {

    private final ApplicantService applicantService;

    @Autowired
    public ApplicantControllerİmpl(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @Override
    @PostMapping(path = "/save")
    public DtoApplicant saveUser(@RequestBody DtoApplicantIU dtoApplicantIU) {
        return applicantService.saveUser(dtoApplicantIU);
    }

    @Override
    @GetMapping(path = "/getAll")
    public List<DtoApplicant> getUserAll() {
        return applicantService.getUserAll();
    }

    @Override
        @GetMapping(path = "/id/{id}")
        public DtoApplicant getUserById(@PathVariable Long id) {
        return applicantService.getUserById(id);
    }

    @Override
    @PutMapping(path = "/update/{id}")
    public DtoApplicant updateUser(@PathVariable Long id, @RequestBody DtoApplicantIU dtoApplicantIU) {
        return applicantService.updateUser(id, dtoApplicantIU);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return applicantService.deleteUser(id);
    }
}
