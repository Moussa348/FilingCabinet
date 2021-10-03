package com.keita.filingcabinet.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.keita.filingcabinet.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Autowired
    private MappingMongoConverter mappingMongoConverter;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Bean
    public GridFsTemplate gridFsTemplate(){
        return new GridFsTemplate(mongoDbFactory(),mappingMongoConverter);
    }

}
