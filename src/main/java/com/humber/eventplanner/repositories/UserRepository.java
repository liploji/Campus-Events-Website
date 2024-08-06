package com.humber.eventplanner.repositories;

import com.humber.eventplanner.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
    User findUserByUsername(String username);

}
