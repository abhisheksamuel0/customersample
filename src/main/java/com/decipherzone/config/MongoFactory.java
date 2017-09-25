package com.decipherzone.config;

import com.decipherzone.CustomerConfiguration;
import com.google.inject.Singleton;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.inject.Inject;

/**
 * Created by decipher on 22/9/17 5:45 PM
 * Abhishek Samuel (Software Engineer)
 * abhisheks@decipherzone.com
 * Decipher Zone Softwares
 * www.decipherzone.com
 */
@Singleton
public class MongoFactory {

    private CustomerConfiguration configuration;
    private MongoClient mongoClient;

    @Inject
    public MongoFactory(CustomerConfiguration configuration, MongoClient mongoClient) {
        this.configuration = configuration;
        this.mongoClient = mongoClient;
    }

    public MongoCollection<Document> getCustomerCollection() {
        MongoDatabase db = mongoClient.getDatabase(configuration.getMongoDB());
        return db.getCollection(configuration.getCollectionName());
    }
}
