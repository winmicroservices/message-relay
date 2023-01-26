package com.example.demo.messagerelay.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
* This entity is used to collect the changes to the customer.  
* The records will be streamed to an event queue.
*/
@Table(name = "event")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Event {

    /**
    * Primary key.
    */
    @Id
    @Column(name="event_id")
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private long eventId;

    public long getEventId() {
        return this.eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isMessageSent() {
        return this.messageSent;
    }

    /**
    * Used to track the state of the customer.
    */
    @Column(name = "action")
    private String state;

    @Column(name = "id")
    private Long id;

    @Column(name="msg_sent")
    private Boolean messageSent;

    private String payload;

    
    public Event() {
        
    }
    
    public Event(String state, Long id, String payload) {
        this.state = state;
        this.id = id;
        this.payload = payload;
    }
    
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }

    public Boolean getMessageSent() {
        return this.messageSent;
    }
    
    public void setMessageSent(Boolean messageSent) {
        this.messageSent = messageSent;
    }
    
    public String getPayload() {
        return this.payload;
    }
    
    public void setPayload(String payload) {
        this.payload = payload;
    }
}
