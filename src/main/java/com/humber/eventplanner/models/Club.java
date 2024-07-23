package com.humber.eventplanner.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Collection;

@Data
@NoArgsConstructor
@Document(collection = "clubs")
@AllArgsConstructor
public class Club {
    @MongoId
    private Integer id;
    private String name;
    private String description;
}
