package com.batuhanyalcin.starter.service;



import com.batuhanyalcin.starter.dto.DtoEmployee;
import com.batuhanyalcin.starter.dto.DtoEmployeeIU;

import java.util.List;

public interface EmployeeService {
    public DtoEmployee saveUser(DtoEmployeeIU dtoEmployeeIU);
    public List<DtoEmployee> getUserAll();
    public DtoEmployee getUserById(Long id);
    public DtoEmployee updateUser(Long id, DtoEmployeeIU dtoEmployeeIU);
    public boolean deleteUser(Long id);
}
