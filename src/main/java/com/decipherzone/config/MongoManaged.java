package com.decipherzone.config;

import com.mongodb.Mongo;
import io.dropwizard.lifecycle.Managed;
/**
 * Created by decipher on 22/9/17 1:14 PM
 * Abhishek Samuel (Software Engineer)
 * abhisheks@decipherzone.com
 * Decipher Zone Softwares
 * www.decipherzone.com
 */

public class MongoManaged implements Managed {

    private Mongo mongo;

    public MongoManaged(Mongo mongo) {
        this.mongo = mongo;
    }

    @Override
    public void start() throws Exception {
        //implement required functionality
    }

    @Override
    public void stop() throws Exception {
        mongo.close();
    }

}