package com.batuhanyalcin.starter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batuhanyalcin.starter.dto.DtoEmployee;
import com.batuhanyalcin.starter.dto.DtoEmployeeIU;
import com.batuhanyalcin.starter.entity.Employee;
import com.batuhanyalcin.starter.repository.EmployeeRepository;
import com.batuhanyalcin.starter.service.EmployeeService;

@Service
public class EmployeeServiceİmpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceİmpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public DtoEmployee saveUser(DtoEmployeeIU dtoEmployeeIU) {
        Employee employee = new Employee();
        employee.setPozition(dtoEmployeeIU.getPozition());
        
        Employee savedEmployee = employeeRepository.save(employee);
        
        DtoEmployee dtoEmployee = new DtoEmployee();
        dtoEmployee.setPozition(savedEmployee.getPozition());
        
        return dtoEmployee;
    }

    @Override
    public List<DtoEmployee> getUserAll() {
        List<Employee> employees = employeeRepository.findAll();
        List<DtoEmployee> dtoEmployees = new ArrayList<>();
        
        for (Employee employee : employees) {
            DtoEmployee dtoEmployee = new DtoEmployee();
            dtoEmployee.setPozition(employee.getPozition());
            dtoEmployees.add(dtoEmployee);
        }
        
        return dtoEmployees;
    }

    @Override
    public DtoEmployee getUserById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            DtoEmployee dtoEmployee = new DtoEmployee();
            dtoEmployee.setPozition(employee.getPozition());
            return dtoEmployee;
        }
        
        return null;
    }

    @Override
    public DtoEmployee updateUser(Long id, DtoEmployeeIU dtoEmployeeIU) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setPozition(dtoEmployeeIU.getPozition());
            
            Employee updatedEmployee = employeeRepository.save(employee);
            
            DtoEmployee dtoEmployee = new DtoEmployee();
            dtoEmployee.setPozition(updatedEmployee.getPozition());
            return dtoEmployee;
        }
        
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
