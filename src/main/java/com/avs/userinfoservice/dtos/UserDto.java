package com.avs.userinfoservice.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.ZonedDateTime;

public record UserDto(
        String Id,
        String Name,
        String Email,
        Integer status,
        @JsonFormat(pattern = "MM/dd/YYYY HH:mm Z") ZonedDateTime DateJoined
) {}
