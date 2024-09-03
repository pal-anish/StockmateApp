package com.cts.AuthenticationService.serviceImpl;

import com.cts.AuthenticationService.model.UserCredential;
import com.cts.AuthenticationService.exception.UserNotFoundException;
import com.cts.AuthenticationService.repository.UserCredentialRepository;
import com.cts.AuthenticationService.service.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialServiceImpl implements UserCredentialService {
    UserCredentialRepository userCredentialRepository;

    @Autowired
    public UserCredentialServiceImpl(UserCredentialRepository userCredentialRepository) {
        super();
        this.userCredentialRepository = userCredentialRepository;
    }

    @Override
    public UserCredential authenticateUser(String username, String password) throws UserNotFoundException {
        UserCredential authUser = userCredentialRepository.findByUsernameAndPassword(username, password);
        if (authUser == null) {
            throw new UserNotFoundException("User not found");
        }

        return authUser;
    }

}
