package com.batuhanyalcin.starter.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batuhanyalcin.starter.core.User;
import com.batuhanyalcin.starter.dto.DtoUser;
import com.batuhanyalcin.starter.dto.DtoUserIU;
import com.batuhanyalcin.starter.repository.UserRepository;
import com.batuhanyalcin.starter.service.UserService;

@Service
public class UserServiceİmpl implements UserService {

    private final UserRepository userRepository;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public UserServiceİmpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public DtoUser saveUser(DtoUserIU dtoUserIU) {
        User user = new User();
        user.setUsername(dtoUserIU.getUsername());
        user.setFirstName(dtoUserIU.getFirstName());
        user.setLastName(dtoUserIU.getLastName());
        user.setEmail(dtoUserIU.getEmail());
        user.setPassword(dtoUserIU.getPassword());
        user.setNationalIdentity(dtoUserIU.getNationalIdentity());
        
        try {
            Date birthDate = dateFormat.parse(dtoUserIU.getDateOfBirth());
            user.setDateOfBirth(birthDate);
        } catch (ParseException e) {
            throw new RuntimeException("Tarih formatı hatalı. Lütfen yyyy-MM-dd formatında girin.", e);
        }
        
        User savedUser = userRepository.save(user);
        
        DtoUser dtoUser = new DtoUser();
        dtoUser.setUsername(savedUser.getUsername());
        dtoUser.setFirstName(savedUser.getFirstName());
        dtoUser.setLastName(savedUser.getLastName());
        dtoUser.setEmail(savedUser.getEmail());
        dtoUser.setDateOfBirth(savedUser.getDateOfBirth());
        
        return dtoUser;
    }

    @Override
    public List<DtoUser> getUserAll() {
        List<User> users = userRepository.findAll();
        List<DtoUser> dtoUsers = new ArrayList<>();
        
        for (User user : users) {
            DtoUser dtoUser = new DtoUser();
            dtoUser.setUsername(user.getUsername());
            dtoUser.setFirstName(user.getFirstName());
            dtoUser.setLastName(user.getLastName());
            dtoUser.setEmail(user.getEmail());
            dtoUser.setDateOfBirth(user.getDateOfBirth());
            dtoUsers.add(dtoUser);
        }
        
        return dtoUsers;
    }

    @Override
    public DtoUser getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            DtoUser dtoUser = new DtoUser();
            dtoUser.setUsername(user.getUsername());
            dtoUser.setFirstName(user.getFirstName());
            dtoUser.setLastName(user.getLastName());
            dtoUser.setEmail(user.getEmail());
            dtoUser.setDateOfBirth(user.getDateOfBirth());
            return dtoUser;
        }
        
        return null;
    }

    @Override
    public DtoUser updateUser(Long id, DtoUserIU dtoUserIU) {
        Optional<User> userOptional = userRepository.findById(id);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(dtoUserIU.getUsername());
            user.setFirstName(dtoUserIU.getFirstName());
            user.setLastName(dtoUserIU.getLastName());
            user.setEmail(dtoUserIU.getEmail());
            user.setPassword(dtoUserIU.getPassword());
            user.setNationalIdentity(dtoUserIU.getNationalIdentity());
            
            try {
                Date birthDate = dateFormat.parse(dtoUserIU.getDateOfBirth());
                user.setDateOfBirth(birthDate);
            } catch (ParseException e) {
                throw new RuntimeException("Tarih formatı hatalı. Lütfen yyyy-MM-dd formatında girin.", e);
            }
            
            User updatedUser = userRepository.save(user);
            
            DtoUser dtoUser = new DtoUser();
            dtoUser.setUsername(updatedUser.getUsername());
            dtoUser.setFirstName(updatedUser.getFirstName());
            dtoUser.setLastName(updatedUser.getLastName());
            dtoUser.setEmail(updatedUser.getEmail());
            dtoUser.setDateOfBirth(updatedUser.getDateOfBirth());
            return dtoUser;
        }
        
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
