package com.humber.eventplanner.controllers;

import com.humber.eventplanner.models.Event;
import com.humber.eventplanner.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    //get event by id
    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    //add event
    @PostMapping("/events")
    public ResponseEntity<String> addEvent(@RequestBody Event event) {

        try {
            eventService.createEvent(event);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error: "+ e.getMessage());
        }

        return ResponseEntity.ok("Event added successfully");
    }
    //update event
    @PutMapping("/events/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable String id, @RequestBody Event event) {

        try {
            eventService.updateEvent(id, event);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error: "+ e.getMessage());
        }

        return ResponseEntity.ok("Event updated successfully");
    }

    //delete event
    @DeleteMapping("/events/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable String id) {
        try {
            eventService.deleteEvent(id);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error: "+ e.getMessage());
        }

        return ResponseEntity.ok("Event deleted successfully");
    }
    // add user to an event
    @PostMapping("/events/{eventId}/join")
    public ResponseEntity<String> addUserToEvent(@PathVariable String eventId, @RequestParam String userId) {
        try {
            eventService.addUserToEvent(userId, eventId);
            return ResponseEntity.ok("User successfully added to the event");
        } catch (IllegalStateException e) {
            // logs the error if user cant register for an event
            System.err.println("Error adding user to event: " + e.getMessage());
            e.printStackTrace();  // prints the stack trace to your server logs
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // shows any unexpected errors
            System.err.println("Unexpected error adding user to event: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

}