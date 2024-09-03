package com.cts.AuthenticationService.configImpl;

import com.cts.AuthenticationService.config.JWTTokenGenerator;
import com.cts.AuthenticationService.model.UserCredential;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Date;

@Service
public class JWTTokenGeneratorImpl implements JWTTokenGenerator {

    @Value("${jwt.secret}")
    private String secret;

//    @Value("${jwttoken.message}")
//    private String message; //Login Successful...

    public Map<String, String> generateToken(UserCredential userCredential) {
        Map<String, String> jwtTokenMap = new HashMap<>();
        Map<String, Object> userData = new HashMap<>();
        userData.put("username", userCredential.getUsername());
        userData.put("password", userCredential.getPassword());
        String jwtToken = Jwts.builder()
                .setClaims(userData)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3000000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();


        //jwtTokenMap.put("message", message);
        jwtTokenMap.put("token", jwtToken);

        return jwtTokenMap;
    }
}
