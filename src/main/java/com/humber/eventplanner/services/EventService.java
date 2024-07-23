package com.humber.eventplanner.services;

import com.humber.eventplanner.models.Event;
import com.humber.eventplanner.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(int id) {
        return eventRepository.findById(id).orElse(null);
    }

    public void createEvent(Event event) {
        if(eventRepository.findEventByTitle(event.getTitle()) != null) {
            throw new IllegalStateException("Club with name "+ event.getTitle()+" already exists");
        }
        eventRepository.save(event);
    }

    public void updateEvent(int id, Event event) {
        if (eventRepository.existsById(id)){
            event.setId(id);
            eventRepository.save(event);
        } else {
            throw new IllegalStateException("Event with id " + id + " does not exist! Cannot update.");
        }
    }

    public void deleteEvent(int id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
        } else {
            throw new IllegalStateException("Event with id " + id + " does not exist! Cannot delete.");
        }
    }
}