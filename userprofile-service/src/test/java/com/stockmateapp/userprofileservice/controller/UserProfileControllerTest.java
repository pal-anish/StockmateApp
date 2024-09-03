package com.stockmateapp.userprofileservice.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.stockmateapp.userprofileservice.exceptions.UserAlreadyExistsException;
import com.stockmateapp.userprofileservice.exceptions.UserNotFoundException;
import com.stockmateapp.userprofileservice.model.User;
import com.stockmateapp.userprofileservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) public class UserProfileControllerTest {
    @InjectMocks
    private UserProfileController userProfileController;

    @Mock
    private UserService userService;

    @Test
    public void registerUser_shouldReturn_CreatedUser() throws UserAlreadyExistsException {
        User newUser = new User();
        newUser.setUsername("test");
        newUser.setPassword("password");
        newUser.setEmail("test@gmail.com");

        User savedUser = new User();
        savedUser.setUsername("test");
        savedUser.setPassword("password");
        savedUser.setEmail("test@gmail.com");

        when(userService.registerUser(newUser)).thenReturn(savedUser);

        ResponseEntity<?> response = userProfileController.registerUser(newUser);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedUser, response.getBody());
    }

    @Test
    public void registerUser_shouldReturn_Conflict() throws UserAlreadyExistsException {
        User newUser = new User();
        newUser.setUsername("test");
        newUser.setPassword("password");
        newUser.setEmail("test@gmail.com");

        when(userService.registerUser(newUser)).thenThrow(new UserAlreadyExistsException("User already exists"));

        ResponseEntity<?> response = userProfileController.registerUser(newUser);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("User already exists", response.getBody());
    }

    @Test
    public void getAllUsers_shouldReturn_ListOfUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());

        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<User>> response = userProfileController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    public void getUserById_shouldReturn_User() throws UserNotFoundException {
        User user = new User();
        user.setUsername("test");
        user.setPassword("password");
        user.setEmail("test@gmail.com");

        when(userService.getUserById(1L)).thenReturn(user);

        ResponseEntity<?> response = userProfileController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void getUserById_shouldReturn_NotFound() throws UserNotFoundException {
        when(userService.getUserById(1L)).thenThrow(new UserNotFoundException("User not found"));

        ResponseEntity<?> response = userProfileController.getUserById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }

    @Test
    public void getUserByUsername_shouldReturn_User() throws UserNotFoundException {
        User user = new User();
        user.setUsername("test");
        user.setPassword("password");
        user.setEmail("test@gmail.com");

        when(userService.getUserByUsername("test")).thenReturn(user);

        ResponseEntity<?> response = userProfileController.getUserByUsername("test");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void getUserByUsername_shouldReturn_NotFound() throws UserNotFoundException {
        when(userService.getUserByUsername("test")).thenThrow(new UserNotFoundException("User not found"));

        ResponseEntity<?> response = userProfileController.getUserByUsername("test");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }

    @Test
    public void getUserByEmail_shouldReturn_User() throws UserNotFoundException {
        User user = new User();
        user.setUsername("test");
        user.setPassword("password");
        user.setEmail("test@gmail.com");

        when(userService.getUserByEmail("test@gmail.com")).thenReturn(user);

        ResponseEntity<?> response = userProfileController.getUserByEmail("test@gmail.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void getUserByEmail_shouldReturn_NotFound() throws UserNotFoundException {
        when(userService.getUserByEmail("test@gmail.com")).thenThrow(new UserNotFoundException("User not found"));

        ResponseEntity<?> response = userProfileController.getUserByEmail("test@gmail.com");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }

    @Test
    public void updateUser_shouldReturn_UpdatedUser() throws UserNotFoundException {
        User user = new User();
        user.setUsername("test");
        user.setPassword("password");
        user.setEmail("test@gmail.com");

        User updatedUser = new User();
        updatedUser.setUsername("test");
        updatedUser.setPassword("new_password");
        updatedUser.setEmail("test@gmail.com");

        when(userService.updateUser(user, "test")).thenReturn(updatedUser);

        ResponseEntity<?> response = userProfileController.updateUser(user, "test");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());
    }

    @Test
    public void updateUser_shouldReturn_NotFound() throws UserNotFoundException {
        User user = new User();
        user.setUsername("test");
        user.setPassword("password");
        user.setEmail("test@gmail.com");

        when(userService.updateUser(user, "test")).thenThrow(new UserNotFoundException("User not found"));

        ResponseEntity<?> response = userProfileController.updateUser(user, "test");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }


    @Test
    public void deleteUser_shouldReturn_NotFound() throws UserNotFoundException {
        doThrow(new UserNotFoundException("User not found")).when(userService).deleteUser("test");

        ResponseEntity<?> response = userProfileController.deleteUser("test");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }
}
