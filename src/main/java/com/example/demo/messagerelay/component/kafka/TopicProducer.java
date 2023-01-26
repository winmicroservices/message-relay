package com.example.demo.messagerelay.component.kafka;

import org.json.JSONObject;
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
     * @param jsonMessage The Customer object in json format.
     */
    public void send(String jsonMessage, Long eventId, Long id, String action) {
        log.info("Payload message: {}", jsonMessage);
        JSONObject jo = new JSONObject(jsonMessage);
        jo.put("action", action);
        jo.put("id",id.toString());
        jo.put("eventId", eventId.toString());
        Message<String> m = MessageBuilder
        .withPayload(jo.toString())
        .setHeader(KafkaHeaders.TOPIC, this.topicName)
        // .setHeader("X-Custom-Header", "Sending Custom Header with Spring Kafka")
        .build();
        kafkaTemplate.send(m);
    }

}
