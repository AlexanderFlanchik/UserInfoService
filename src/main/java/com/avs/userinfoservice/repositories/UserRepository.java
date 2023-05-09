package com.avs.userinfoservice.repositories;

import com.avs.userinfoservice.domain.User;
import com.avs.userinfoservice.domain.UserDetails;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    @Aggregation({
            """
            {
                $match: { _id: ?0 }
            }
            """,
            """
            {
                $lookup: {
                    from: "Sites",
                    localField: "_id",
                    foreignField: "CreatedBy._id",
                    as: "sites"
                }
            }
            """,
            """
            {
                $unwind: "$sites"
            }
            """,
            """
            {
                $group: {
                    _id: { user_id: "$_id", user_name: "$Name" },
                    sites: { $sum: 1 }
                }
            }
            """,
            """
            {
                $project: {
                    _id: 0,
                    name: "$_id.user_name",
                    sites: 1
                }
            }
            """
    })
    Mono<UserDetails> GetUserDetails(String userId);
}