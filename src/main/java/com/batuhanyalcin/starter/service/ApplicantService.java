package com.batuhanyalcin.starter.service;

import com.batuhanyalcin.starter.dto.DtoApplicant;
import com.batuhanyalcin.starter.dto.DtoApplicantIU;
import com.batuhanyalcin.starter.dto.DtoUser;
import com.batuhanyalcin.starter.dto.DtoUserIU;

import java.util.List;

public interface ApplicantService {
    public DtoApplicant saveUser(DtoApplicantIU dtoApplicantIU);
    public List<DtoApplicant> getUserAll();
    public DtoApplicant getUserById(Long id);
    public DtoApplicant updateUser(Long id, DtoApplicantIU dtoApplicantIU);
    public boolean deleteUser(Long id);

}
