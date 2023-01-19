package com.example.demo.messagerelay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.messagerelay.entity.Event;

/**
* Dao for the Event table.
*/
public interface EventRepository extends JpaRepository<Event, Long> {

    // TODO: Limit this to 100 items
    String FIND_UNSENT_EVENTS = "select b from Event b where b.messageSent = null";

    @Query(FIND_UNSENT_EVENTS)
    List<Event> findUnsentEvents();
}