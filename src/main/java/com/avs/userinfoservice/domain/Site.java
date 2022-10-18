package com.avs.userinfoservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Sites")
@Data
@AllArgsConstructor
public class Site {
    @Id
    @BsonId
    private String id;

    @Field(name = "CreatedBy")
    private User createdBy;
}