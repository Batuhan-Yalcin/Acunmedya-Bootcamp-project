package com.batuhanyalcin.starter.controller;

import com.batuhanyalcin.starter.dto.DtoInstructor;
import com.batuhanyalcin.starter.dto.DtoInstructorIU;

import java.util.List;

public interface InstructorController {
    public DtoInstructor saveUser(DtoInstructorIU dtoInstructorIU);
    public List<DtoInstructor> getUserAll();
    public DtoInstructor getUserById(Long id);
    public DtoInstructor updateUser(Long id, DtoInstructorIU dtoInstructorIU);
    public boolean deleteUser(Long id);
}
