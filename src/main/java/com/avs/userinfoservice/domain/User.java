package com.avs.userinfoservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.ZonedDateTime;
import java.util.Date;

@Document(collection = "Users")
@Data
@AllArgsConstructor
public class User {
    @Id
    @BsonId
    private String id;

    @Field("Name")
    private String name;

    @Field("Email")
    private String email;

    @Field("Status")
    private Integer status;

    @Field(value = "DateJoined")
    private Date dateJoined;
}