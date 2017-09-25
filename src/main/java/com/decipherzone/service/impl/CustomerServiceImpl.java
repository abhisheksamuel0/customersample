package com.decipherzone.service.impl;

import com.decipherzone.dao.CustomerDao;
import com.decipherzone.service.CustomerService;
import com.google.inject.Singleton;
import org.bson.Document;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by decipher on 22/9/17 12:24 PM
 * Abhishek Samuel (Software Engineer)
 * abhisheks@decipherzone.com
 * Decipher Zone Softwares
 * www.decipherzone.com
 */
@Singleton
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;

    @Inject
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * Get all customer in collection
     * @return : List<Document> (List of documents in customer)
     */
    @Override
    public List<Document> findAll() {
        return customerDao.findAll();
    }
}
