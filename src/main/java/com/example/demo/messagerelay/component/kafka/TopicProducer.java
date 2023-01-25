package com.example.demo.messagerelay.component.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
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
    public void send(String message, Long eventId, Long id, String action){
        log.info("Payload message: {}", message);
        Message<String> m = MessageBuilder
        .withPayload(message)
        .setHeader(KafkaHeaders.TOPIC, topicName)
        .setHeader("eventId",eventId.toString())
        .setHeader("id",id.toString())
        .setHeader("action",action)
        .setHeader("X-Custom-Header", "Sending Custom Header with Spring Kafka")
        .build();
        kafkaTemplate.send(m);
    }

}
