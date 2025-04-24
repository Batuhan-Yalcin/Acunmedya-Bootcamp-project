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

import com.batuhanyalcin.starter.controller.InstructorController;
import com.batuhanyalcin.starter.dto.DtoInstructor;
import com.batuhanyalcin.starter.dto.DtoInstructorIU;
import com.batuhanyalcin.starter.service.InstructorService;

@RestController
@RequestMapping("/api/instructors")
public class InstructorControllerİmpl implements InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorControllerİmpl(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @Override
    @PostMapping(path = "/save")
    public DtoInstructor saveUser(@RequestBody DtoInstructorIU dtoInstructorIU) {
        return instructorService.saveUser(dtoInstructorIU);
    }

    @Override
    @GetMapping(path = "/getAll")
    public List<DtoInstructor> getUserAll() {
        return instructorService.getUserAll();
    }

    @Override
    @GetMapping(path = "/getById/{id}")
    public DtoInstructor getUserById(@PathVariable Long id) {
        return instructorService.getUserById(id);
    }

    @Override
    @PutMapping(path = "/update/{id}")
    public DtoInstructor updateUser(@PathVariable Long id, @RequestBody DtoInstructorIU dtoInstructorIU) {
        return instructorService.updateUser(id, dtoInstructorIU);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return instructorService.deleteUser(id);
    }
}
