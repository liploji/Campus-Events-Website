package com.humber.eventplanner.repositories;

import com.humber.eventplanner.models.EventRegistration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRegistrationRepository extends MongoRepository<EventRegistration, String> {
    boolean existsByUserIdAndEventId(String userId, String eventId);
}