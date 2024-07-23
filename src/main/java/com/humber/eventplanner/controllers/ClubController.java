package com.humber.eventplanner.controllers;

import com.humber.eventplanner.models.Club;
import com.humber.eventplanner.services.ClubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public ResponseEntity<List<Club>> getAllClubs() {
        return ResponseEntity.ok(clubService.getAllClubs());
    }

    @GetMapping("/clubs/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable int id) {
        return ResponseEntity.ok(clubService.getClubById(id));
    }

    @PostMapping("/clubs")
    public ResponseEntity<String> addClub(@RequestBody Club club) {
        try {
            clubService.createClub(club);
            return ResponseEntity.ok("Club added successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/clubs/{id}")
    public ResponseEntity<String> updateClub(@PathVariable int id, @RequestBody Club club) {
        try {
            clubService.updateClub(id, club);
            return ResponseEntity.ok("Club updated successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/clubs/{id}")
    public ResponseEntity<String> deleteClub(@PathVariable int id) {
        try {
            clubService.deleteClub(id);
            return ResponseEntity.ok("Club deleted successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}