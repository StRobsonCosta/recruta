package br.com.kavex.recruta.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection="candidates")
public class Candidate {

    public ObjectId id;
    public String userId;
    public String area;
    public int progress;
}
