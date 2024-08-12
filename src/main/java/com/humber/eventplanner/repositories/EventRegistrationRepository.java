package com.humber.eventplanner.repositories;

import com.humber.eventplanner.models.EventRegistration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRegistrationRepository extends MongoRepository<EventRegistration, Integer> {
    boolean existsByUserIdAndEventId(String userId, String eventId);

    List<EventRegistration> findByUserId(String userId);
}