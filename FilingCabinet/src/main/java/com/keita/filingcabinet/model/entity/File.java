package com.keita.filingcabinet.model.entity;

import com.mongodb.DBObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.BSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Data
@Document("files")
@NoArgsConstructor
@AllArgsConstructor
public class File implements Serializable, DBObject {

    @Id
    private String id;
    private String folderId;
    private String fileName, uploadBy, description;
    private LocalDateTime uploadDate;
    private Boolean isActive;

    @Override
    public void markAsPartialObject() {

    }

    @Override
    public boolean isPartialObject() {
        return false;
    }

    @Override
    public Object put(String s, Object o) {
        return null;
    }

    @Override
    public void putAll(BSONObject bsonObject) {

    }

    @Override
    public void putAll(Map map) {

    }

    @Override
    public Object get(String s) {
        return null;
    }

    @Override
    public Map toMap() {
        return null;
    }

    @Override
    public Object removeField(String s) {
        return null;
    }

    @Override
    public boolean containsField(String s) {
        return false;
    }

    @Override
    public Set<String> keySet() {
        return null;
    }
}
