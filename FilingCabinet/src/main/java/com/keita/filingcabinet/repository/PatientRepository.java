package com.keita.filingcabinet.repository;

import com.keita.filingcabinet.model.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    boolean existsByEmail(String email);
}
