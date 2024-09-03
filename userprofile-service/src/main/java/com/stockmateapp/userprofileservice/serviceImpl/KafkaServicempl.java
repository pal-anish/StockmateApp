package com.stockmateapp.userprofileservice.serviceImpl;


import com.google.gson.Gson;
import com.stockmateapp.userprofileservice.model.User;
import com.stockmateapp.userprofileservice.service.KafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaServicempl implements KafkaService {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private Gson gson;

    private Logger logger= LoggerFactory.getLogger(KafkaServicempl.class);

    /*
    Method to trigger kafka send
     */
    @Override
    public void sendPayload(String topic, User payload) {
        String jsonPayload = gson.toJson(payload);
        logger.info("Sending payload to kafka topic: "+topic+" payload: "+jsonPayload);
        kafkaTemplate.send(topic,jsonPayload);
        logger.info("Payload sent to kafka topic: "+topic);
        logger.info("Payload: "+jsonPayload);
    }
}
