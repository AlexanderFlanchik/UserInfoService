package com.avs.userinfoservice.services;

import com.avs.userinfoservice.dtos.UserDetailsDto;
import com.avs.userinfoservice.dtos.UserDto;
import com.avs.userinfoservice.mapper.UserMapper;
import com.avs.userinfoservice.repositories.SiteRepository;
import com.avs.userinfoservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserInfoService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final SiteRepository siteRepository;

    @Autowired
    private final UserMapper mapper;

    public Flux<UserDto> GetUsers() {
        return userRepository.findAll()
                .map(mapper::Map);
    }

    public Mono<UserDetailsDto> userDetails(String userId) {
        return userRepository.findById(userId)
                .flatMap(user ->
                            siteRepository.countSiteAmount(userId)
                                    .map(amount -> new UserDetailsDto(user.getName(), amount))
                        );
    }
}