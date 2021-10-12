package com.keita.filingcabinet.repository;

import com.keita.filingcabinet.model.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category,String> {
    boolean existsByName(String name);
    List<Category>  findAllByIsActiveTrue();
}
