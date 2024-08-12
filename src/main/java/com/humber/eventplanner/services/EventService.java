package com.humber.eventplanner.services;

import com.humber.eventplanner.models.Event;
import com.humber.eventplanner.models.EventRegistration;
import com.humber.eventplanner.models.User;
import com.humber.eventplanner.repositories.EventRepository;
import com.humber.eventplanner.repositories.EventRegistrationRepository;
import com.humber.eventplanner.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventRegistrationRepository eventRegistrationRepository;
    private final UserRepository userRepository;

    @Autowired
    public EventService(EventRepository eventRepository, EventRegistrationRepository eventRegistrationRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.eventRegistrationRepository = eventRegistrationRepository;
        this.userRepository = userRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(String id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Event with id " + id + " not found"));
    }

    public void createEvent(Event event) {
        if(eventRepository.findEventByTitle(event.getTitle()) != null) {
            throw new IllegalStateException("Event with title "+ event.getTitle()+" already exists");
        }
        eventRepository.save(event);
    }

    public void updateEvent(String id, Event event) {
        if (eventRepository.existsById(id)){
            event.setId(id);
            eventRepository.save(event);
        } else {
            throw new IllegalStateException("Event with id " + id + " does not exist! Cannot update.");
        }
    }

    public void deleteEvent(String id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
        } else {
            throw new IllegalStateException("Event with id " + id + " does not exist! Cannot delete.");
        }
    }

    public void addUserToEvent(String userId, String eventId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalStateException("Event not found"));

        if (!"student".equalsIgnoreCase(user.getRole())) {
            throw new IllegalStateException("Only students can join an event");
        }

        if (eventRegistrationRepository.existsByUserIdAndEventId(userId, eventId)) {
            throw new IllegalStateException("User has already joined this event");
        }

        EventRegistration registration = new EventRegistration();
        registration.setUserId(userId);
        registration.setEventId(eventId);
        registration.setRegistrationTime(LocalDateTime.now());

        EventRegistration savedRegistration = eventRegistrationRepository.save(registration);

        if (event.getRegistrationIds() == null) {
            event.setRegistrationIds(new ArrayList<>());
        }
        event.getRegistrationIds().add(savedRegistration.getId());
        eventRepository.save(event);
    }
}