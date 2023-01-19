package com.javable.daleks.api;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface LevelsRepository extends MongoRepository<Level, String> {

    @Query("{ 'name' : ?0 }")
    Level findItemByName(String name);

}
