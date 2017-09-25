package com.decipherzone.health;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by decipher on 22/9/17 6:00 PM
 * Abhishek Samuel (Software Engineer)
 * abhisheks@decipherzone.com
 * Decipher Zone Softwares
 * www.decipherzone.com
 */
public class MongoDBHealthCheck extends HealthCheck {

    private MongoClient mongoClient;

    public MongoDBHealthCheck(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    protected Result check() throws Exception {
        List<String> dbs = new ArrayList<>();
        MongoCursor<String> dbsCursor = mongoClient.listDatabaseNames().iterator();
        while (dbsCursor.hasNext()) {
            dbs.add(dbsCursor.next());
        }
        if (!dbs.isEmpty()) {
            return Result.healthy("Database names in MongogDB are: " + dbs);
        }
        return Result.unhealthy("DropwizardMongoDB Service is down");
    }
}
