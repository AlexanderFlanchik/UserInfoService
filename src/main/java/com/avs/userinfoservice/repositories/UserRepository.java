package com.avs.userinfoservice.repositories;

import com.avs.userinfoservice.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
}