package com.humber.eventplanner.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@Document(collection = "admins")
@AllArgsConstructor
public class User {

    @MongoId
    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private String role;
}
