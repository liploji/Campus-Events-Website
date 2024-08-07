package com.humber.eventplanner.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "event_registrations")
public class EventRegistration {

    @Id
    private String id;  // so MongoDB can generate id
    private Integer userId;
    private Integer eventId;
    private LocalDateTime registrationTime;
}