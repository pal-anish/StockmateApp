package com.cts.AuthenticationService.config;

import com.cts.AuthenticationService.model.UserCredential;
import com.cts.AuthenticationService.repository.UserCredentialRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Map;


@Configuration
public class UserRegistrationConsumer {


    @Autowired
    private UserCredentialRepository userCredentialRepository;

    private UserCredential userCredential;

    public UserRegistrationConsumer(UserCredentialRepository userCredentialRepository){

        this.userCredentialRepository = userCredentialRepository;
        this.userCredential = new UserCredential();
    }

    @KafkaListener(topics = "stockmatetopic", groupId = "group_id")
    public void listenUserCredential(String value) throws JsonProcessingException {
        //System.out.println(value);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> responseMap = objectMapper.readValue(value, Map.class);
        //System.out.println(responseMap);

//        if((responseMap.get("userId")).equals(userCredential.getUserid()))
//        {
//            userCredential.setUsername(responseMap.get("username"));
//            userCredential.setPassword(responseMap.get("password"));
//        }
//        else {
//            userCredential.setUserid(responseMap.get("userId"));
//            userCredential.setUsername(responseMap.get("username"));
//            userCredential.setPassword(responseMap.get("password"));
//        }
        userCredential.setUsername(responseMap.get("username"));
        userCredential.setPassword(responseMap.get("password"));
        userCredentialRepository.save(userCredential);

    }


}
