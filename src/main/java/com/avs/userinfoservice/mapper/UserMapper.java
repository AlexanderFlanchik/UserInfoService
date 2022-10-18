package com.avs.userinfoservice.mapper;

import com.avs.userinfoservice.domain.User;
import com.avs.userinfoservice.dtos.UserDto;
import org.springframework.stereotype.Component;
import java.time.ZoneId;

@Component
public class UserMapper {
    private final ZoneId utcZoneId = ZoneId.of("UTC").normalized();

    public UserDto Map(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getStatus(),
                user.getDateJoined()
                        .toInstant()
                        .atZone(utcZoneId)
            );
    }
}