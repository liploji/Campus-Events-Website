package com.humber.eventplanner.repositories;

import com.humber.eventplanner.models.Club;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends MongoRepository<Club, Integer> {

    Club findClubByName(String name);
}
