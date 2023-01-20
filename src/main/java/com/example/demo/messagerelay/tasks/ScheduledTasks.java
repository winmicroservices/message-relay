package com.example.demo.messagerelay.tasks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.messagerelay.component.kafka.TopicProducer;
import com.example.demo.messagerelay.entity.Event;
import com.example.demo.messagerelay.repository.EventRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduledTasks {

	@Autowired
	EventRepository eventRepository;

	@Autowired
	TopicProducer topicProducer;

	/**
	 * Send new events to Kafka every 5 seconds.
	 * Mark the events as being sent in the database.
	 */
	@Scheduled(fixedRate = 5000)
	public void sentEventsToKafka() {

		log.info("Sending unsent events...");
		List<Event> events = eventRepository.findUnsentEvents();
		for(Event e : events) {
			topicProducer.send(e.getPayload());
			e.setMessageSent(true);
			eventRepository.save(e);
		}
	}

	/**
	 * Delete the sent messages from the local database.
	 * Run it daily.
	 */
	@Scheduled(fixedRate = 86000)
	public void deleteSentMessages() {
		int deletedItems = eventRepository.deleteSent();
		log.info("Deleted {} items",deletedItems);
	}
}