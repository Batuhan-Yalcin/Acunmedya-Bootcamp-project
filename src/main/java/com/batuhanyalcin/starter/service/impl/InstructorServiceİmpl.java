package com.batuhanyalcin.starter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batuhanyalcin.starter.dto.DtoInstructor;
import com.batuhanyalcin.starter.dto.DtoInstructorIU;
import com.batuhanyalcin.starter.entity.Instructor;
import com.batuhanyalcin.starter.repository.InstructorRepository;
import com.batuhanyalcin.starter.service.InstructorService;

@Service
public class InstructorServiceİmpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceİmpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public DtoInstructor saveUser(DtoInstructorIU dtoInstructorIU) {
        Instructor instructor = new Instructor();
        instructor.setCompanyName(dtoInstructorIU.getCompanyName());
        
        Instructor savedInstructor = instructorRepository.save(instructor);
        
        DtoInstructor dtoInstructor = new DtoInstructor();
        dtoInstructor.setCompanyName(savedInstructor.getCompanyName());
        
        return dtoInstructor;
    }

    @Override
    public List<DtoInstructor> getUserAll() {
        List<Instructor> instructors = instructorRepository.findAll();
        List<DtoInstructor> dtoInstructors = new ArrayList<>();
        
        for (Instructor instructor : instructors) {
            DtoInstructor dtoInstructor = new DtoInstructor();
            dtoInstructor.setCompanyName(instructor.getCompanyName());
            dtoInstructors.add(dtoInstructor);
        }
        
        return dtoInstructors;
    }

    @Override
    public DtoInstructor getUserById(Long id) {
        Optional<Instructor> instructorOptional = instructorRepository.findById(id);
        
        if (instructorOptional.isPresent()) {
            Instructor instructor = instructorOptional.get();
            DtoInstructor dtoInstructor = new DtoInstructor();
            dtoInstructor.setCompanyName(instructor.getCompanyName());
            return dtoInstructor;
        }
        
        return null;
    }

    @Override
    public DtoInstructor updateUser(Long id, DtoInstructorIU dtoInstructorIU) {
        Optional<Instructor> instructorOptional = instructorRepository.findById(id);
        
        if (instructorOptional.isPresent()) {
            Instructor instructor = instructorOptional.get();
            instructor.setCompanyName(dtoInstructorIU.getCompanyName());
            
            Instructor updatedInstructor = instructorRepository.save(instructor);
            
            DtoInstructor dtoInstructor = new DtoInstructor();
            dtoInstructor.setCompanyName(updatedInstructor.getCompanyName());
            return dtoInstructor;
        }
        
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        if (instructorRepository.existsById(id)) {
            instructorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
