package com.batuhanyalcin.starter.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.batuhanyalcin.starter.core.User;
import com.batuhanyalcin.starter.dto.DtoUser;
import com.batuhanyalcin.starter.dto.DtoUserIU;
import com.batuhanyalcin.starter.repository.UserRepository;
import com.batuhanyalcin.starter.service.impl.UserServiceİmpl;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceİmpl userService;

    private User user;
    private DtoUserIU dtoUserIU;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setNationalIdentity(12345678);
        user.setDateOfBirth(new Date());

        dtoUserIU = new DtoUserIU();
        dtoUserIU.setUsername("testuser");
        dtoUserIU.setFirstName("Test");
        dtoUserIU.setLastName("User");
        dtoUserIU.setEmail("test@example.com");
        dtoUserIU.setPassword("password123");
        dtoUserIU.setNationalIdentity(12345678);
        dtoUserIU.setDateOfBirth("2000-01-01");
    }

    @Test
    void testSaveUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        DtoUser result = userService.saveUser(dtoUserIU);

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        assertEquals("Test", result.getFirstName());
        assertEquals("User", result.getLastName());
        assertEquals("test@example.com", result.getEmail());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserAll() {
        List<User> users = new ArrayList<>();
        users.add(user);

        when(userRepository.findAll()).thenReturn(users);

        List<DtoUser> result = userService.getUserAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("testuser", result.get(0).getUsername());
        assertEquals("Test", result.get(0).getFirstName());
        assertEquals("User", result.get(0).getLastName());
        assertEquals("test@example.com", result.get(0).getEmail());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        DtoUser result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        assertEquals("Test", result.getFirstName());
        assertEquals("User", result.getLastName());
        assertEquals("test@example.com", result.getEmail());

        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGetUserByIdNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        DtoUser result = userService.getUserById(1L);

        assertNull(result);
        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    void testUpdateUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        DtoUser result = userService.updateUser(1L, dtoUserIU);

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        assertEquals("Test", result.getFirstName());
        assertEquals("User", result.getLastName());
        assertEquals("test@example.com", result.getEmail());

        verify(userRepository, times(1)).findById(anyLong());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        DtoUser result = userService.updateUser(1L, dtoUserIU);

        assertNull(result);
        verify(userRepository, times(1)).findById(anyLong());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testDeleteUser() {
        when(userRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(userRepository).deleteById(anyLong());

        boolean result = userService.deleteUser(1L);

        assertTrue(result);
        verify(userRepository, times(1)).existsById(anyLong());
        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testDeleteUserNotFound() {
        when(userRepository.existsById(anyLong())).thenReturn(false);

        boolean result = userService.deleteUser(1L);

        assertFalse(result);
        verify(userRepository, times(1)).existsById(anyLong());
        verify(userRepository, never()).deleteById(anyLong());
    }
} 