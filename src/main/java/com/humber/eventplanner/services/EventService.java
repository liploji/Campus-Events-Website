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

    public Optional<Event> getEventById(String id) {
        return eventRepository.findById(id);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Optional<Event> updateEvent(String id, Event eventDetails) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            Event existingEvent = event.get();
            existingEvent.setTitle(eventDetails.getTitle());
            existingEvent.setDescription(eventDetails.getDescription());
            existingEvent.setStartTime(eventDetails.getStartTime());
            existingEvent.setEndTime(eventDetails.getEndTime());
            existingEvent.setLocation(eventDetails.getLocation());
//            existingEvent.setOrganizerId(eventDetails.getOrganizerId());
            return Optional.of(eventRepository.save(existingEvent));
        } else {
            return Optional.empty();
        }
    }

    public void deleteEvent(String id) {
        eventRepository.deleteById(id);
    }
}