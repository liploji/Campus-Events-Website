package com.humber.eventplanner.models;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Document(collection = "events")
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @MongoId
    private Integer id;
    private String title;
    private String description;
    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime endTime;
    private String location;
    private Integer userId;
}