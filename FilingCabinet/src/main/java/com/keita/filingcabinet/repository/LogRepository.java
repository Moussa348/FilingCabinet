package com.keita.filingcabinet.repository;

import com.keita.filingcabinet.model.entity.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<Log,String> {
}
