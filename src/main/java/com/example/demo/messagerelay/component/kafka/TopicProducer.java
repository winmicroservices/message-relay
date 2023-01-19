package com.example.demo.messagerelay.component.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
* Kafka topic service for the customer.
*/
@Component
@Slf4j
public class TopicProducer {

    @Value("${topic.name.producer}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Sends a create order message to a Kafka topic that tracks new customers.
     * @param message The Customer object in json format.
     */
    public void send(String message){
        log.info("Payload message: {}", message);
        kafkaTemplate.send(topicName, message);
    }

}
