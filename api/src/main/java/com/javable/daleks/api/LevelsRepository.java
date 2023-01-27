package com.javable.daleks.api;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface LevelsRepository extends MongoRepository<Level, String> {

    @Query("{ 'name' : ?0 }")
    Level findItemByName(String name);

    @Query(value="{ 'isCampaign' : ?0}", sort = "{ 'campaignOrder' : 1 }")
    List<Level> findAllByIsCampaign(boolean isCampaign);

}
