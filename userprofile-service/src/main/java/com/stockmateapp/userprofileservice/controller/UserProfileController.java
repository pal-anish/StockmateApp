package com.stockmateapp.userprofileservice.controller;

import com.stockmateapp.userprofileservice.exceptions.UserAlreadyExistsException;
import com.stockmateapp.userprofileservice.exceptions.UserNotFoundException;
import com.stockmateapp.userprofileservice.model.User;
import com.stockmateapp.userprofileservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/users")
public class UserProfileController {
    @Autowired
    private final UserService userService;

    @Autowired
    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User newUser) throws UserAlreadyExistsException {
        try{
            User savedUser = userService.registerUser(newUser);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }catch (UserAlreadyExistsException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        try {
            User user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username) throws UserNotFoundException {
        try {
            User user = userService.getUserByUsername(username);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) throws UserNotFoundException {
        try {
            User user = userService.getUserByEmail(email);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateUser/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable("username") String username)
            throws UserNotFoundException {
        try {
            User updatedUser = userService.updateUser(user,username);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteUser/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username) throws UserNotFoundException {
        try {
            userService.deleteUser(username);
            return new ResponseEntity<>("User Deleted",HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

}
