package com.batuhanyalcin.starter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batuhanyalcin.starter.dto.DtoApplicant;
import com.batuhanyalcin.starter.dto.DtoApplicantIU;
import com.batuhanyalcin.starter.entity.Applicant;
import com.batuhanyalcin.starter.repository.ApllicantRepository;
import com.batuhanyalcin.starter.service.ApplicantService;

@Service
public class ApplicantServiceİmpl implements ApplicantService {

    private final ApllicantRepository apllicantRepository;

    @Autowired
    public ApplicantServiceİmpl(ApllicantRepository apllicantRepository) {
        this.apllicantRepository = apllicantRepository;
    }

    @Override
    public DtoApplicant saveUser(DtoApplicantIU dtoApplicantIU) {
        Applicant applicant = new Applicant();
        applicant.setAbout(dtoApplicantIU.getAbout());
        
        Applicant savedApplicant = apllicantRepository.save(applicant);
        
        DtoApplicant dtoApplicant = new DtoApplicant();
        dtoApplicant.setAbout(savedApplicant.getAbout());
        
        return dtoApplicant;
    }

    @Override
    public List<DtoApplicant> getUserAll() {
        List<Applicant> applicants = apllicantRepository.findAll();
        List<DtoApplicant> dtoApplicants = new ArrayList<>();
        
        for (Applicant applicant : applicants) {
            DtoApplicant dtoApplicant = new DtoApplicant();
            dtoApplicant.setAbout(applicant.getAbout());
            dtoApplicants.add(dtoApplicant);
        }
        
        return dtoApplicants;
    }

    @Override
    public DtoApplicant getUserById(Long id) {
        Optional<Applicant> applicantOptional = apllicantRepository.findById(id);
        
        if (applicantOptional.isPresent()) {
            Applicant applicant = applicantOptional.get();
            DtoApplicant dtoApplicant = new DtoApplicant();
            dtoApplicant.setAbout(applicant.getAbout());
            return dtoApplicant;
        }
        
        return null;
    }

    @Override
    public DtoApplicant updateUser(Long id, DtoApplicantIU dtoApplicantIU) {
        Optional<Applicant> applicantOptional = apllicantRepository.findById(id);
        
        if (applicantOptional.isPresent()) {
            Applicant applicant = applicantOptional.get();
            applicant.setAbout(dtoApplicantIU.getAbout());
            
            Applicant updatedApplicant = apllicantRepository.save(applicant);
            
            DtoApplicant dtoApplicant = new DtoApplicant();
            dtoApplicant.setAbout(updatedApplicant.getAbout());
            return dtoApplicant;
        }
        
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        if (apllicantRepository.existsById(id)) {
            apllicantRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
