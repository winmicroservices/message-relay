package com.example.demo.messagerelay.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.demo.messagerelay.entity.Event;

public class EventRepsitoryImpl implements EventRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private String FIND_UNSENT_EVENTS = "select b from Event b where b.messageSent = null";

    private String DELETE_SENT = "delete from Event b where b.messageSent != null";

    @Override
    public List<Event> findUnsentEvents() {
        return entityManager.createQuery(FIND_UNSENT_EVENTS, Event.class).setMaxResults(100).getResultList();
    }

    @Override
    public int deleteSent() {
        return entityManager.createQuery(DELETE_SENT).executeUpdate();
    }

    

}
