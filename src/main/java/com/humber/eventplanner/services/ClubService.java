package com.humber.eventplanner.services;

import com.humber.eventplanner.models.Club;
import com.humber.eventplanner.models.Event;
import com.humber.eventplanner.repositories.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {

    private final ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public Club getClubById(int id) {
        return clubRepository.findById(id).orElse(null);
    }

    public void createClub(Club club) {
        if(clubRepository.findClubByName(club.getName()) != null) {
            throw new IllegalStateException("Club with name "+ club.getName()+" already exists");
        }
        clubRepository.save(club);
    }

    public void updateClub(int id, Club club) {
        if (clubRepository.existsById(id)){
            club.setId(id);
            clubRepository.save(club);
        } else {
            throw new IllegalStateException("Club with id " + id + " does not exist! Cannot update.");
        }
    }

    public void deleteClub(int id) {
        if (clubRepository.existsById(id)) {
            clubRepository.deleteById(id);
        } else {
            throw new IllegalStateException("Club with id " + id + " does not exist! Cannot delete.");
        }
    }

}
