package com.stockmateapp.userprofileservice.service;

import com.stockmateapp.userprofileservice.model.User;

public interface KafkaService {
    /*
        Method to trigger kafka send
         */
    void sendPayload(String topic, User payload);
}
