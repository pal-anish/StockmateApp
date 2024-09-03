package com.cts.AuthenticationService.config;

import com.cts.AuthenticationService.model.UserCredential;

import java.util.Map;

public interface JWTTokenGenerator {
    Map<String, String> generateToken(UserCredential userCredential);
}
