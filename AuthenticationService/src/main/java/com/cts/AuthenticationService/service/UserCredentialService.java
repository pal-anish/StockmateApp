package com.cts.AuthenticationService.service;

import com.cts.AuthenticationService.exception.UserAlreadyExistsException;
import com.cts.AuthenticationService.model.UserCredential;
import com.cts.AuthenticationService.exception.UserNotFoundException;
import org.apache.kafka.common.protocol.types.Field;

public interface UserCredentialService {

    UserCredential authenticateUser(String username, String password) throws UserNotFoundException;

    //UserCredential saveUserDetails(UserCredential newUserCredential) throws UserAlreadyExistsException;
}
