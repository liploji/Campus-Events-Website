package com.humber.eventplanner.repositories;

import com.humber.eventplanner.models.EventRegistration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRegistrationRepository extends MongoRepository<EventRegistration, Integer> {
    boolean existsByUserIdAndEventId(Integer userId, Integer eventId);
}