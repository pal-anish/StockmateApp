package com.cts.AuthenticationService.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.cts.AuthenticationService.model.UserCredential;
import com.cts.AuthenticationService.serviceImpl.UserCredentialServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserCredentialRepositoryTest {

    @Mock
    private UserCredentialRepository userRepository;

    @InjectMocks
    private UserCredentialServiceImpl userService;

    @Test
    void testFindByUsernameAndPassword() {
        // Create a sample UserCredential
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername("testUser");
        userCredential.setPassword("testPassword");
        // Mock the behavior of the userRepository.findByUsernameAndPassword method
        when(userRepository.findByUsernameAndPassword(eq("testUser"), eq("testPassword")))
                .thenReturn(userCredential);

        // Call the method you want to test from your service
        UserCredential result = userService.authenticateUser("testUser", "testPassword");

        // Verify the result
        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        assertEquals("testPassword", result.getPassword());

        // Verify that the userRepository.findByUsernameAndPassword method was called with the correct parameters
        verify(userRepository, times(1)).findByUsernameAndPassword(eq("testUser"), eq("testPassword"));
    }
}