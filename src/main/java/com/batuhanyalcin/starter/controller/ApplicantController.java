package com.batuhanyalcin.starter.controller;

import com.batuhanyalcin.starter.dto.DtoApplicant;
import com.batuhanyalcin.starter.dto.DtoApplicantIU;

import java.util.List;

public interface ApplicantController {

    public DtoApplicant saveUser(DtoApplicantIU dtoApplicantIU);
    public List<DtoApplicant> getUserAll();
    public DtoApplicant getUserById(Long id);
    public DtoApplicant updateUser(Long id, DtoApplicantIU dtoApplicantIU);
    public boolean deleteUser(Long id);

}
