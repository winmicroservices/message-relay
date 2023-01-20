package com.example.demo.messagerelay.repository;

import java.util.List;

import com.example.demo.messagerelay.entity.Event;

public interface EventRepositoryCustom {

    public List<Event> findUnsentEvents();

    public int deleteSent();
}
