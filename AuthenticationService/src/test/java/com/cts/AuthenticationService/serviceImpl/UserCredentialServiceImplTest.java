package com.cts.AuthenticationService.serviceImpl;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.cts.AuthenticationService.exception.UserNotFoundException;
import com.cts.AuthenticationService.model.UserCredential;
import com.cts.AuthenticationService.repository.UserCredentialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserCredentialServiceImplTest {

    @Mock
    private UserCredentialRepository userCredentialRepository;

    @InjectMocks
    private UserCredentialServiceImpl userCredentialService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthenticateUser_SuccessfulAuthentication() throws UserNotFoundException {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        UserCredential mockUser = new UserCredential(username, password);
        when(userCredentialRepository.findByUsernameAndPassword(username, password)).thenReturn(mockUser);

        // Act
        UserCredential authenticatedUser = userCredentialService.authenticateUser(username, password);

        // Assert
        assertNotNull(authenticatedUser);
        assertEquals(username, authenticatedUser.getUsername());
        assertEquals(password, authenticatedUser.getPassword());
        verify(userCredentialRepository, times(1)).findByUsernameAndPassword(username, password);
    }

    @Test
    void testAuthenticateUser_UserNotFound() {
        // Arrange
        String username = "nonexistentUser";
        String password = "testPassword";
        when(userCredentialRepository.findByUsernameAndPassword(username, password)).thenReturn(null);

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            userCredentialService.authenticateUser(username, password);
        });

        verify(userCredentialRepository, times(1)).findByUsernameAndPassword(username, password);
    }
}
