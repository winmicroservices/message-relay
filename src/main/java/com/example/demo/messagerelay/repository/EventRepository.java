package com.example.demo.messagerelay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.messagerelay.entity.Event;


/**
* Dao for the Event table.
*/
public interface EventRepository extends JpaRepository<Event,Long>, EventRepositoryCustom {


}