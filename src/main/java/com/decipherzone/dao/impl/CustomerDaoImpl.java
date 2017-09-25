package com.decipherzone.dao.impl;

import com.decipherzone.config.MongoFactory;
import com.decipherzone.dao.CustomerDao;
import com.google.inject.Singleton;
import org.bson.Document;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by decipher on 22/9/17 2:13 PM
 * Abhishek Samuel (Software Engineer)
 * abhisheks@decipherzone.com
 * Decipher Zone Softwares
 * www.decipherzone.com
 */
@Singleton
public class CustomerDaoImpl implements CustomerDao {

    private MongoFactory factory;

    @Inject
    public CustomerDaoImpl(MongoFactory factory) {
        this.factory = factory;
    }

    /**
     * Get all customer in collection
     * @return : List<Document> (List of documents in customer)
     */
    public List<Document> findAll() {
            return factory.getCustomerCollection().find().into(new ArrayList<>());
    }
}
