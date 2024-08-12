package com.humber.eventplanner.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@Document(collection = "users")
@AllArgsConstructor
public class User {

    @Id
    private String id;
    private String first_name;
    private String last_name;
    private String password;
    private String username;
    private String email;
    private String role;
}
