package com.avs.userinfoservice.repositories;

import com.avs.userinfoservice.domain.Site;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SiteRepository extends ReactiveMongoRepository<Site, String> {
    @Query(value = """
           {
             "CreatedBy.Id": ?0
           }
            """, count = true)
    Mono<Long> countSiteAmount(String userId);
}