package com.decipherzone.service;

import org.bson.Document;

import java.util.List;

/**
 * Created by decipher on 22/9/17 12:24 PM
 * Abhishek Samuel (Software Engineer)
 * abhisheks@decipherzone.com
 * Decipher Zone Softwares
 * www.decipherzone.com
 */
public interface CustomerService {
    List<Document> findAll();
}
