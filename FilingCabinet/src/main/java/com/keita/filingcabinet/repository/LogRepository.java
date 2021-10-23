package com.keita.filingcabinet.repository;

import com.keita.filingcabinet.model.entity.Log;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogRepository extends MongoRepository<Log, String> {
    List<Log> findAllByIdAndDateBetween(String id, LocalDateTime date1, LocalDateTime date2, Pageable pageable);//TODO --> test
}
