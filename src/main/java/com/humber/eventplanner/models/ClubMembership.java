package com.humber.eventplanner.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "club_memberships")
public class ClubMembership {

    @MongoId
    private Integer membershipId;
    private Integer userId;
    private Integer clubId;

}
