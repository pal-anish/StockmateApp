package com.stockmateapp.userprofileservice.service;

import com.stockmateapp.userprofileservice.exceptions.UserAlreadyExistsException;
import com.stockmateapp.userprofileservice.model.User;

import java.util.List;


public interface UserService {

    List<User> getAllUsers();

    User registerUser(User newUser) throws UserAlreadyExistsException;

    User getUserById(Long userId);

    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User updateUser(User user,String username);
    String deleteUser(String username);

}
