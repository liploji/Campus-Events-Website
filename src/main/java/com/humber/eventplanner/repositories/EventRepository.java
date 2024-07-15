package com.humber.eventplanner.repositories;

import com.humber.eventplanner.models.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
}