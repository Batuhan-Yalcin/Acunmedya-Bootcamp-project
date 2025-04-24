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

import com.batuhanyalcin.starter.controller.EmployeeController;
import com.batuhanyalcin.starter.dto.DtoEmployee;
import com.batuhanyalcin.starter.dto.DtoEmployeeIU;
import com.batuhanyalcin.starter.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeControllerİmpl implements EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeControllerİmpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    @PostMapping(path = "/save")
    public DtoEmployee saveUser(@RequestBody DtoEmployeeIU dtoEmployeeIU) {
        return employeeService.saveUser(dtoEmployeeIU);
    }

    @Override
    @GetMapping(path = "/getAll")
    public List<DtoEmployee> getUserAll() {
        return employeeService.getUserAll();
    }

    @Override
    @GetMapping(path = "/getById/{id}")
    public DtoEmployee getUserById(@PathVariable Long id) {
        return employeeService.getUserById(id);
    }

    @Override
    @PutMapping(path = "/update/{id}")
    public DtoEmployee updateUser(@PathVariable Long id, @RequestBody DtoEmployeeIU dtoEmployeeIU) {
        return employeeService.updateUser(id, dtoEmployeeIU);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return employeeService.deleteUser(id);
    }
}
