package com.decipherzone.dao;

import org.bson.Document;
import java.util.List;

/**
 * Created by decipher on 22/9/17 2:14 PM
 * Abhishek Samuel (Software Engineer)
 * abhisheks@decipherzone.com
 * Decipher Zone Softwares
 * www.decipherzone.com
 */
public interface CustomerDao {
    List<Document> findAll();
}
