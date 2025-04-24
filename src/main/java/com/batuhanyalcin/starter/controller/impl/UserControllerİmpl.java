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

import com.batuhanyalcin.starter.controller.UserController;
import com.batuhanyalcin.starter.dto.DtoUser;
import com.batuhanyalcin.starter.dto.DtoUserIU;
import com.batuhanyalcin.starter.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserControllerİmpl implements UserController {

    private final UserService userService;

    @Autowired
    public UserControllerİmpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping(path = "/save")
    public DtoUser saveUser(@RequestBody DtoUserIU dtoUserIU) {
        return userService.saveUser(dtoUserIU);
    }

    @Override
    @GetMapping(path = "/getAll")
    public List<DtoUser> getUserAll() {
        return userService.getUserAll();
    }

    @Override
    @GetMapping(path = "/getById/{id}")
    public DtoUser getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Override
    @PutMapping(path = "/update/{id}")
    public DtoUser updateUser(@PathVariable Long id, @RequestBody DtoUserIU dtoUserIU) {
        return userService.updateUser(id, dtoUserIU);
    }

    @Override
    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
