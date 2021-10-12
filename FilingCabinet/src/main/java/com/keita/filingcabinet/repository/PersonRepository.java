package com.keita.filingcabinet.repository;

import com.keita.filingcabinet.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<User,String> {
}
