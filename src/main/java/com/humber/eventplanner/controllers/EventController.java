package com.humber.eventplanner.controllers;

import com.humber.eventplanner.models.Event;
import com.humber.eventplanner.services.ClubService;
import com.humber.eventplanner.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Event> getEventById(@PathVariable int id) {
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
    public ResponseEntity<String> updateEvent(@PathVariable int id, @RequestBody Event event) {

        try {
            eventService.updateEvent(id, event);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error: "+ e.getMessage());
        }

        return ResponseEntity.ok("Event updated successfully");
    }

    //delete event
    @DeleteMapping("/events/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable int id) {
        try {
            eventService.deleteEvent(id);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error: "+ e.getMessage());
        }

        return ResponseEntity.ok("Event deleted successfully");
    }

}