package com.example.demo.messagerelay.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.demo.messagerelay.entity.Event;


/**
* Dao for the Event table.
*/
@Repository
public class EventRepository {

    @PersistenceContext
    private EntityManager entityManager;

    String FIND_UNSENT_EVENTS = "select b from Event b where b.messageSent = null";

    public List<Event> findUnsentEvents() {
        return entityManager.createQuery(FIND_UNSENT_EVENTS, Event.class).setMaxResults(100).getResultList();
    }

    public void save(Event event) {
        entityManager.merge(event);
    }

}