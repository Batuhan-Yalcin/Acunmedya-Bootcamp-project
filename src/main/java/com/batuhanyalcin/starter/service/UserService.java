package com.batuhanyalcin.starter.service;

import com.batuhanyalcin.starter.dto.DtoUser;
import com.batuhanyalcin.starter.dto.DtoUserIU;

import java.util.List;

public interface UserService {
    public DtoUser saveUser(DtoUserIU dtoUserIU);
    public List<DtoUser> getUserAll();
    public DtoUser getUserById(Long id);
    public DtoUser updateUser(Long id, DtoUserIU dtoUserIU);
    public boolean deleteUser(Long id);
}
