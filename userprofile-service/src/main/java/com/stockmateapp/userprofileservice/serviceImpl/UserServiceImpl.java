package com.stockmateapp.userprofileservice.serviceImpl;

import com.stockmateapp.userprofileservice.exceptions.UserAlreadyExistsException;
import com.stockmateapp.userprofileservice.exceptions.UserNotFoundException;
import com.stockmateapp.userprofileservice.model.User;
import com.stockmateapp.userprofileservice.repository.UserProfileRepository;
import com.stockmateapp.userprofileservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserProfileRepository userProfileRepository;

    @Autowired
    KafkaServicempl kafkaServicempl;

    @Autowired
    public UserServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public User registerUser(User newUser) throws UserAlreadyExistsException{
        String userUsername = newUser.getUsername();
        String userEmail = newUser.getEmail();
        User existingUsername = userProfileRepository.findByUsername(userUsername);
        User existingEmail = userProfileRepository.findByEmail(userEmail);
        if (existingUsername != null){
            throw new UserAlreadyExistsException("User with Username: " + userUsername + " already exists");
        } else if (existingEmail != null){
            throw new UserAlreadyExistsException("User with Email: " + userEmail + " already exists");
        }else {
            userProfileRepository.save(newUser);
            kafkaServicempl.sendPayload("stockmatetopic",newUser);
            return newUser;
        }
    }
    @Override
    public List<User> getAllUsers(){
        return userProfileRepository.findAll();
    }

    @Override
    public User getUserById(Long  userId) throws UserNotFoundException{
        Optional<User> user = userProfileRepository.findById(userId);
        if (user.isPresent()){
            return user.get();
        }else {
            throw new UserNotFoundException("User not found with id:" + userId);
        }
    }
    @Override
    public User getUserByUsername(String username) throws UserNotFoundException{
        User user = userProfileRepository.findByUsername(username);
        if (user != null){
            return user;
        }else {
            throw new UserNotFoundException("User not found with username:" + username);
        }
    }
    @Override
    public User getUserByEmail(String email) throws UserNotFoundException{
        User user = userProfileRepository.findByEmail(email);
        if (user != null){
            return user;
        }else {
            throw new UserNotFoundException("User not found with email:" + email);
        }
    }

    @Override
    public User updateUser(User user, String username) throws UserNotFoundException{
        User existingUser = userProfileRepository.findByUsername(username);
        if (existingUser != null) {
            User newUser = existingUser;
            newUser.setFirstname(user.getFirstname());
            newUser.setLastname(user.getLastname());
            newUser.setEmail(user.getEmail());

            kafkaServicempl.sendPayload("stockmatetopic",newUser);

            return userProfileRepository.save(newUser);
            //return "User Updated";
        }else {
            throw new UserNotFoundException("User not found with username:" + user.getUsername());

        }
    }

    @Override
    public String deleteUser(String username) {
        User user = userProfileRepository.findByUsername(username);
        if (user != null){
            userProfileRepository.deleteById(user.getUserId());
            return "User Deleted";
        }else {
            throw new UserNotFoundException("User not found with username:" + username);
        }
    }

}
