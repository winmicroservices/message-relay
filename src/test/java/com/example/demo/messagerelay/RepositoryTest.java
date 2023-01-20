package com.example.demo.messagerelay;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.messagerelay.repository.EventRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@Slf4j
public class RepositoryTest {

    @Configuration
    @EnableAutoConfiguration
    static class Config {}

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void testDelete() throws Exception {
        int deletedItems = eventRepository.deleteSent();
        log.info("{} events deleted", deletedItems);
        assertEquals(0, deletedItems);
    }
    
}
