package com.humber.eventplanner.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "event_registrations")
public class EventRegistration {

    @MongoId
    private Integer registrationId;
    private Integer userId;
    private Integer eventId;

}
