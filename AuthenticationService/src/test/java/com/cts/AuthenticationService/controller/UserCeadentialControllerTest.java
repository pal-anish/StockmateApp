package com.cts.AuthenticationService.controller;

import com.cts.AuthenticationService.config.JWTTokenGenerator;
import com.cts.AuthenticationService.model.UserCredential;
import com.cts.AuthenticationService.exception.UserNotFoundException;
import com.cts.AuthenticationService.service.UserCredentialService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserCeadentialControllerTest {

    @InjectMocks
    private UserCeadentialController userCeadentialController;

    @Mock
    private UserCredentialService userCredentialService;

    @Mock
    private JWTTokenGenerator jwtTokenGenerator;

    @Test
    public void testLoginUserSuccess() throws UserNotFoundException {
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername("testuser");
        userCredential.setPassword("testpassword");

        UserCredential userDetails = new UserCredential();
        userDetails.setUsername("testuser");
        userDetails.setPassword("testpassword");

        when(userCredentialService.authenticateUser(userCredential.getUsername(), userCredential.getPassword())).thenReturn(userDetails);

        ResponseEntity<?> responseEntity = userCeadentialController.loginUser(userCredential);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void testLoginUserUsernameNull() throws UserNotFoundException {
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername(null);
        userCredential.setPassword("testpassword");

        ResponseEntity<?> responseEntity = userCeadentialController.loginUser(userCredential);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Username or Password is null", responseEntity.getBody());
    }

    @Test
    public void testLoginUserPasswordNull() throws UserNotFoundException {
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername("testuser");
        userCredential.setPassword(null);


        ResponseEntity<?> responseEntity = userCeadentialController.loginUser(userCredential);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Username or Password is null", responseEntity.getBody());
    }

    @Test
    public void testLoginUserUserNotFound() throws UserNotFoundException {
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername("testuser");
        userCredential.setPassword("testpassword");

        when(userCredentialService.authenticateUser(userCredential.getUsername(), userCredential.getPassword())).thenReturn(null);

        ResponseEntity<?> responseEntity = userCeadentialController.loginUser(userCredential);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("User not found", responseEntity.getBody());
    }

    @Test
    public void testLoginUserPasswordIncorrect() throws UserNotFoundException {
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername("testuser");
        userCredential.setPassword("wrongpassword");

        UserCredential userDetails = new UserCredential();
        userDetails.setUsername("testuser");
        userDetails.setPassword("testpassword");

        when(userCredentialService.authenticateUser(userCredential.getUsername(), userCredential.getPassword())).thenReturn(userDetails);

        ResponseEntity<?> responseEntity = userCeadentialController.loginUser(userCredential);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Password is incorrect", responseEntity.getBody());
    }
}